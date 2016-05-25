(ns ecapseman.core-test
  (:require [clojure.test :refer :all]
            [ecapseman.core :as core]))

;; https://www.4clojure.com/problem/34 -- Implement range
;;
;; Difficulty: easy
;; Topics: seqs core-functions
;;
;; Write a function which creates a list of all integers in a given range.
;;
;; Restriction: range
(deftest four-clojure-34-test
  (testing "4Clojure #34"
    (let [__ core/four-clojure-34]
      (is (= (__ 1 4) '(1 2 3)))
      (is (= (__ -2 2) '(-2 -1 0 1)))
      (is (= (__ 5 8) '(5 6 7))))))


;; https://www.4clojure.com/problem/29 -- Get the Caps
;;
;; Difficulty: easy
;; Topics: strings
;;
;; Write a function which takes a string and returns a new string containing only the capital letters.
(deftest four-clojure-29-test
  (testing "4Clojure #29"
    (let [__ core/four-clojure-29]
      (is (= (__ "HeLlO, WoRlD!") "HLOWRD"))
      (is (empty? (__ "nothing")))
      (is (= (__ "$#A(*&987Zf") "AZ")))))


;; https://www.4clojure.com/problem/77 -- Anagram Finder
;;
;; Difficulty: medium
;;
;; Write a function which finds all the anagrams in a vector of
;; words. A word x is an anagram of word y if all the letters in x can
;; be rearranged in a different order to form y. Your function should
;; return a set of sets, where each sub-set is a group of words which
;; are anagrams of each other. Each sub-set should have at least two
;; words. Words without any anagrams should not be included in the
;; result.
(deftest four-clojure-77-test
  (testing "4Clojure #77"
    (let [__ core/four-clojure-77]
      (is (= (__ ["meat" "mat" "team" "mate" "eat"])
             #{#{"meat" "team" "mate"}}))
      (is (= (__ ["veer" "lake" "item" "kale" "mite" "ever"])
             #{#{"veer" "ever"} #{"lake" "kale"} #{"mite" "item"}})))))


;; https://projecteuler.net/problem=4 prep!
;; Write a predicate function that takes a number and returns a truthy
;; value if the number is a palindrome, falsy otherwise.
;;
;; Difficulty: medium
;;
;;   E.g.:
;;   => (project-euler-4-prep 1221)
;;   => true
;;   => (project-euler-4-prep 121)
;;   => true
;;   => (project-euler-4-prep 122)
;;   => false
(deftest project-euler-4-prep-test
  (let [__ core/project-euler-4-prep]
    (is (__ 121))
    (is (__ 1221))
    (is (__ 12345678987654321))
    (is (not (__ 10)))
    (is (not (__ 12)))
    (is (not (__ 122)))
    (is (not (__ 12000000)))))


;; https://projecteuler.net/problem=4
;;
;; Difficulty: hard
;;
;; A palindromic number reads the same both ways. The largest
;; palindrome made from the product of two 2-digit numbers is 9009 = 91
;; × 99.
;;
;; Write a function that calculates the largest palindrome made from
;; the product of two 3-digit numbers.
(deftest project-euler-4-test
  (testing "Project Euler #4"
    (= 906609 (core/project-euler-4))))


;; https://www.4clojure.com/problem/84 -- Transitive Closure
;;
;; Difficulty: hard
;; Topics: set-theory
;;
;; Write a function which generates the transitive closure
;; ( http://en.wikipedia.org/wiki/Transitive_closure ) of a binary
;; relation ( http://en.wikipedia.org/wiki/Binary_relation ). The
;; relation will be represented as a set of 2 item vectors.
(deftest four-clojure-84-test
  (testing "4Clojure #84"
    (let [__ core/four-clojure-84]
      (is (let [divides #{[8 4] [9 3] [4 2] [27 9]}]
            (= (__ divides) #{[4 2] [8 4] [8 2] [9 3] [27 9] [27 3]})))
      (is (let [more-legs
                #{["cat" "man"] ["man" "snake"] ["spider" "cat"]}]
            (= (__ more-legs)
               #{["cat" "man"] ["cat" "snake"] ["man" "snake"]
                 ["spider" "cat"] ["spider" "man"] ["spider" "snake"]})))
      (is (let [progeny
                #{["father" "son"] ["uncle" "cousin"] ["son" "grandson"]}]
            (= (__ progeny)
               #{["father" "son"] ["father" "grandson"]
                 ["uncle" "cousin"] ["son" "grandson"]}))))))
