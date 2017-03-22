
(ns LazyM
  (:require [Monads :as m]))

(deftype Thunk [fx]
  clojure.lang.IFn
  (invoke [_] (fx))

  (wrap [_ x] (thunk x))
  (flat-map [inner-thunk thunk-f]
    (Thunk. (fn []
              (let [inner-x (inner-thunk)
                    new-x-thunk (thunk-f inner-x)]
                (new-x-thunk))))))

(defmethod print-method Thunk [v ^java.io.Writer w]
  (.write w "<Thunk ...>"))


(defmacro thunk [& exprs] `(LazyM/Thunk. (fn [] (do ~@exprs))))

(defn plus-1 [x]
  (thunk (println "executing 'plus-1' with input of" x)
         (inc x)))

(defn dbl [x]
  (thunk (println "executing 'dbl' with input of" x)
         (+ x x)))
