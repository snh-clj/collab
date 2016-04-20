(ns progroblems.core-test
  (:require [clojure.test :refer :all]
            [progroblems.core :as pc]))

;; http://www.4clojure.com/problem/33 -- Replicate a Sequence
;;
;; Difficulty: easy
;; Topics: seqs
;;
;; Write a function which replicates each element of a sequence a variable number of times.
(deftest four-clojure-33-test
  (testing "4Clojure #33"
    (let [__ pc/four-clojure-33]
      (is (= (__ [1 2 3] 2) '(1 1 2 2 3 3)))
      (is (= (__ [:a :b] 4) '(:a :a :a :a :b :b :b :b)))
      (is (= (__ [4 5 6] 1) '(4 5 6)))
      (is (= (__ [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4])))
      (is (= (__ [44 33] 2) [44 44 33 33])))))


;; http://www.4clojure.com/problem/38 -- Maximum Value
;;
;; Difficulty: easy
;; Topics: core-functions
;;
;; Write a function which takes a variable number of parameters and returns the maximum value.
(deftest four-clojure-38-test
  (testing "4Clojure #38"
    (let [__ pc/four-clojure-38]
      (is (= (__ 1 8 3 4) 8))
      (is (= (__ 30 20) 30))
      (is (= (__ 45 67 11) 67)))))


;; http://www.4clojure.com/problem/76 -- Intro to Trampoline

;; Difficulty: medium
;; Topics: recursion

;; The trampoline function takes a function f and a variable number of
;; parameters. Trampoline calls f with any parameters that were
;; supplied. If f returns a function, trampoline calls that function
;; with no arguments. This is repeated, until the return value is not
;; a function, and then trampoline returns that non-function
;; value. This is useful for implementing mutually recursive
;; algorithms in a way that won't consume the stack.
(deftest four-clojure-76-test
  (testing "4Clojure #76"
    (let [__ pc/four-clojure-76]
      (is (= __
             (letfn
                 [(foo [x y] #(bar (conj x y) y))
                  (bar [x y] (if (> (last x) 10)
                               x
                               #(foo x (+ 2 y))))]
               (trampoline foo [] 1)))))))


;; http://www.4clojure.com/problem/78 -- Reimplement Trampoline

;; Difficulty: medium
;; Topics: core-functions

;; Reimplement the function described [above] in "Intro to Trampoline".
(deftest four-clojure-78-test
  (testing "4Clojure #78"
    (let [__ pc/four-clojure-78]
      (is (= (letfn [(triple [x] #(sub-two (* 3 x)))
                     (sub-two [x] #(stop? (- x 2)))
                     (stop? [x] (if (> x 50) x #(triple x)))]
               (__ triple 2))
             82))
      (is (= (letfn [(my-even? [x] (if (zero? x) true #(my-odd? (dec x))))
                     (my-odd? [x] (if (zero? x) false #(my-even? (dec x))))]
               (map (partial __ my-even?) (range 6)))
             [true false true false true false])))))


;; http://www.4clojure.com/problem/119 -- Win at Tic-Tac-Toe
;;
;; Difficulty: hard
;; Topics: game
;;
;; As in Problem 73, a tic-tac-toe board is represented by a two
;; dimensional vector. X is represented by :x, O is represented by :o,
;; and empty is represented by :e. Create a function that accepts a
;; game piece and board as arguments, and returns a set (possibly
;; empty) of all valid board placements of the game piece which would
;; result in an immediate win.
;;
;; Board coordinates should be as in calls to get-in. For example,
;; [0 1] is the topmost row, center position.
(deftest four-clojure-119-test
  (testing "4Clojure #119"
    (let [__ pc/four-clojure-119]
      (is (= (__ :x [[:o :e :e]
                     [:o :x :o]
                     [:x :x :e]])
             #{[2 2] [0 1] [0 2]}))
      (is (= (__ :x [[:x :o :o]
                     [:x :x :e]
                     [:e :o :e]])
             #{[2 2] [1 2] [2 0]}))
      (is (= (__ :x [[:x :e :x]
                     [:o :x :o]
                     [:e :o :e]])
             #{[2 2] [0 1] [2 0]}))
      (is (= (__ :x [[:x :x :o]
                     [:e :e :e]
                     [:e :e :e]])
             #{}))
      (is (= (__ :o [[:x :x :o]
                     [:o :e :o]
                     [:x :e :e]])
             #{[2 2] [1 1]})))))


;; http://www.4clojure.com/problem/111 -- Crossword puzzle!
;;
;; Difficulty: hard
;; Topics: game
;;
;; Write a function that takes a string and a partially-filled crossword puzzle board, and determines if the input string can be legally placed onto the board.
;;
;; The crossword puzzle board consists of a collection of partially-filled rows. Empty spaces are denoted with an underscore (_), unusable spaces are denoted with a hash symbol (#), and pre-filled spaces have a character in place; the whitespace characters are for legibility and should be ignored.
;;
;; For a word to be legally placed on the board:
;; - It may use empty spaces (underscores)
;; - It may use but must not conflict with any pre-filled characters.
;; - It must not use any unusable spaces (hashes).
;; - There must be no empty spaces (underscores) or extra characters before or after the word (the word may be bound by unusable spaces though).
;; - Characters are not case-sensitive.
;; - Words may be placed vertically (proceeding top-down only), or horizontally (proceeding left-right only).
(deftest four-clojure-111-test
  (testing "4Clojure #111"
    (let [__ pc/four-clojure-111]
      (is (= true  (__ "the" ["_ # _ _ e"])))
      (is (= false (__ "the" ["c _ _ _"
                              "d _ # e"
                              "r y _ _"])))
      (is (= true  (__ "joy" ["c _ _ _"
                              "d _ # e"
                              "r y _ _"])))
      (is (= false (__ "joy" ["c o n j"
                              "_ _ y _"
                              "r _ _ #"])))
      (is (= true  (__ "clojure" ["_ _ _ # j o y"
                                  "_ _ o _ _ _ _"
                                  "_ _ f _ # _ _"]))))))
