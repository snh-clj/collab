(ns specifically-spec.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec.test :as test]
            [clojure.spec :as s]
            [specifically-spec.core :as ssc]))

(comment
  (defn gen-test-fn [sym]
    (->> (test/check sym)
         (filter :failure)))

  (deftest name-test
    (is (empty? (gen-test-fn `clojure.core/name))))

  (deftest map-test
    (is (empty? (gen-test-fn `clojure.core/map))))

  (deftest juxt-test
    (is (empty? (gen-test-fn `clojure.core/juxt))))
  )

(deftest make-pizza-test

  (is (= {:total 1 :check-passed 1}
         (-> `ssc/make-pizza test/check test/summarize-results)))
  )

(comment

  ;; To run all the tests in your REPL:
  (run-tests)


  )
