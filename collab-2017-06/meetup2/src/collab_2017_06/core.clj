(ns collab-2017-06.core
  (:require [clojure.zip :as z]))


(def nds "Our fancy, nested data structure"
  {:a [1 2 3 #{:a :b :c '(x1 y1 z1)}]
   :b [4 5 6 #{:d :e :f '(x2 y2 z2)}]})


(def znds "Our zipper around our fancy, nested data structure"
  (z/zipper coll? ;; branch predicate
            seq   ;; get children function
            (fn make-node [existing-node children]
              (prn :chilren children)
              (if (map-entry? existing-node)
                (vec children)
                (into (empty existing-node)
                      children)))
            nds))


(def znode "A node deep down in our zippered, fancy, nested data structure"
  (-> znds
    z/down
    z/right
    z/down
    z/right
    z/down
    z/right
    z/right
    z/right
    z/down))
