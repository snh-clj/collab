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




(def pair-map
  {"MM" 1000
   "MD" 1000
   "MC" 1000
   "ML" 1000
   "MX" 1000
   "MV" 1000
   "MI" 1000
   "DC" 500
   "CM" 900
   "CD" 400
   "CC" 100
   "CX" 100
   "XC" 90
   "CL" 100
   "CV" 100
   "CI" 100
   "LX" 50
   "XL" 40
   "XX" 10
   "XI" 10
   "IX" 9
   "XV" 10
   "VI" 5
   "IV" 4
   "II" 1
   "I " 1
   "V " 5
   "X " 10
   "L " 50
   "C " 100
   "D " 500
   "M " 1000})

(def valid-subtractions
  {"CM" -100
   "CD" -100
   "XC" -10
   "XL" -10
   "IX" -1
   "IV" -1})

(def roman-vals
  {"M" 1000
   "D" 500
   "C" 100
   "L" 50
   "X" 10
   "V" 5
   "I" 1})


(defn pair->dec [pair]
  (or (valid-subtractions pair)
      (roman-vals (subs pair 0 1))
      0))

(defn accum-pair [acc pair]
  (+ acc (pair->dec pair)))

(defn chunk-roman [roman]
  (map #(apply str %) (partition 2 1 (char-array roman))))

(defn roman->dec [roman]
  (reduce accum-pair 0 (chunk-roman (str roman " "))))))

(defn four-clojure-92 [roman]
  (roman->dec roman))



(defn first-attempt [roman]
  (cond
   (empty? roman) 0
   (two-len-map (subs roman 0 2)) (+ (two-len-map (subs roman 0 2)) (first-attempt (subs roman 2)))
   (one-len-map (subs roman 0 1)) (+ (one-len-map (subs roman 0 1)) (first-attempt (subs roman 1)))))


(defn roman-val-2- [roman]
  (reduce #(+ %1 (or (pair-map %2) 0))
          0
          (map #(apply str %) (partition 2 1 (char-array roman)))))


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

(defn four-clojure-178 [hand]
  :fifty-two-pickup)

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
