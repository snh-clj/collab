
(ns ListM
  (:require [Monads :as m]))

(extend-type clojure.lang.IPersistentList
  m/Monad
  (zero [_] (list))
  (wrap [_ x] (list x))
  (flat-map [xs f]
    (mapcat f xs)))

(defn plus-1 [x]
  (list x (inc x)))

(defn dbl [x]
  (list x (+ x x)))
