(ns specifically-spec.core-test
  (:require [clojure.test :refer :all]
            [clojure.spec.test :as test]
            [clojure.spec :as s]
            [specifically-spec.core :as ssc]))

(defn gen-test-fn [sym]
  (->> (test/check sym)
       (filter :failure)))

(deftest name-test
  (is (empty? (gen-test-fn `clojure.core/name))))

(deftest map-test
  (is (empty? (gen-test-fn `clojure.core/map))))

(deftest juxt-test
  (is (empty? (gen-test-fn `clojure.core/juxt))))

(comment

  ;; To run all the tests in your REPL:
  (run-tests)


  )
