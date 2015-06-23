(ns meet-test-check.meet-java-test
  (:require [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]])
  (:import (meet_test_check MeetJava)))


(defspec all-ints-positive
  10
  (prop/for-all [v (gen/vector (gen/fmap #(+ 1 %) gen/pos-int))]
                (let [xformed-array (MeetJava/xformIntArray (int-array v))]
                  (every? pos? xformed-array))))
#_(defspec all-ints-positive
  100
  (prop/for-all [v (gen/vector gen/int)]
     (every? pos? v)))
