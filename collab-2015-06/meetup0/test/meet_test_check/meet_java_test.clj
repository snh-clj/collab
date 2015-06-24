(ns meet-test-check.meet-java-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]])
  (:import (meet_test_check MeetJava)))

(def int-vector (gen/vector (gen/fmap #(+ 1 %) gen/pos-int)))

(defn get-int-vector
  [size]
  (gen/sample int-vector size))

(defspec in-out-vectors-same-size
  ;;incoming positive value results in negative value in output
  1000
  (prop/for-all [v int-vector]
                (let [xformed-array (MeetJava/xformIntArray (int-array v))]
                  (= (count xformed-array) (count v)))))

(defspec all-ints-positive
  ;;increase 10 to 100, 1000, 10000 to trigger case where
  ;;incoming positive value results in negative value in output
  1000
  (prop/for-all [v int-vector]
                (let [xformed-array (MeetJava/xformIntArray (int-array v))]
                  (not-any? neg? xformed-array))))
