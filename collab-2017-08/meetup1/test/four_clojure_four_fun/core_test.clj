(ns four-clojure-four-fun.core-test
 (:require [clojure.test :refer :all]
           [four-clojure-four-fun.core :as core]))

(deftest problem-32-test
  (let [__ core/problem-32]
    (is (= (__ [1 2 3]) '(1 1 2 2 3 3)))
    (is (= (__ [:a :a :b :b]) '(:a :a :a :a :b :b :b :b)))
    (is (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))
    (is (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))))

(deftest problem-44-test
  (let [__ core/problem-44]
    (is (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2)))
    (is (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3)))
    (is (= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1)))
    (is (= (__ 1 '(:a :b :c)) '(:b :c :a)))
    (is (= (__ -4 '(:a :b :c)) '(:c :a :b)))))
