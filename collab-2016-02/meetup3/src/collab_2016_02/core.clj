(ns collab-2016-02.core
  (:require [clojure.walk :as walk]))

;; Solutions by: <individual or group names here>

;; Clojure reference:
;; http://ClojureDocs.org

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

(defn four-clojure-141 [trump-suit]
  ::no-implementation)

;; https://www.4clojure.com/problem/158 -- Decurry
;;
;; Difficulty:Medium
;; Topics:partial-functions
;;
;; Write a function that accepts a curried function of unknown arity n.
;; Return an equivalent function of n arguments.
;;
;; You may wish to read http://en.wikipedia.org/wiki/Currying

(defn full-name [l f m]
  (str l ", " f " " m))

(defn f-m [f m]
  (fn [l] (str l ", " f " " m)))

(defn four-clojure-158 [curried-fns]
  (fn [& args]
    (loop [result curried-fns
           args args]
      (if (empty? args)
        result
        (recur (result (first args))
               (rest args)))))
  #_
  (fn four-clojure-158- [& args]
    (prn args)
    (let [result (curried-fns (first args))]
      (if (next args)
        (result (next args))
        result))
    (comment
      (curried-fns (first args))
      )

    ))

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

(defn four-clojure-164 [dfa]
  ::no-implementation)

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

(defn four-clojure-79
  ([triangle] (four-clojure-79 triangle 0 0))
  ([triangle current-index current-sum]
     (if (empty? triangle)
       current-sum
       (min (four-clojure-79 (rest triangle)
                             current-index
                             (+ current-sum (nth (first triangle) current-index)))
            (four-clojure-79 (rest triangle)
                             (inc current-index)
                             (+ current-sum (nth (first triangle) current-index)))))))

(comment
        [   [1]
            [2 4]
            [5 1 4]
            [2 3 4 5]]

        [[1]
         [2 4]
         [5 1 4]
         [2 3 4 5]]

        [[0 0 0 0]
         [0 0 0 1]
         [0 0 1 1]
         [0 0 1 2]
         [0 1 1 1]
         [0 1 1 2]
         [0 1 2 2]
         [0 1 2 3]]

        {[3 2 1 9 4 5] 24}
        )

(comment
  ;; Run this first
  (require '[collab-2016-02.core-test :as test])

  ;; To run all tests:
  (clojure.test/test-ns 'collab-2016-02.core-test)

  ;; To run individual test:
  (test/run-test four-clojure-141)
  (test/run-test four-clojure-164)
  (test/run-test four-clojure-158)
  (test/run-test four-clojure-79)
)
