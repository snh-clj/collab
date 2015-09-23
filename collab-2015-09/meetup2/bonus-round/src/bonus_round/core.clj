(ns bonus-round.core
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd])
  )

;; 2014-04
;; Penultimate Problem: 4Clojure 178
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

;; 2014-04
;; Ultimate Problem: Magic Squares
;;
;; Given a three-by-three square, fill each square with a unique
;; number 1-9 so all rows, columns and diagonals sum to 15.
;; Each number should be used once and only once.
;;
;; The output of your function should return a sequence (or list or vector) of
;; all solutions, with each solution being a sequence of three sequences of
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

;; 2014-09
;; 2048 -- come back to this?
;; https://github.com/moquist/twenty-forty-eight/blob/master/src/twenty_forty_eight.clj

