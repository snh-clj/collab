(ns birdie-tell.core
  "This ns implements a simple Gossip protocol, where peers each have
  a map of data they own, and they gossip to each other to pass along
  their own data.

  Each peer tracks versions of its data so that recipients of gossip
  can merge newer versions safely.

  Each peer rereads its data regularly from a specified EDN data
  file. This data is arbitrary."
  (:require [clojure.edn :as edn]
            [clojure.set :refer [difference]]
            [clojure.java.io :as io]
            [server.socket :as socket]
            [cheshire.core :as cheshire]
            [clojure.edn :as edn]
            [clojure.string :as str]
            [birdie-tell.util :as util]
            [clojure.pprint :refer [pprint]])
  (:import (java.net Socket SocketException)
           (java.io PrintWriter InputStreamReader BufferedReader)))

(comment
  ;; Sample peer state
  {:uuid "725b7252-d104-44e0-9c73-3cf7f87fb41d"
   :peers
   {:identified {"725b7252-d104-44e0-9c73-3cf7f87fb41d" {:data {:silk #{:calde :augur}
                                                                :auk  #{:prophet :thief}}
                                                         :version 10
                                                         :name "wolfe"
                                                         :host-port "127.0.0.1:1400"}
                 "f7fe1c2f-aaf8-4166-9689-588805424bcb" {:data {:grandpa-joe #{:old :bedridden}
                                                                :wonka #{:dapper :bottle-green-pants}
                                                                :charlie #{:mopey :poor}}
                                                         :version 0
                                                         :name "dahl"
                                                         :alive? true
                                                         :host-port "127.0.0.1:1401"}
                 "41008adf-fcdd-4ef5-8a70-0f0dccd3d806" {:data {:gimli #{:bearded :short :tired}
                                                                :legolas #{:lithe :merry}
                                                                :aragorn #{:brooding :wise :unaccountably-old}}
                                                         :version 7
                                                         :name "tolkien"
                                                         :alive? false
                                                         :host-port "127.0.0.1:1402"}}
    :potential #{"127.0.0.1:1403"}}}

  )

(def state (atom {
                  ;; mapping from UUIDs to states of peers, including me!
                  :peers {}
                  }))

(defn- merge-my-data
  "Take my state map and 'new-data.

  If 'new-data is equal to my state :data, return the state
  unchanged.

  If 'new-data is different than my current :data, assoc the new
  :data, 'inc my :version, and return the new state."
  [current-state new-data]
  (let [my-path [:peers :identified (:uuid current-state)]
        my-state (get-in current-state my-path)]
    (if (= (:data my-state) new-data)
      current-state
      (assoc-in current-state
                my-path
                (merge my-state {:data new-data :version (inc (:version my-state))})))))

(defn- clean-potential-peers
  "Remove each host-port entry in :peers :potential that is found in
  an :identified peer.

  Philosophical rationale: realized potential is no longer potential."
  [dirty-state]
  (let [identified-peer-host-ports (->> dirty-state :peers :identified
                                        (reduce (fn [r [k v]] (conj r (:host-port v))) #{}))]
    (update-in dirty-state
               [:peers :potential]
               difference
               identified-peer-host-ports)))

(defn- merge-peer-state
  "Take my current 'my-state map and 'peer-state, a complete state map from a peer.

  Merge the :peers from 'peer-state with :peers in 'my-state, ignoring
  myself, adding any new :identified peers, and updating :data,
  :version, and :host-port for :identified peers with higher versions
  than I have.

  TODO: consider :name changes
  TODO: think about merging :potential peers in case this peer can reach a host-port another peer cannot
  "
  [my-state peer-state]
  (util/debug :merge-peer-state :my-state my-state :peer-state peer-state)
  (let [my-uuid (:uuid my-state)
        peer-identified-peers (-> peer-state :peers :identified (dissoc my-uuid))
        my-identified-peers (-> my-state :peers :identified)
        version-check (fn [old new]
                        (if (>= (:version old) (:version new))
                          old
                          (merge old (select-keys new [:data :version :host-port]))))
        dirty-state (assoc-in my-state
                              [:peers :identified]
                              (merge-with version-check
                                          my-identified-peers
                                          peer-identified-peers))
        new-state (clean-potential-peers dirty-state)]
    (util/debug :new-state new-state)
    new-state))

(defn- parse-gossip
  "Take a 'gossip-handler, an 'input-stream-handler, an
  'output-stream-handler, an 'input-stream, and an 'output-stream.

  Parse 'input-stream EDN into a map of gossiped state, and call
  'gossip-handler with the parsed state. Then call
  'output-stream-handler with 'output-stream.

  Return nil."
  [gossip-handler input-stream-handler output-stream-handler input-stream output-stream]
  (let [input-string (input-stream-handler input-stream)
        peer-state (edn/read-string input-string)]
    (gossip-handler peer-state)
    (output-stream-handler output-stream)
    nil))

(defn- listen
  "Start listening on 'port, calling 'parse-gossip with
  'gossip-handler and input/output handlers on connect."
  [gossip-handler port]
  (let [input-stream-handler (fn [input-stream]
                               (with-open [rdr (io/reader input-stream)]
                                 (reduce str (line-seq rdr))))
        output-stream-handler (fn [output-stream] nil)]
    (socket/create-server port (partial parse-gossip
                                        gossip-handler
                                        input-stream-handler
                                        output-stream-handler))))

(defn- send-gossip
  "Send 'my-state to 'peer-host-port, calling 'handler-fn with 'true
  and the 'peer-host-port if the connection succeeds, and 'false and
  the 'peer-host-port if the connection times out."
  [handler-fn my-state peer-host-port]
  (let [[host port] (util/split-hostport peer-host-port)]
    (try
      (with-open [socket (Socket. host port)
                  _reader (BufferedReader. (InputStreamReader. (.getInputStream socket)))
                  writer (PrintWriter. (.getOutputStream socket))]
        (doto writer
          (.println (pr-str my-state))
          (.flush))
        (handler-fn true peer-host-port))
      (catch Exception e
        (util/debug :sending-exception (.getMessage e))
        (println "Seems dead: " [host port])
        (handler-fn false peer-host-port)))))

(defn- categorize-peer-liveness
  "Take a peers map with :identified and :potential keys, and extract
  a mapping of peer states to host-port strings.

  Example:
      (categorize-peer-liveness
       {:identified {\"uuid:1\" {:alive? true :host-port \"127.0.0.1:400\"}
                     \"uuid:2\" {:alive? false :host-port \"127.0.0.1:401\"}}
        :potential #{\"127.0.0.1:402\"}})
      ;;=> {:alive [\"127.0.0.1:400\"]
      ;;    :dead [\"127.0.0.1:401\"]
      ;;    :potential [\"127.0.0.1:402\"]}
  "
  [peers]
  (let [categorized (reduce
                     (fn [categories [_k v]]
                       (let [category (if (:alive? v) :alive :dead)
                             host-port (:host-port v)
                             category-members (categories category)]
                         (assoc categories category (conj category-members host-port))))
                     {:alive [] :dead []}
                     (:identified peers))]
    (assoc categorized :potential (-> peers :potential vec))))

(defn- select-peer
  "Take a 'live-percentage integer and a map of :identified and
  :potential peers.

  'live-percentage specifies the percentage liklihood of attempting to
  select an :alive? peer.

  If there are no :alive? peers, or in the remaining percentage of
  cases, randomly select (with 0.5 probability of either) a peer that
  is not :alive? or a :potential peer.

  If there are no :potential or :identified peers, return nil.

  Return a peer host-port string, e.g., \"hostname:1234\"
  "
  [live-percentage peers]
  (let [categorized-peers (categorize-peer-liveness peers)
        chosen-peers (into {} (map (fn [[k v]] [k (when (seq v) (rand-nth v))])
                                   categorized-peers))
        priorities (if (< (rand-int 100) live-percentage)
                     [:alive :dead :potential]
                     (if (zero? (rand-int 2))
                       [:dead :potential :alive]
                       [:potential :dead :alive]))]
    (->> priorities ; take our category priority order
         (map #(% chosen-peers)) ; get the selected peers in priority order
         (filter identity) ; eliminate 'nil peers (empty categories)
         first))) ; return the first

(defn- handle-peer-liveness
  "Take a 'state atom, an 'alive? predicate, and a 'peer-host-port.

  Find the first UUID corresponding to the specified 'peer-host-port.
  TODO: Figure out how to protect against / handle duplicate 'peer-host-port values in 'state.

  'swap! the 'alive? predicate for the specified 'peer-host-port into 'state.
  "
  [state alive? peer-host-port]
  (util/debug :handle-peer-liveness @state alive? peer-host-port)
  (if-let [uuid (->> @state
                     :peers
                     :identified
                     (filter (fn [[k v]] (= peer-host-port (:host-port v))))
                     ffirst)]
    (swap! state assoc-in [:peers :identified uuid :alive?] alive?)))

;; TODO: lift out call to 'send-gossip, 'Thread/sleep, and rest-time handling
(defn start-gossipping! [live-percentage delay-fn state]
  (let [my-uuid (:uuid @state)
        filter-out-self (fn [{:keys [identified potential]}]
                          {:potential potential
                           :identified (filter (fn [[k v]] (not= my-uuid k)) identified)})]
    (while true
      (util/debug :start-gossipping @state)
      (if-let [peer (select-peer live-percentage (filter-out-self (:peers @state)))]
        (do
          (util/debug :gossiping-to-peer peer)
          ;; TODO: lift send-gossip out of here, take in a send-gossip fn instead
          (send-gossip (partial handle-peer-liveness state) @state peer)))
      (delay-fn))))

(defn- do-self-updating!
  "Parse 'data-file as EDN every 'reread-delay-ms, and update :data in
  'state (with version bumps) when 'data-file changes."
  [data-file state reread-delay-ms]
  (while true
      (let [data (edn/read-string (slurp data-file))]
        (swap! state merge-my-data data))
      (Thread/sleep reread-delay-ms)))

(defn calculate-propagation-steps
  "Calculate the probabilistic number of gossip steps it will take for
  data from one peer to reach every other cluster member in a cluster
  of size 'cluster-size. When the probable number of peers is within
  'tolerance of 'cluster-size, consider the propagation to be complete.

  'tolerance defaults to 0.1.

  Examples:
    (calculate-propagation-steps 100)
    ;;=> 10
    (calculate-propagation-steps 1000)
    ;;=> 14
    (calculate-propagation-steps 10000)
    ;;=> 17
  "
  ([cluster-size] (calculate-propagation-steps cluster-size 0.1))
  ([cluster-size tolerance] (calculate-propagation-steps cluster-size tolerance 1 0))
  ([cluster-size tolerance knowers steps]
   (util/debug :cluster-size cluster-size :tolerance tolerance :knowers knowers :steps steps)
   (if (<= cluster-size (+ knowers tolerance))
     steps
     (recur
      cluster-size
      tolerance
      (+' knowers (*' knowers (/ (-' (dec cluster-size) (dec knowers)) (dec cluster-size))))
      (inc steps)))))

(defn main [& args]
  (let [{:keys [debug host-port input-file live-percentage maximum-gossip-wait minimum-gossip-wait name peer uuid]}
        (util/parse-opts args)]
    (if debug (reset! util/debug? true)) ; enable debugging immediately, if we want it
    (let [uuid (or uuid (util/uuid)) ; ensure this peer has a UUID
          [_ my-port] (util/split-hostport host-port) ; split out this peer's listening port
          peer (if peer #{peer} #{}) ; set up our initial set of potential peers

          ;; Create a fn to delay between gossip attempts.
          delay-fn (util/make-delay-fn minimum-gossip-wait maximum-gossip-wait)]

      ;; Initialize local peer state.
      (swap! state assoc
             :uuid uuid ; my self-reference into [:peers :identified]
             :peers {:potential peer ; TODO: allow more than one potential peer
                     :identified {uuid {:version 0

                                        ;; Leave :name out for now;
                                        ;; it's not needed with
                                        ;; user-supplied UUIDs, and
                                        ;; fills up the screen too
                                        ;; much.
                                        ;; :name name

                                        :uuid uuid
                                        :host-port host-port}}})

      ;; Start listening for gossip.
      (listen #(swap! state merge-peer-state %) my-port)

      ;; Start watching my local input file for changes to this peer's :data.
      (.start (Thread. #(do-self-updating! input-file state 1000)))

      ;; TODO: fix state refs to be to the var, & resolve the var
      ;; Start spreading gossip.
      (.start (Thread. #(start-gossipping!
                         live-percentage
                         delay-fn
                         state)))

      ;; Output this peer's state each second to watch for changes.
      (while true
        (pprint @state) (prn)
        (Thread/sleep 1000)))))

(comment
  (require 'birdie-tell.core :reload-all) (in-ns 'birdie-tell.core) (use 'clojure.repl)
  )

