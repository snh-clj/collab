(ns traveling-game.graph
  "Collection of data and functions related to creating, transforming, traversing, and scoring paths through graphs"
  (:require [clojure.string :as str]
            [clojure.math.combinatorics :as combo]))

(def nodes
  "Nodes that may be selected when creating a randomized graph
  Map from node-name to metadata about the node. The metadata is currently ignored."
  {"London" {:points 17}
   "Paris" {:points 14}
   "Nashua" {:points 3}
   "Hudson" {:points 1}
   "Stromsburg" {:points 9}
   "Osceola" {:points 8}
   "Medaryville" {:points 10}
   "Manchester" {:points 11}
   "Boston" {:points 19}
   "Portland" {:points 123}
   "Seattle" {:points 43}
   "Robin's_Nest" {:points 80}
   "Omaha" {:points 71}
   })

(defn select-nodes
  "Randomly select 'n nodes, presumeably to be used when constructing a graph."
  [n]
  (let [n (min n (count nodes))]
    (->> nodes
         keys
         shuffle
         (take n))))

(defn do-probability
  "'rand is inclusive of 0 and exclusive of 1, so our test should not have '=."
  [cutoff]
  (< (rand) cutoff))

(defn nodes->graph
  "Given
   * the 'prob[ability] that any two nodes will be connected by an edge,
   * the 'cost-min and 'cost-max for the randomized cost of any given edge, and
   * a seq of the nodes to be included in the graph,
  ...construct a graph represented as a vector of 3-tuple vectors.

  E.g.:
  (nodes->graph 0.5 1 10 (select-nodes 3))
  [[\"Robin's_Nest\" \"Stromsburg\" 1]
   [\"Osceola\" \"Robin's_Nest\" 2]
   [\"Osceola\" \"Stromsburg\" 3]]
  "
  [prob cost-min cost-max nodes]
  (let [prob (max prob 0.3)]
    (->> (mapcat ; Make a seq of every possible pair of nodes.
          (fn [x]
            (mapv #(vector x %) nodes))
          nodes)
         ;; Eliminate the node pairs that aren't (won't be) connected by an ed.ge
         (filter (fn [[a b]] (and (not= a b) (do-probability prob))))
         ;; Add randomized costs within the specified range.
         (mapv (fn [[a b]]
                 (vector a b
                         (+ cost-min (rand-int (- cost-max cost-min)))))))))

(defn graph->undirected
  "Take a directed graph and make it undirected, selecting whichever edge cost comes last."
  [graph]
  (reduce (fn [r [ab cost]]
            (let [[a b] (vec ab)]
              (conj r [a b cost] [b a cost])))
          []
          (reduce (fn [r [a b cost]]
                    (assoc r #{a b} cost))
                  {}
                  graph)))

(defn graph->undirected
  "Take a directed graph and make it undirected, selecting whichever edge cost comes last."
  [graph]
  (->> graph

       (reduce
        (fn [r [a b cost]]
          (assoc r #{a b} cost))
        {})

       (reduce
        (fn [r [ab cost]]
          (let [[a b] (vec ab)]
            (conj r [a b cost] [b a cost])))
        [])))


(defn travel
  "Given some nodes that have been visited, 'travel simultaneously
  from all of them to whatever can now be reached. Return the seq of
  distinct nodes reachable from everywhere you've been."
  [visited-nodes graph]
  (->> graph
       (filter #(-> % first visited-nodes))
       (map second)
       (distinct)))

(defn graph->nodes
  "Extract the node names from the given 'graph, returning a 'set."
  [graph]
  (->> graph
       (mapcat #(take 2 %))
       set))

(defn connected-from?
  "Determine if the given 'graph is connected (i.e., every node is
  reachable) when starting from 'starting-node."
  [graph starting-node]
  (let [all-nodes (graph->nodes graph)]
    (loop [graph graph
           visited-nodes (hash-set starting-node)]
      #_(println :graph (count graph) :visited-nodes visited-nodes :all-nodes all-nodes)
      (if (= visited-nodes all-nodes)
        true
        (let [reachable-nodes (travel visited-nodes graph)
              now-visited-nodes (into visited-nodes reachable-nodes)]
          #_(println :reachable-nodes (set reachable-nodes))
          #_(println :type-now-visited-nodes (type now-visited-nodes) :type-visited-nodes (type visited-nodes))
          #_(prn :now-visited-nodes now-visited-nodes)
          #_(prn :visited-nodes visited-nodes)
          (if (not= now-visited-nodes visited-nodes)
            (recur (filter #((complement now-visited-nodes) (first %)) graph)
                   now-visited-nodes)
            false))))))

(defn connected?
  "Testing every node that has an outgoing edge, return true if the
  graph is connected from all points, and false if it is not connected
  from any point. (We don't want to bother handling disconnected graphs.)"
  [graph]
  (let [all-starting-nodes (->> graph (map first) distinct)]
    (loop [starting-node (first all-starting-nodes)
           rest-start-nodes (rest all-starting-nodes)]
      (cond
        (connected-from? graph starting-node) true

        (empty? (rest rest-start-nodes)) false

        :default (recur (first rest-start-nodes) (rest rest-start-nodes))))))

(defn generate-connected-graph
  "Generate random graphs up to 'max-tries times, returning only when
  we've given up, or when we've generated a connected graph."
  [max-tries directed? connectivity-prob cost-min cost-max node-count]
  (loop [attempt-counter 0]
    (if (< attempt-counter max-tries)
      (let [g (nodes->graph connectivity-prob cost-min cost-max (select-nodes node-count))
            g (if directed? g (graph->undirected g))]
        (if (connected? g)
          g
          (recur (inc attempt-counter))))
      (do (println "ERROR: Failed to generate connected graph in" max-tries "tries")
          nil))))

(defn graph->map
  "Turn the given tuples graph into the same graph represented by a
  map from 2-tuples representing the direction of the edge to the cost
  of that edge: {[city-a, city-b] 43
                 [city-b, city-c] 86}

  E.g.:
  (graph->map [[\"London\" \"Omaha\" 4]
               [\"London\" \"Medaryville\" 4]
               [\"Omaha\" \"Medaryville\" 2]
               [\"Medaryville\" \"Omaha\" 4]])
  {[\"London\" \"Omaha\"] 4,
   [\"London\" \"Medaryville\"] 4,
   [\"Omaha\" \"Medaryville\"] 2,
   [\"Medaryville\" \"Omaha\"] 4}
  "
  [graph]
  (reduce
   (fn [r [a b cost]]
     (assoc r [a b] cost))
   {}
   graph))

(defn score
  "Add up the lengths of the edges specified in the provided 'path
  through the provided graph, which must be in the form returned by
  'graph->map."
  [graph-map path]
  (let [path (partition 2 1 path)
        s (reduce (fn [r hop]
                    #_(println :hop hop :graph-map (graph-map (vec hop)))
                    (+ r (or (graph-map (vec hop)) 0)))
                  0
                  path)]
    ;; (println :score s)
    s))

(defn hops-valid?
  "Return true if evey hop in the provided 'path is valid within the
  provided graph, which must be in the form returned by 'graph->map."
  [graph-map path]
  (every? graph-map (map vec (partition 2 1 path))))

(defn score-all-paths
  "Find all valid paths through the given 'graph, and score each
  one. How else will you know which one is best? Go, traveling
  salesperson, go!"
  [graph]
  (let [all-nodes (graph->nodes graph)
        all-paths (combo/permutations all-nodes)
        all-scores (->> all-paths
                        (filter #(hops-valid? (graph->map graph) %))
                        (reduce
                         (fn [r p]
                           (assoc r p (score (graph->map graph) p)))
                         {}))]
    ;; I kind of like this printout, so I'm keeping it. It's fun to
    ;; watch this go up as you increase the size of your graph and
    ;; your nodes->graph 'prob... and if you increase much beyond 8
    ;; nodes you're gonna need a bigger browser!
    (println :scored-paths-count (count all-scores))
    all-scores))

(defn graph->shortest-path
  "Find the shortest path through the given 'graph, by adding up all
  the available paths in the graph. This is what real traveling
  salespeople do."
  [graph]
  (->> graph
       score-all-paths
       (sort-by second)
       first))
