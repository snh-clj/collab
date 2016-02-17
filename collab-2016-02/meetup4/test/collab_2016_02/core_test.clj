(ns collab-2016-02.core-test
  (:require [clojure.test :refer :all]
            [collab-2016-02.core :as cc]))

;; https://www.4clojure.com/problem/141 -- Tricky Card Games
;;
;; Difficulty:Medium
;; Topics:game cards
;;
;; In trick-taking card games such as bridge, spades, or hearts, cards
;; are played in groups known as "tricks" - each player plays a single
;; card, in order; the first player is said to "lead" to the
;; trick. After all players have played, one card is said to have
;; "won" the trick. How the winner is determined will vary by game,
;; but generally the winner is the highest card played in the suit
;; that was led. Sometimes (again varying by game), a particular suit
;; will be designated "trump", meaning that its cards are more
;; powerful than any others: if there is a trump suit, and any trumps
;; are played, then the highest trump wins regardless of what was led.
;;
;; Your goal is to devise a function that can determine which of a
;; number of cards has won a trick. You should accept a trump suit,
;; and return a function winner. Winner will be called on a sequence
;; of cards, and should return the one which wins the trick. Cards
;; will be represented in the format returned by Problem 128,
;; Recognize Playing Cards: a hash-map of :suit and a numeric
;; :rank. Cards with a larger rank are stronger.

(deftest four-clojure-141
  (testing "4Clojure #141"
    (let [__ cc/four-clojure-141]
      (is (let [notrump (__ nil)]
            (and (= {:suit :club :rank 9}  (notrump [{:suit :club :rank 4}
                                                     {:suit :club :rank 9}]))
                 (= {:suit :spade :rank 2} (notrump [{:suit :spade :rank 2}
                                                     {:suit :club :rank 10}])))))
      (is (= {:suit :club :rank 10} ((__ :club) [{:suit :spade :rank 2}
                                                 {:suit :club :rank 10}])))
      (is (= {:suit :heart :rank 8}
             ((__ :heart) [{:suit :heart :rank 6} {:suit :heart :rank 8}
                           {:suit :diamond :rank 10} {:suit :heart :rank 4}]))))))

;; https://www.4clojure.com/problem/158 -- Decurry
;;
;; Difficulty:Medium
;; Topics:partial-functions
;;
;; Write a function that accepts a curried function of unknown arity n.
;; Return an equivalent function of n arguments.
;;
;; You may wish to read http://en.wikipedia.org/wiki/Currying

(deftest four-clojure-158
  (testing "4Clojure #158"
    (let [__ cc/four-clojure-158]
      (is (= 10 ((__ (fn [a]
                       (fn [b]
                         (fn [c]
                           (fn [d]
                             (+ a b c d))))))
                 1 2 3 4)))
      (is (= 24 ((__ (fn [a]
                       (fn [b]
                         (fn [c]
                           (fn [d]
                             (* a b c d))))))
                 1 2 3 4)))
      (is (= 25 ((__ (fn [a]
                       (fn [b]
                         (* a b))))
                 5 5))))))

;; https://www.4clojure.com/problem/164 -- Language of a DFA
;;
;; Difficulty:Hard
;; Topics:automata seqs
;;
;; A deterministic finite automaton (DFA) is an abstract machine that
;; recognizes a regular language. Usually a DFA is defined by a
;; 5-tuple, but instead we'll use a map with 5 keys:
;;
;;  :states is the set of states for the DFA.
;;  :alphabet is the set of symbols included in the language recognized by the DFA.
;;  :start is the start state of the DFA.
;;  :accepts is the set of accept states in the DFA.
;;  :transitions is the transition function for the DFA, mapping :states 
;;
;; Write a function that takes as input a DFA definition (as described
;; above) and returns a sequence enumerating all strings in the
;; language recognized by the DFA. Note: Although the DFA itself is
;; finite and only recognizes finite-length strings it can still
;; recognize an infinite set of finite-length strings. And because
;; stack space is finite, make sure you don't get stuck in an infinite
;; loop that's not producing results every so often!

(deftest four-clojure-164
  (testing "4Clojure #164"
    (let [__ cc/four-clojure-164]
      (is (= #{"a" "ab" "abc"}
             (set (__ '{:states #{q0 q1 q2 q3}
                        :alphabet #{a b c}
                        :start q0
                        :accepts #{q1 q2 q3}
                        :transitions {q0 {a q1}
                                      q1 {b q2}
                                      q2 {c q3}}}))))
      (is (= #{"hi" "hey" "hello"}
             (set (__ '{:states #{q0 q1 q2 q3 q4 q5 q6 q7}
                        :alphabet #{e h i l o y}
                        :start q0
                        :accepts #{q2 q4 q7}
                        :transitions {q0 {h q1}
                                      q1 {i q2, e q3}
                                      q3 {l q5, y q4}
                                      q5 {l q6}
                                      q6 {o q7}}}))))
      (is (= (set (let [ss "vwxyz"] (for [i ss, j ss, k ss, l ss] (str i j k l))))
             (set (__ '{:states #{q0 q1 q2 q3 q4}
                        :alphabet #{v w x y z}
                        :start q0
                        :accepts #{q4}
                        :transitions {q0 {v q1, w q1, x q1, y q1, z q1}
                                      q1 {v q2, w q2, x q2, y q2, z q2}
                                      q2 {v q3, w q3, x q3, y q3, z q3}
                                      q3 {v q4, w q4, x q4, y q4, z q4}}}))))
      (is (let [res (take 2000 (__ '{:states #{q0 q1}
                                     :alphabet #{0 1}
                                     :start q0
                                     :accepts #{q0}
                                     :transitions {q0 {0 q0, 1 q1}
                                                   q1 {0 q1, 1 q0}}}))]
            (and (every? (partial re-matches #"0*(?:10*10*)*") res)
                 (= res (distinct res)))))
      (is (let [res (take 2000 (__ '{:states #{q0 q1}
                                     :alphabet #{n m}
                                     :start q0
                                     :accepts #{q1}
                                     :transitions {q0 {n q0, m q1}}}))]
            (and (every? (partial re-matches #"n*m") res)
                 (= res (distinct res)))))
      (is (let [res (take 2000 (__ '{:states #{q0 q1 q2 q3 q4 q5 q6 q7 q8 q9}
                                     :alphabet #{i l o m p t}
                                     :start q0
                                     :accepts #{q5 q8}
                                     :transitions {q0 {l q1}
                                                   q1 {i q2, o q6}
                                                   q2 {m q3}
                                                   q3 {i q4}
                                                   q4 {t q5}
                                                   q6 {o q7}
                                                   q7 {p q8}
                                                   q8 {l q9}
                                                   q9 {o q6}}}))]
            (and (every? (partial re-matches #"limit|(?:loop)+") res)
                 (= res (distinct res))))))))

;; https://www.4clojure.com/problem/79 -- Triangle Minimal Path
;;
;; Difficulty:Hard
;; Topics:graph-theory
;;
;; Write a function which calculates the sum of the minimal path
;; through a triangle. The triangle is represented as a collection of
;; vectors. The path should start at the top of the triangle and move
;; to an adjacent number on the next row until the bottom of the
;; triangle is reached.

(deftest four-clojure-79
  (testing "4Clojure #79"
    (let [__ cc/four-clojure-79]
      (is (= 7 (__ '(   [1]
                       [2 4]
                      [5 1 4]
                     [2 3 4 5])))) ; 1->2->1->3
      (is (= 20 (__ '(     [3]
                          [2 4]
                         [1 9 3]
                        [9 9 2 4]
                       [4 6 6 7 8]
                      [5 7 3 5 1 4]))))))) ; 3->4->3->2->7->1

;; Helper macro for running individual tests from primary namespace
(defmacro run-test [tsym]
  `(binding [*report-counters* (ref *initial-report-counters*)]
     (test-vars [(ns-resolve (find-ns 'collab-2016-02.core-test) '~tsym)])
     @*report-counters*))
