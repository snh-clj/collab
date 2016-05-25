(ns ecapseman.core)

(defn four-clojure-34
  "https://www.4clojure.com/problem/34 -- Implement range

  Difficulty: easy
  Topics: seqs core-functions

  Write a function which creates a list of all integers in a given range.

  Restriction: range"
  [x y]
  (take (- y x) (iterate inc x)))

(defn four-clojure-29
  "https://www.4clojure.com/problem/29 -- Get the Caps

  Difficulty: easy
  Topics: strings

  Write a function which takes a string and returns a new string containing only the capital letters."
  [s]
  (apply str (re-seq #"[A-Z]" s)))

(defn four-clojure-77
  "https://www.4clojure.com/problem/77 -- Anagram Finder

  Difficulty: medium

  Write a function which finds all the anagrams in a vector of
  words. A word x is an anagram of word y if all the letters in x can
  be rearranged in a different order to form y. Your function should
  return a set of sets, where each sub-set is a group of words which
  are anagrams of each other. Each sub-set should have at least two
  words. Words without any anagrams should not be included in the
  result."
  [words]
  ;; Wow... I don't remember how long ago I submitted this to
  ;; 4clojure, but I think it has not been golfed. :-)
  (set
   (map (fn find-1 [[k v]] (set v))
        (filter (fn find-2 [[k v]] (< 1 (count v)))
                ((fn find-3 [words coll]
                   (if (empty? words)
                     coll
                     (let [word (first words)
                           words (rest words)]
                       (if (coll (set word))
                         (recur words (merge-with concat {(set word) [word]} coll))
                         (recur words (assoc coll (set word) [word]))))))
                 words {})))))

(defn project-euler-4-prep
  "Write a predicate function that takes a number and returns a truthy
  value if the number is a palindrome, falsy otherwise.

  Difficulty: medium

  E.g.:
  => (project-euler-4-prep 1221)
  => true
  => (project-euler-4-prep 121)
  => true
  => (project-euler-4-prep 122)
  => false
  "
  [n]
  (loop [n (str n)]
    (if (empty? n)
      true
      (when (= (first n) (last n))
        (recur (butlast (drop 1 n)))))))

(defn project-euler-4
  "https://projecteuler.net/problem=4

  Difficulty: hard

  A palindromic number reads the same both ways. The largest
  palindrome made from the product of two 2-digit numbers is 9009 = 91
  × 99.

  Write a function that calculates the largest palindrome made from
  the product of two 3-digit numbers."
  []
  (reduce max 0 (filter project-euler-4-prep (for [x (range 100 1000)
                                                   y (range 100 1000)]
                                               (* x y)))))

(defn four-clojure-84
  "https://www.4clojure.com/problem/84 -- Transitive Closure

  Difficulty: hard
  Topics: set-theory

  Write a function which generates the transitive closure
  ( http://en.wikipedia.org/wiki/Transitive_closure ) of a binary
  relation ( http://en.wikipedia.org/wiki/Binary_relation ). The
  relation will be represented as a set of 2 item vectors."
  [s]
  (let [m (into {} s)]
     (reduce (fn [r [k v]]
               (let [r (conj r [k v])]
                 (if (m v)
                   (recur r [k (m v)])
                   r)))
             #{}
             m)))

(comment
  (require 'ecapseman.core :reload-all) (in-ns 'ecapseman.core) (use 'clojure.repl)
  )


(defn euler-4-answers [x y]
  (let [palindrome? project-euler-4-prep]
    (condp = [x y]
      ;; 9009
      [2 2]
      (reduce max 0 (filter palindrome? (for [x (range 10 100)
                                              y (range 10 100)]
                                          (* x y))))
      ;; 828828 .5s
      [3 2]
      (reduce max 0 (filter palindrome? (for [x (range 10 100)
                                              y (range 10 100)
                                              z (range 10 100)]
                                          (* x y z))))

      ;; 67144176 52s
      [4 2]
      (reduce max 0 (filter palindrome? (for [x (range 10 100)
                                              y (range 10 100)
                                              z (range 10 100)
                                              a (range 10 100)
                                              ]
                                          (* x y z a))))

      ;; 906609
      [2 3]
      (reduce max 0 (filter palindrome? (for [x (range 100 1000)
                                              y (range 100 1000)]
                                          (* x y))))

      ;; 99000099 60s
      [2 4]
      (reduce max 0 (filter palindrome? (for [x (range 1000 10000)
                                              y (range 1000 10000)]
                                          (* x y))))

      [3 3]
      (reduce max 0 (filter palindrome? (for [x (range 100 1000)
                                              y (range 100 1000)
                                              z (range 100 1000)
                                              ]
                                          (* x y z))))

      [4 3]
      (reduce max 0 (filter palindrome? (for [x (range 100 1000)
                                              y (range 100 1000)
                                              z (range 100 1000)
                                              a (range 100 1000)
                                              ]
                                          (* x y z a))))


      )))

