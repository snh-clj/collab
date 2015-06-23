(ns meet-test-check.meet-java-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]])
  (:import (meet_test_check MeetJava)))


(defspec all-ints-positive
  ;;increase 10 to 100, 1000, 10000 to trigger case where
  ;;incoming positive value results in negative value in output
  10
  (prop/for-all [v (gen/vector (gen/fmap #(+ 1 %) gen/pos-int))]
                (let [xformed-array (MeetJava/xformIntArray (int-array v))]
                  (every? pos? xformed-array))))
