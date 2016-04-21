(ns progroblems.core)

(defn four-clojure-33
  "http://www.4clojure.com/problem/33 -- Replicate a Sequence

  Difficulty: easy
  Topics: seqs

  Write a function which replicates each element of a sequence a variable number of times."
  [coll n]
  (mapcat #(repeat n %) coll))


(defn four-clojure-38
  "http://www.4clojure.com/problem/38 -- Maximum Value

  Difficulty: easy
  Topics: core-functions

  Write a function which takes a variable number of parameters and returns the maximum value."
  [& args]
  (loop [remaining-items args
         max-value nil]
    (if (empty? remaining-items)
      max-value
      (recur
        (rest remaining-items)
        (if (or
              (nil? max-value)
              (> (first remaining-items) max-value))
          (first remaining-items) max-value)))))

(defn some-function
  [max-value args]
  (if (empty? args) max-value
    #(some-function 
       (if
         (or
           (nil? max-value)
           (> (first args) max-value))  
         (first args) max-value)
       (rest args))))

(defn four-clojure-38-trampoline
  [& args]
  (trampoline some-function nil args))

(comment
  
  (time (apply four-clojure-38 (take 10000 (repeatedly #(rand-int 1000)))))
  (time (apply four-clojure-38-trampoline (take 10000 (repeatedly #(rand-int 1000)))))

  )


(def four-clojure-76
  "http://www.4clojure.com/problem/76 -- Intro to Trampoline

  Difficulty: medium
  Topics: recursion

  The trampoline function takes a function f and a variable number of
  parameters. Trampoline calls f with any parameters that were
  supplied. If f returns a function, trampoline calls that function
  with no arguments. This is repeated, until the return value is not a
  function, and then trampoline returns that non-function value. This
  is useful for implementing mutually recursive algorithms in a way
  that won't consume the stack."
  ::no-implementation)

(defn four-clojure-78
  "http://www.4clojure.com/problem/78 -- Reimplement Trampoline

  Difficulty: medium
  Topics: core-functions

  Reimplement the function described [above] in \"Intro to Trampoline\"."
  [f & args]
  ::no-implementation)

(defn four-clojure-119
  "http://www.4clojure.com/problem/119 -- Win at Tic-Tac-Toe

  Difficulty: hard
  Topics: game

  As in Problem 73, a tic-tac-toe board is represented by a two
  dimensional vector. X is represented by :x, O is represented by
  :o,and empty is represented by :e. Create a function that accepts a
  game piece and board as arguments, and returns a set (possibly
  empty) of all valid board placements of the game piece which would
  result in an immediate win.

  Board coordinates should be as in calls to get-in. For example,
  [0 1] is the topmost row, center position."
  [game-piece board]
  ::no-implementation)

(defn four-clojure-111
  "http://www.4clojure.com/problem/111 -- Crossword puzzle!

  Difficulty: hard
  Topics: game

  Write a function that takes a string and a partially-filled
  crossword puzzle board, and determines if the input string can be
  legally placed onto the board.

  The crossword puzzle board consists of a collection of
  partially-filled rows. Empty spaces are denoted with an underscore
  (_), unusable spaces are denoted with a hash symbol (#), and
  pre-filled spaces have a character in place; the whitespace
  characters are for legibility and should be ignored.

  For a word to be legally placed on the board:
  - It may use empty spaces (underscores)
  - It may use but must not conflict with any pre-filled characters.
  - It must not use any unusable spaces (hashes).
  - There must be no empty spaces (underscores) or extra characters
    before or after the word (the word may be bound by unusable spaces
    though).
  - Characters are not case-sensitive.
  - Words may be placed vertically (proceeding top-down only), or
    horizontally (proceeding left-right only)."
  [s board]
  ::no-implementation)
