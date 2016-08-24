(ns specifically-spec.core
  (:require [clojure.spec :as s]
            [clojure.spec.test :as test]
            [clojure.spec.gen :as gen]))

(s/fdef clojure.core/name
        :args (s/alt :str string?
                     :kw keyword?
                     :sym symbol?)
        :fn (fn [{:keys [ret]
                  [_t v] :args}]
              (.endsWith (str v) ret))
        :ret #_(= % "hi")  string?)

(s/fdef clojure.core/map
        ;; broken ATM
        :args (s/cat :f (s/spec ifn? :gen #(gen/return (constantly true)))
                     :colls (s/spec (s/* coll?)))
        :ret (s/alt :seq seq? :transducer fn?)
        :fn (fn [{:keys [ret] {:keys [f colls]} :args}]
              (if (seq colls)
                (= (count ret) (apply min (map count colls)))
                (fn? ret))))
