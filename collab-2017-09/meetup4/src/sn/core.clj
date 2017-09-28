(ns sn.core)

(defn four-clojure-95
  "http://www.4clojure.com/problem/95 -- To Tree, or not to tree

  Difficulty: easy
  Topics: trees

  Write a predicate which checks whether or not a given sequence represents a binary tree. Each node in the tree must have a value, a left child, and a right child.
  (binary tree: https://en.wikipedia.org/wiki/Binary_tree)"
  [s]
  (cond (nil? s) true
        (and (coll? s)
             (= (count s) 3)
             (four-clojure-95 (second s))
             (four-clojure-95 (nth s 2))) true
        :else false))

(defn four-clojure-96
  "http://www.4clojure.com/problem/96 -- Beauty is Symmetry

  Difficulty: Easy
  Topics: trees

  Special restrictions: none

  Let us define a binary tree as 'symmetric' if the left half of the
  tree is the mirror image of the right half of the tree. Write a
  predicate to determine whether or not a given binary tree is
  symmetric. (see Problem #95 above for a reminder on the tree
  representation we're using)."
  [t]
  ::no-implementation)

(defn four-clojure-55
  "http://www.4clojure.com/problem/55 -- Count Occurrences

  Difficulty: Medium
  Topics: functions

  Special restrictions: frequencies

  Write a function which returns a map containing the number of occurences of each
  distinct item in a sequence."
  [s]
  ::no-implementation)

(defn four-clojure-69
  "http://www.4clojure.com/problem/69 -- Merge with a Function

  Difficulty: Medium
  Topics: core-functions

  Special restrictions: merge-with


  Write a function which takes a function f and a variable number of
  maps. Your function should return a map that consists of the rest
  of the maps conj-ed onto the first.  If a key occurs in more than
  one map, the mapping(s) from the latter (left-to-right) should be
  combined with the mapping in the result by calling (f val-in-result
  val-in-latter)"
  [f & maps]
  ::no-implementation)

(defn four-clojure-121
  "http://www.4clojure.com/problem/121 -- Universal Computation Engine

  Difficulty: Medium
  Topics: functions

  Special restrictions: eval, resolve

  Given a mathematical formula in prefix notation, return a function
  that calculates the value of the formula. The formula can contain
  nested calculations using the four basic mathematical operators,
  numeric constants, and symbols representing variables. The returned
  function has to accept a single parameter containing the map of
  variable names to their values."
  [formula]
  ::no-implementation)

(defn four-clojure-195
  "http://www.4clojure.com/problem/195 -- Parentheses... Again

  Difficulty: Medium
  Topics: math combinatrics

  Special restrictions: none

  In a family of languages like Lisp, having balanced parentheses is
  a defining feature of the language. Luckily, Lisp has almost no
  syntax, except for these 'delimiters' -- and that hardly qualifies
  as 'syntax', at least in any useful computer programming sense.

  It is not a difficult exercise to find all the combinations of
  well-formed parentheses if we only have N pairs to work with. For
  instance, if we only have 2 pairs, we only have two possible
  combinations: '()()' and '(())'. Any other combination of length 4
  is ill-formed. Can you see why?

  Generate all possible combinations of well-formed parentheses of
  length 2n (n pairs of parentheses). For this problem, we only
  consider '(' and ')', but the answer is similar if you work with
  only {} or only [].

  There is an interesting pattern in the numbers!"
  [n]
  ::no-implementation)
