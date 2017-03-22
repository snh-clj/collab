
(ns VectorM
  (:require [Monads :as m]))

(extend-type clojure.lang.IPersistentVector
  m/Monad
  (zero [_] [])
  (wrap [_ x] (vector x))
  (flat-map [xs f]
    (vec (mapcat f xs))))

(defn plus-1 [x]
  (vector x (inc x)))

(defn dbl [x]
  (vector x (+ x x)))

;; Verify the 3 monad laws

(= (m/flat-map (vector 1 2 3) vector)
   (vector 1 2 3))

(= (m/flat-map (vector 4) dbl)
   (dbl 4))

(= (m/flat-map (m/flat-map (vector 4) plus-1) dbl)
      (m/flat-map (vector 4) (fn [x] (m/flat-map (plus-1 x) dbl))))
