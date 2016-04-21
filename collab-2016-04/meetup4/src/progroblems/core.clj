(ns progroblems.core)

(defn four-clojure-33
  "http://www.4clojure.com/problem/33 -- Replicate a Sequence

  Difficulty: easy
  Topics: seqs

  Write a function which replicates each element of a sequence a variable number of times."
  [coll n]
  ::no-implementation)


(defn four-clojure-38
  "http://www.4clojure.com/problem/38 -- Maximum Value

  Difficulty: easy
  Topics: core-functions

  Write a function which takes a variable number of parameters and returns the maximum value."
  [& args]
  ::no-implementation)


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
  :o, and empty is represented by :e. Create a function that accepts a
  game piece and board as arguments, and returns a set (possibly
  empty) of all valid board placements of the game piece which would
  result in an immediate win.

  Board coordinates should be as in calls to get-in. For example,
  [0 1] is the topmost row, center position.

  * collect the coordinates of empty positions
  * test each coordinate pair for a win
  * we need a winning? fn

"
  [player board]
  (let [rotate-board (fn [board] (apply map vector (reverse board)))
        get-diagonal (fn [board] (map-indexed #(nth %2 %1) board))
        winning-row? (fn [player row] (= #{player} (into #{} row)))
        winner? (fn [player board]
                  (let [rotated-board (rotate-board board)
                        diagonals [(get-diagonal board)
                                   (get-diagonal rotated-board)]
                        all-rows (concat board rotated-board diagonals)]
                    (some (partial winning-row? player) all-rows)))
        board-size (count board) ; ASSUMPTION: square boards only!
        ]
    (apply hash-set
           (filter identity
                   (for [row-coordinate (range board-size)
                         col-coordinate (range board-size)]
                     (let [coordinates [row-coordinate col-coordinate]
                           coordinate-value (get-in board coordinates)]
                       (when (= coordinate-value :e)
                         (when (winner? player
                                        (assoc-in board coordinates player))
                           coordinates))))))))

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
