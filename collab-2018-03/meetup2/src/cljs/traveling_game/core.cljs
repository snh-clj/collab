(ns traveling-game.core
  "Provide a \"UI\" of sorts on a digraph-generating, path-scoring toy thing."
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]
            [reagent.core :as reagent]
            [traveling-game.graph :as tgg]
            [cljs.pprint :as pprint]
   ))

(enable-console-print!)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; These functions could go somewhere else.

(defn parse-path
  "We'll handle almost anything the user cares to throw at us, and we
  eat spaces and commas just like Clojure does!"
  [path]
  (str/split path #"[ ,]+"))

(defn graph->d3-format
  "This was a really quick hack to munge a graph into a format like
  the one used in a D3 force-directed layout example I found."
  [graph]
  (let [nodes (tgg/graph->nodes graph)]
    {"nodes" (mapv #(hash-map "id" % "group" (rand-int 20)) nodes)
     "links" (->> graph
                  (reduce (fn [r [a b cost]] (assoc r #{a b} cost)) {}) ; eliminate duplicates in undirected graph
                  (mapv (fn [[ab cost]]
                          (let [[a b] (vec ab)]
                            (hash-map "source" a
                                      "target" b
                                      "value" cost)))))}))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Vars

(def default-node-count 6)
(def max-node-count 8) ;; up to 40320 paths to evaluate... keeps a browser busy.
(def edge-cost-min 1)
(def edge-cost-max 100)

(defonce app-state
  ;; Everything related to the UI state should go in here. Except for
  ;; what doesn't (which isn't much).
  (reagent/atom {:graph nil
                 :proposed-path nil
                 :node-count default-node-count
                 :shortest-path nil
                 :show-shortest-path? false
                 :show-shortest-score? false}))

(defn toggle
  "I borrowed this code from the Internet. It's handy for checkboxes."
  [id]
  (swap! app-state update id not))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; UI elements

(defn proposed-solution
  "Display a text input box, in which the user can type the names of
  visited nodes to attempt a solution."
  []
  [:div {:class "container"}
   [:input {:type "text"
            :size 86
            :placeholder "Solution as: Name Name Name"
            ;; It's a clever idea to set the value of the box from
            ;; what's been parsed, because you can simply eat invalid
            ;; input, do spell-checking, auto-completion of unique
            ;; strings, etc. But I only got as far as realizing this
            ;; pevented the user from typing any space characters, and
            ;; thereby traveling anywhere at all.
            #_#_:value (str/join " " (:proposed-path @app-state))
            :on-change #(let [input (-> % .-target .-value)]
                          (swap! app-state assoc :proposed-path (parse-path input))
                          (set! (.-value (.-target %)) input))}]])

(defn graph-node
  "Display an individual graph node as a textual list item."
  [[a b cost]]
  [:li {:key (str a b cost)} (str a " -> " b " (" cost ")")])

(defn graph-display-text
  "Display a given graph textually."
  [graph]
  [:ul
   (for [node graph]
     ;; fn call [note the round parens] to avoid :key prop errors
     ;; https://github.com/reagent-project/reagent/issues/34
     (graph-node node)
     )])

(defn graph-display-fdg-element
  "Display an SVG. This was an experiment that I didn't get to finish to my satisfaction.."
  []
  [:div.svg-container {:class "container"
                       :id "graph-container"}
   #_
   [:svg.svg-content-responsive {:id "graph_fdg"
                                 :viewBox "-150 -60 300 120"}]])

(defn ui-controls
  "Display some controls to reset the graph, toggle some display elements, etc."
  []
  (let [num-cities-val (reagent/atom nil)]
    ;; Closing over num-cities-val means we have local, closed-over UI state.
    ;; In this instance, that works pretty well to disallow invalid
    ;; input entirely, cap input at our max-node-count, etc.
    (fn []
      (let [{:keys [node-count]} @app-state]
        [:div {:class "container"}
         [:div {:class "input-group mb-3"}
          [:dev {:class "input-group-prepend"}
           [:button {:type "button"
                     :class "btn btn-secondary"
                     :on-click (fn []
                                 ;; It would be neat to wire up more UI elements to control some of these parameters!
                                 (let [g (tgg/generate-connected-graph 10 true 0.6 edge-cost-min edge-cost-max node-count)]
                                   (swap! app-state assoc
                                          :graph g
                                          :shortest-path (tgg/graph->shortest-path g))))}
            "Reset"]]
          [:input {:type "text" ; This text box is really, really wide, given that it accepts a single digit as input.
                   :id "number-of-cities"
                   :class "form-control"
                   :placeholder (str "Number of cities (max " max-node-count ", default " default-node-count ")")
                   :value @num-cities-val
                   :on-change #(let [input (-> % .-target .-value js/parseInt)
                                     newval (if (and (int? input) (< 0 input))
                                              (min input max-node-count)
                                              "")]
                                 (reset! num-cities-val newval)
                                 (swap! app-state assoc
                                        :node-count @num-cities-val)
                                 ;; Better to use :value, above. But this also works.
                                 #_(set! (.-value (.-target %)) @num-cities-val))}]]
         [:div {:class "form-check form-check-inline"}
          [:input {:class "form-check-input"
                   :type "checkbox"
                   :id "show-shortest-score"
                   :on-change #(toggle :show-shortest-score?)}]
          [:label {:class "form-check-label"
                   :for "show-shortest-score"}
           "Show best possible score"]
          [:input {:class "form-check-input"
                   :type "checkbox"
                   :id "show-shortest-path"
                   :on-change #(toggle :show-shortest-path?)}]
          [:label {:class "form-check-label"
                   :for "show-shortest-path"}
           "Show best possible path"]]]
        ))))

(defn proposed-path-score
  "Display the proposed path as it has been parsed, the nodes
  remaining to be visited, and maybe the best-possible score and the
  best-possible path."
  []
  (let [{:keys [proposed-path graph shortest-path show-shortest-path? show-shortest-score?]}
        (select-keys @app-state [:proposed-path :graph :shortest-path :show-shortest-path? :show-shortest-score?])

        nodes (tgg/graph->nodes graph)
        [shortest-path shortest-score] shortest-path
        score (when proposed-path (tgg/score (tgg/graph->map graph) proposed-path))
        remaining-cities (if proposed-path
                           (let [visited (into #{} proposed-path)]
                             (filter (complement visited) nodes))
                           nodes)]
    [:div
     [:p "Parsed proposed path:" (str/join " " proposed-path)]
     [:p "Cities remaining: " (str/join " " remaining-cities)]
     [:p (str "Score: " score)]
     (when (and show-shortest-score? shortest-score)
       [:p (str "Best possible score: " shortest-score)])
     (when (and show-shortest-path? shortest-path)
       [:p (str "Best possible path: " shortest-path)])]))



(defn page [ratom]
  "Display the whole page. Don't display the D3 force-directed graph,
  because it's useless without any labels. Also, it doesn't have any
  labels."
  (let [graph-render-hack (reagent/atom nil)]
    (fn []
      (let [current-graph (:graph @ratom)]
        [:div {:class "container"}
         "Welcome to a graph"
         [ui-controls]
         [graph-display-text current-graph]
         #_
         (graph-display-fdg-element)
         (when (and false
                    current-graph
                    (not= current-graph @graph-render-hack))
           ;; Don't redraw our D3 graph constantly. Whee!
           (reset! graph-render-hack current-graph)
           (js/fdg_go (clj->js (graph->d3-format current-graph))))
         [proposed-solution]
         [proposed-path-score]
         ]))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Initialize App

(defn dev-setup []
  (when ^boolean js/goog.DEBUG
    (enable-console-print!)
    (println "dev mode")
    ))

(defn reload []
  (reagent/render [page app-state]
                  (.getElementById js/document "app"))
  #_
  (println (graph->d3-format (:graph @app-state)))
  ;; If you're going to display the graph, you need to re-render it
  ;; after a code reload. Otherwise your state gets weird.
  #_
  (js/fdg_go (clj->js (graph->d3-format (:graph @app-state))))
  )

(defn ^:export main []
  (dev-setup)
  (reload))

(comment
  (def g (nodes->graph 0 8 88 (select-nodes 5)))

  (swap! app-state assoc
         :graph g
         :shortest-path (graph->shortest-path g))

  (println :gurp (score-all-paths g))

  (.-value (.getElementById js/document "number-of-cities"))
  )

