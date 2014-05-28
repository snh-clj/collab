(ns collab-2014-04.core-test
  (:require [clojure.test :refer :all]
            [collab-2014-04.core :as cc]))

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

(deftest four-clojure-92
  (testing "4Clojure #92"
    (let [__ cc/four-clojure-92]
      (is (= 14 (__ "XIV")))
      (is (= 827 (__ "DCCCXXVII")))
      (is (= 3999 (__ "MMMCMXCIX")))
      (is (= 48 (__ "XLVIII"))))))

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

(deftest four-clojure-178
  (testing "4Clojure #178"
    (let [__ cc/four-clojure-178]
      (is (= :high-card (__ ["HA" "D2" "H3" "C9" "DJ"])))
      (is (= :pair (__ ["HA" "HQ" "SJ" "DA" "HT"])))
      (is (= :two-pair (__ ["HA" "DA" "HQ" "SQ" "HT"])))
      (is (= :three-of-a-kind (__ ["HA" "DA" "CA" "HJ" "HT"])))
      (is (= :straight (__ ["HA" "DK" "HQ" "HJ" "HT"])))
      (is (= :straight (__ ["HA" "H2" "S3" "D4" "C5"])))
      (is (= :flush (__ ["HA" "HK" "H2" "H4" "HT"])))
      (is (= :full-house (__ ["HA" "DA" "CA" "HJ" "DJ"])))
      (is (= :four-of-a-kind (__ ["HA" "DA" "CA" "SA" "DJ"])))
      (is (= :straight-flush (__ ["HA" "HK" "HQ" "HJ" "HT"]))))))

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

(deftest magic-squares
  (testing "3x3 sum 15 Magic Square..."
    (let [result (cc/magic-squares)]
      (testing "Result must be a sequence of sequences each of three sequences of three numbers, 1-9."
        (testing "Result is a sequence"
          (is (sequential? result))
          (is (sequential? (first result)))
          (is (every? sequential? (first result)))
          (is (= 3 (count (first result))))
          (is (every? (comp (partial = 3) count) (first result)))))
      (let [[[a b c] [d e f] [g h i]] (first result)]
        (testing "All squares must be different"
          (is (distinct? a b c d e f g h i)))
        (testing "All squares must be in the range of 1-9"
          (is (every? #(<= 1 % 9) [a b c d e f g h i])))
        (testing "Rows sum to 15"
          (is (= 15 (+ a b c)))
          (is (= 15 (+ d e f)))
          (is (= 15 (+ g h i))))
        (testing "Columns sum to 15"
          (is (= 15 (+ a d g)))
          (is (= 15 (+ b e h)))
          (is (= 15 (+ c f i))))
        (testing "Diagonals sum to 15"
          (is (= 15 (+ a e i)))
          (is (= 15 (+ c e g))))))))


;; Helper macro for running individual tests from primary namespace
(defmacro run-test [tsym]
  `(binding [*report-counters* (ref *initial-report-counters*)]
     (test-vars [(ns-resolve (find-ns 'collab-2014-04.core-test) '~tsym)])
     @*report-counters*))
