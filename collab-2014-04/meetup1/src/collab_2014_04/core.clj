(ns collab-2014-04.core
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]))

;; Solutions by: <individual or group names here>

;;; Clojure reference:
;; http://ClojureDocs.org


;; http://www.4clojure.com/problem/92
;;
;; Roman numerals are easy to recognize, but not everyone knows all
;; the rules necessary to work with them. Write a function to parse a
;; Roman-numeral string and return the number it represents.
;;
;; You can assume that the input will be well-formed, in upper-case,
;; and follow the subtractive principle. ^1 You don't need to handle any
;; numbers greater than MMMCMXCIX (3999), the largest number
;; representable with ordinary letters.
;;
;;  ^1 http://en.wikipedia.org/wiki/Roman_numerals#Subtractive_principle

(def symbols {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000})

(defn parse [str]
  (loop [[cur next & more] str total 0]
    (let [a (symbols cur 0)
          b (symbols next 0)]
      (if cur
        (if (>= a b)
          (recur (cons next more) (+ total a))
          (recur more (+ total b (- a))))
        total))))

(defn four-clojure-92 [roman]
  (parse roman))

;; http://www.4clojure.com/problem/178
;;
;; Following on from Recognize Playing Cards, determine the best poker
;; hand that can be made with five cards. The hand rankings are listed
;; below for your convenience.
;;
;; * Straight flush: All cards in the same suit, and in sequence
;; * Four of a kind: Four of the cards have the same rank
;; * Full House: Three cards of one rank, the other two of another rank
;; * Flush: All cards in the same suit
;; * Straight: All cards in sequence (aces can be high or low, but not both at once)
;; * Three of a kind: Three of the cards have the same rank
;; * Two pair: Two pairs of cards have the same rank
;; * Pair: Two cards have the same rank
;; * High card: None of the above conditions are met

(defn pair? [hand]
  )
(defn two-pair? [hand])
(defn triple? [hand])
(defn quadruple? [hand])
(defn full-house? [hand])
(defn straight? [hand])
(defn flush? [[[s _] :as hand]]
  (when (every?
         (fn [[s2 _]] (= s2 s))
         hand)
    :flush))
(defn straight-flush? [hand])

(defn four-clojure-178 [hand]
  (or (royal-flush? hand)
      (flush? hand)
      (quadruple? hand)
      (straight? hand)
      :high-card))

;; Magic Squares
;;
;; Given a three-by-three square, fill each square with a unique
;; number 1-9 so all rows, columns and diagonals sum to 15.
;; Each number should be used once and only once.
;;
;; The output of your function should return a sequence (or list or vector) of
;; all solutions, with ;; each solution being a sequence of three sequences of
;; three numbers, representing each row. e.g.
;;
;; [[[1 2 3][4 5 6][7 8 9]] [[2 3 4][5 6 7][8 9 1]] ... ]
;; ^--- the above doesn't meet summation constraints
;;
;; P.S. For those already comfortable with Clojure, this problem has a
;; nice solution using core.logic.

(defn magic-squares []
  [[[1 2 3] [4 5 6] [7 8 9]]
   [[9 8 7] [6 5 4] [3 2 1]]
   ,,,])

(comment
  ;; Run this first
  (require '[collab-2014-04.core-test :as test])

  ;; To run all tests:
  (clojure.test/test-ns 'collab-2014-04.core-test)

  ;; To run individual test:
  (test/run-test four-clojure-92)
  (test/run-test four-clojure-178)
  (test/run-test magic-squares)
)
