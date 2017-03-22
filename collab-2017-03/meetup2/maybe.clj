
(ns MaybeM
  (:require [Monads :as m]))


(deftype Maybe [x]
  java.lang.Object
  (toString [_] (str "<Maybe " x ">"))

  m/Monad
  (zero [_] nil)
  (wrap [_ new-x] (Maybe. new-x))
  (flat-map [_ f]
    (when x
      (f x))))

(defmethod print-method Maybe [v ^java.io.Writer w]
  (.write w (str v)))

(extend-type nil
  m/Monad
  (wrap [_ x] (Maybe. x))
  (flat-map [_ _] nil))

(defn plus-1 [x]
  (when (odd? x)
    (Maybe. (inc x))))

(defn dbl [x]
  (when (even? x)
    (Maybe. (+ x x))))
