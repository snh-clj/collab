
(ns SetM
  (:require [Monads :as m]))

(extend-type clojure.lang.PersistentHashSet
  m/Monad
  (zero [_] #{})
  (wrap [_ x] (hash-set x))
  (flat-map [xs f]
    (set (mapcat f xs))))

(defn plus-1 [x]
  (hash-set x (inc x)))

(defn dbl [x]
  (hash-set x (+ x x)))
