(ns bonus-round.core
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.fd :as fd]
            [clojure.math.combinatorics :as combo]))

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

(defn map-add-fn
  [i]
  (= 15 (reduce + i)))

(defn diags
  [matrix]
  (let [matrix (vec (flatten matrix))]
    [[(get matrix 2) (get matrix 4) (get matrix 6)]
     [(get matrix 0) (get matrix 4) (get matrix 8)]]))

(defn adds-to-15?
  [matrix]
  ;;; Rows.
  (when
    (and
      (every?
        true?
        (map
          map-add-fn
          (diags matrix)))
      (every?
        true?
        (map
          map-add-fn
        matrix))
    (every?
      true?
      (map map-add-fn (apply map list matrix))))
    matrix))

(defn flat-to-vecvec
  [fl]
  (vec (map vec (partition 3 fl))))

(defn generate-perms
  []
  (map flat-to-vecvec (combo/permutations [1 2 3 4 5 6 7 8 9])))

(defn magic-squares []
  (some
    adds-to-15?
    (generate-perms)))

;; 2014-09
;; 2048 -- come back to this?
;; https://github.com/moquist/twenty-forty-eight/blob/master/src/twenty_forty_eight.clj

