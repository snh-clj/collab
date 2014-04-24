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

(def nums
  [["M" 1000]
   ["IM" 999]
   ["VM" 995]
   ["XM" 990]
   ["LM" 950]
   ["CM" 900]
   ["D" 500]
   ["ID" 499]
   ["VD" 495]
   ["XD" 490]
   ["LD" 450]
   ["CD" 400]
   ["C" 100]
   ["IC" 99]
   ["VC" 95]
   ["XC" 90]
   ["L" 50]
   ["IL" 49]
   ["VL" 45]
   ["XL" 40]
   ["X" 10]
   ["IX" 9]
   ["V" 5]
   ["IV" 4]
   ["I" 1]])

(defn match [s]
  (let [res   (take 1 (filter (fn [n] (.startsWith s (first n))) nums))]
    (prn res (type res))
    res)
)

(defn match2 [s]
  (first (filter (fn [[n]] (.startsWith s n)) nums)))

(defn four-clojure-92 [roman]
  (loop [s roman
         n 0]
    (let [[[d v]] (match s)
          _ (prn d v)
          new-s (subs s (.length d))
          new-n (+ n v)]
      (if (empty? new-s)
        new-n
        (recur new-s new-n))
      )
    ))

(defn parse2 [roman]
  (loop [s roman
         n 0]
    (let [[d v] (match2 s)
          new-s (subs s (.length d))
          new-n (+ n v)]
      (if (empty? new-s)
        new-n
        (recur new-s new-n))
      )
    ))

;; http://www.4clojure.com/problem/178
;;
;; Following on from Recognize Playing Cards, determine the best poker
;; hand that can be made with five cards. The hand rankings are listed
;; below for your convenience.
;;
;; * Straight flush: All cards in the same suit, and in sequence
(declare is-flush is-straight)

(defn is-straight-flush [hand]
  (and (is-flush hand)
       (is-straight hand)))

;; * Four of a kind: Four of the cards have the same rank
(defn four-of-a-kind [hand]
  (->> hand
       (group-by second)
       (filter (fn [[k v]] (= 4 (count v))))
       empty?
       not)
  #_(not (empty? (filter (fn [[k v]] (= 4 (count v))) (group-by second hand)))))

;; * Full House: Three cards of one rank, the other two of another
;; * rank
(defn full-house [hand]
  ())

;; * Flush: All cards in the same suit
(defn is-flush [hand]
  (apply =  (map first hand)))

;; * Straight: All cards in sequence (aces can be high or low, but not both at once)
;; * Three of a kind: Three of the cards have the same rank
;; * Two pair: Two pairs of cards have the same rank
;; * Pair: Two cards have the same rank

(defn make-rank-checker [n]
  (fn [hand]
      (->> hand
       (group-by second)
       (filter (fn [[k v]] (= n (count v))))
       empty?
       not)))

(def three-of-a-kind (make-rank-checker 3))
(def pair (make-rank-checker 2))

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
