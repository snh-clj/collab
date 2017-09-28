(ns sn.core-test
  (:require [clojure.test :refer :all]
            [sn.core :as sn]))

;; http://www.4clojure.com/problem/95 -- To Tree, or not to tree

;; Difficulty: Easy
;; Topics: trees

;; Special restrictions: none

;; Write a predicate which checks whether or not a given sequence represents a binary tree.
;; Each node in the tree must have a value, a left child, and a right child.
(deftest four-clojure-95-test
  (testing "4Clojure #95"
    (let [__ sn/four-clojure-95]
      (is (= (__ '(:a (:b nil nil) nil))
             true))
      (is (= (__ '(:a (:b nil nil)))
             false))
      (is (= (__ [1 nil [2 [3 nil nil] [4 nil nil]]])
             true))
      (is (= (__ [1 [2 nil nil] [3 nil nil] [4 nil nil]])
             false))
      (is (= (__ [1 [2 [3 [4 nil nil] nil] nil] nil])
             true))
      (is (= (__ [1 [2 [3 [4 false nil] nil] nil] nil])
             false))
      (is (= (__ '(:a nil ()))
             false)))))

;; http://www.4clojure.com/problem/96 -- 

;; Difficulty: Easy
;; Topics: trees

;; Special restrictions: none

;; Let us define a binary tree as "symmetric" if the left half of the
;; tree is the mirror image of the right half of the tree. Write a
;; predicate to determine whether or not a given binary tree is
;; symmetric. (see Problem #95 above for a reminder on the tree
;; representation we're using).
(deftest four-clojure-96-test
  (testing "4Clojure #96"
    (let [__ sn/four-clojure-96]
      (is (= (__ '(:a (:b nil nil) (:b nil nil))) true))
      (is (= (__ '(:a (:b nil nil) nil)) false))
      (is (= (__ '(:a (:b nil nil) (:c nil nil))) false))
      (is (= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [6 nil nil] [5 nil nil]]] nil]])
             true))
      (is (= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [5 nil nil] [6 nil nil]]] nil]])
             false))
      (is (= (__ [1 [2 nil [3 [4 [5 nil nil] [6 nil nil]] nil]]
                  [2 [3 nil [4 [6 nil nil] nil]] nil]])
             false)))))

;; http://www.4clojure.com/problem/55 -- Count Occurrences

;; Difficulty: Medium
;; Topics: seqs core-functions

;; Special restrictions: frequencies

;; Write a function which returns a map containing the number of occurences of each
;; distinct item in a sequence.
(deftest four-clojure-55-test
  (testing "4Clojure #55"
    (let [__ sn/four-clojure-55]
      (is (= (__ [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1}))
      (is (= (__ [:b :a :b :a :b]) {:a 2, :b 3}))
      (is (= (__ '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})))))

;; http://www.4clojure.com/problem/69 -- Merge with a Function

;; Difficulty: Medium
;; Topics: core-functions

;; Special restrictions: merge-with

;; Write a function which takes a function f and a variable number of
;; maps. Your function should return a map that consists of the rest
;; of the maps conj-ed onto the first.  If a key occurs in more than
;; one map, the mapping(s) from the latter (left-to-right) should be
;; combined with the mapping in the result by calling (f val-in-result
;; val-in-latter)
(deftest four-clojure-69-test
  (testing "4Clojure #69"
    (let [__ sn/four-clojure-69]
      (is (= (__ * {:a 2, :b 3, :c 4} {:a 2} {:b 2} {:c 5})
             {:a 4, :b 6, :c 20}))
      (is (= (__ - {1 10, 2 20} {1 3, 2 10, 3 15})
             {1 7, 2 10, 3 15}))
      (is (= (__ concat {:a [3], :b [6]} {:a [4 5], :c [8 9]} {:b [7]})
             {:a [3 4 5], :b [6 7], :c [8 9]}))
      )))

;; http://www.4clojure.com/problem/121 -- Universal Computation Engine

;; Difficulty: Medium
;; Topics: functions

;; Special restrictions: eval, resolve

;; Given a mathematical formula in prefix notation, return a function
;; that calculates the value of the formula. The formula can contain
;; nested calculations using the four basic mathematical operators,
;; numeric constants, and symbols representing variables. The returned
;; function has to accept a single parameter containing the map of
;; variable names to their values.
(deftest four-clojure-121-test
  (testing "4Clojure #121"
    (let [__ sn/four-clojure-121]
      (is (= 2 ((__ '(/ a b))
                '{b 8 a 16})))
      (is (= 8 ((__ '(+ a b 2))
                '{a 2 b 4})))
      (is (= [6 0 -4]
             (map (__ '(* (+ 2 a)
                          (- 10 b)))
                  '[{a 1 b 8}
                    {b 5 a -2}
                    {a 2 b 11}])))
      (is (= 1 ((__ '(/ (+ x 2)
                        (* 3 (+ y 1))))
                '{x 4 y 1})))
      )))

;; http://www.4clojure.com/problem/195 -- Parentheses... Again

;; Difficulty: Medium
;; Topics: math combinatrics

;; Special restrictions: none

;; In a family of languages like Lisp, having balanced parentheses is
;; a defining feature of the language. Luckily, Lisp has almost no
;; syntax, except for these "delimiters" -- and that hardly qualifies
;; as "syntax", at least in any useful computer programming sense.
;;
;; It is not a difficult exercise to find all the combinations of
;; well-formed parentheses if we only have N pairs to work with. For
;; instance, if we only have 2 pairs, we only have two possible
;; combinations: "()()" and "(())". Any other combination of length 4
;; is ill-formed. Can you see why?
;;
;; Generate all possible combinations of well-formed parentheses of
;; length 2n (n pairs of parentheses). For this problem, we only
;; consider '(' and ')', but the answer is similar if you work with
;; only {} or only [].
;;
;; There is an interesting pattern in the numbers!
(deftest four-clojure-195-test
  (testing "4Clojure #195"
    (let [__ sn/four-clojure-195]
      (is (= [#{""} #{"()"} #{"()()" "(())"}] (map (fn [n] (__ n)) [0 1 2])))
      (is (= #{"((()))" "()()()" "()(())" "(())()" "(()())"} (__ 3)))
      (is (= 16796 (count (__ 10))))
      (is (= (nth (sort (filter #(.contains ^String % "(()()()())") (__ 9))) 6) "(((()()()())(())))"))
      (is (= (nth (sort (__ 12)) 5000) "(((((()()()()()))))(()))"))
      )))

