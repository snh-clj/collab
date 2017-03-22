
(ns StateM
  (:require [Monads :as m]))

(defmacro state [& exprs] `(StateM/StateFn. (fn [state-value#]
                                              [(do ~@exprs) state-value#])))

(deftype StateFn [fx]
  clojure.lang.IFn
  (invoke [_ state-value] (fx state-value))

  m/Monad
  (wrap [_ x] (state x))
  (flat-map [inner-fn state-f]
    (StateFn. (fn [state-value]
                (let [[x new-state] (inner-fn state-value)]
                  ((state-f x) new-state))))))

(defmethod print-method StateFn [v ^java.io.Writer w]
    (.write w "<StateFn ...>"))

(defn get-state []
  (StateFn. (fn [state-value]
              [state-value state-value])))

(defn update-state [f]
  (StateFn. (fn [state-value]
              [state-value (f state-value)])))

(defn get-val
  ([key]
   (StateFn. (fn [state-value]
               [(get state-value key) state-value])))
  ([key default-value]
   (StateFn. (fn [state-value]
               [(get state-value key default-value) state-value]))))

(defn assoc-val [key value]
  (StateFn. (fn [state-value]
              [state-value (assoc state-value key value)])))

(defn update-val [key f & args]
  (StateFn. (fn [state-value]
              [state-value (apply update state-value key f args)])))

(defn plus-1 [x]
  (state (println "executing 'plus-1' with input of" x)
         (inc x)))

(defn dbl [x]
  (state (println "executing 'dbl' with input of" x)
         (+ x x)))

