(ns sn.core)

(defn four-clojure-26
  "http://www.4clojure.com/problem/26 -- Fibonacci Sequence

  Difficulty: easy
  Topics: Fibonacci seqs

  Write a function which returns the first X fibonacci numbers."
  [x]
  ::no-implementation)

(defn four-clojure-30
  "http://www.4clojure.com/problem/30 -- Compress a Sequence
  Difficulty: easy
  Topics: seqs

  Write a function which removes consecutive duplicates from a sequence."
  [coll]

  (comment 
    (def ex-1 [1 2 2 3 3 3 4 4 4 4 5 5 5 5 5])
    (def ex-2 [nil nil 1 2 2 1 1 1 nil nil 2 1 2])
    )

  (let [my-fn (fn [a b]
               (if (empty? a)
                 (conj a b)
                 (let [c (last a)]
                   (if (= c b)
                     a
                     (conj a b)))))]
    (reduce my-fn [] coll)))

(defn four-clojure-53
  "http://www.4clojure.com/problem/53 -- Longest Increasing Sub-Seq

  Difficulty: hard
  Topics: seqs

  Given a vector of integers, find the longest consecutive
  sub-sequence of increasing numbers. If two sub-sequences have the
  same length, use the one that occurs first. An increasing
  sub-sequence must have a length of 2 or greater to qualify."
  [coll]
  ::no-implementation)

(defn four-clojure-54
  "http://www.4clojure.com/problem/54 -- Partition a Sequence

  Difficulty: medium
  Topics: seqs core-functions

  Write a function which returns a sequence of lists of x items
  each. Lists of less than x items should not be returned."
  [x coll]
  ::no-implementation)

(defn four-clojure-60
  "http://www.4clojure.com/problem/60 -- Sequence Reductions

  Difficulty: medium
  Topics: seqs core-functions

  Write a function which behaves like reduce, but returns each
  intermediate value of the reduction. Your function must accept
  either two or three arguments, and the return sequence must be
  lazy."
  ([f coll] 
     (if-let [s (seq coll)] 
      (four-clojure-60 f (first s) (rest s))

  ([f init coll] 
    (let [s (seq coll)
          x (f init (first s))]
      (list x
        (four-clojure-60 f x (rest s))))))

(defn four-clojure-73
  "http://www.4clojure.com/problem/73 -- Analyze a Tic-Tac-Toe Board

  Difficulty: hard
  Topics: game

  A tic-tac-toe board is represented by a two dimensional vector. X is
  represented by :x, O is represented by :o, and empty is represented
  by :e. A player wins by placing three Xs or three Os in a
  horizontal, vertical, or diagonal row. Write a function which
  analyzes a tic-tac-toe board and returns :x if X has won, :o if O
  has won, and nil if neither player has won." 
  [board]
  ::no-implementation)
