(ns sn.core-test
  (:require [clojure.test :refer :all]
            [sn.core :as sn]))

;; http://www.4clojure.com/problem/26 -- Fibonacci Sequence

;; Difficulty: easy
;; Topics: Fibonacci seqs

;; Write a function which returns the first X fibonacci numbers.
(deftest four-clojure-26-test
  (testing "4Clojure #26"
    (let [__ sn/four-clojure-26]
      (is (= (__ 3) '(1 1 2)))
      (is (= (__ 6) '(1 1 2 3 5 8)))
      (is (= (__ 8) '(1 1 2 3 5 8 13 21))))))


;; http://www.4clojure.com/problem/30 -- Compress a Sequence

;; Difficulty: easy
;; Topics: seqs

;; Write a function which removes consecutive duplicates from a sequence.
(deftest four-clojure-30-test
  (testing "4Clojure #30"
    (let [__ sn/four-clojure-30]
      (is (= (apply str (__ "Leeeeeerrroyyy")) "Leroy"))
      (is (= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3)))
      (is (= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))))))

;; http://www.4clojure.com/problem/53 -- 53Longest Increasing Sub-Seq

;; Difficulty: hard
;; Topics: seqs

;; Given a vector of integers, find the longest consecutive
;; sub-sequence of increasing numbers. If two sub-sequences have the
;; same length, use the one that occurs first. An increasing
;; sub-sequence must have a length of 2 or greater to qualify.
(deftest four-clojure-53-test
  (testing "4Clojure #53"
    (let [__ sn/four-clojure-53]
      (is (= (__ [1 0 1 2 3 0 4 5]) [0 1 2 3]))
      (is (= (__ [5 6 1 3 2 7]) [5 6]))
      (is (= (__ [2 3 3 4 5]) [3 4 5]))
      (is (= (__ [7 6 5 4]) [])))))

;; http://www.4clojure.com/problem/54 -- Partition a Sequence

;; Difficulty: medium
;; Topics: seqs core-functions

;; Write a function which returns a sequence of lists of x items
;; each. Lists of less than x items should not be returned.
(deftest four-clojure-54-test
  (testing "4Clojure #54"
    (let [__ sn/four-clojure-54]
      (is (= (__ 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8))))
      (is (= (__ 2 (range 8)) '((0 1) (2 3) (4 5) (6 7))))
      (is (= (__ 3 (range 8)) '((0 1 2) (3 4 5)))))))

;; http://www.4clojure.com/problem/60 -- Sequence Reductions

;; Difficulty: medium
;; Topics: seqs core-functions

;; Write a function which behaves like reduce, but returns each
;; intermediate value of the reduction. Your function must accept
;; either two or three arguments, and the return sequence must be
;; lazy.
(deftest four-clojure-60-test
  (testing "4Clojure #60"
    (let [__ sn/four-clojure-60]
      (is (= (take 5 (__ + (range))) [0 1 3 6 10]))
      (is (= (__ conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]]))
      (is (= (last (__ * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120)))))

;; http://www.4clojure.com/problem/73 -- Analyze a Tic-Tac-Toe Board

;; Difficulty: hard
;; Topics: game

;; A tic-tac-toe board is represented by a two dimensional vector. X is
;; represented by :x, O is represented by :o, and empty is represented
;; by :e. A player wins by placing three Xs or three Os in a
;; horizontal, vertical, or diagonal row. Write a function which
;; analyzes a tic-tac-toe board and returns :x if X has won, :o if O
;; has won, and nil if neither player has won.
(deftest four-clojure-73-test
  (testing "4Clojure #73"
    (let [__ sn/four-clojure-73]
      (is (= nil (__ [[:e :e :e]
                      [:e :e :e]
                      [:e :e :e]])))
      (is (= :x (__ [[:x :e :o]
                     [:x :e :e]
                     [:x :e :o]])))
      (is (= :o (__ [[:e :x :e]
                     [:o :o :o]
                     [:x :e :x]])))
      (is (= :x (__ [[:x :e :e]
                     [:o :x :e]
                     [:o :e :x]])))
      (is (= :o (__ [[:x :e :o]
                     [:x :o :e]
                     [:o :e :x]])))
      (is (= nil (__ [[:x :o :x]
                      [:x :o :x]
                      [:o :x :o]]))))))
