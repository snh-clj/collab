(ns collab-2015-10.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [collab-2015-10.core :refer [my-eval]]))

(def test-cases
  '[["Numbers eval to themselves" (my-eval {} 1) 1]
    ["Strings eval to themselves" (my-eval {} "hello") "hello"]
    ["Symbols eval to their value in the environment" (my-eval {'x 10} 'x) 10]
    ["Symbols eval to their value in the environment" (my-eval {'x "yo!"} 'x) "yo!"]
    ["Functions evaluate to themselves" (my-eval {} +) +]
    ["Functions evaluate to themselves" (my-eval {} *) *]
    ["If statements with nil test eval else" (my-eval {} '(if nil 0 1)) 1]
    ["If statements with non-nil test eval then" (my-eval {'test 1, 'x 0, 'y 1} '(if test x y)) 0]
    ["Empty do statement evals to nil" (my-eval {} '(do)) nil]
    ["One-expression do evals to expression" (my-eval {} '(do 1)) 1]
    ["Multi-expression do evals to last expression" (my-eval {} '(do 1 2 3 4)) 4]
    ["Let statements add to environment" (my-eval {} '(let [x 1] x)) 1]
    ["Function application" (my-eval {'+ +} '(+ 1 1)) 2]
    ["Function application" (my-eval {'/ /} '(/ 20 4)) 5]
    ["Ensure we're calling my-eval recursively" (my-eval {'+ +} '(do (+ 1 2))) 3]
    ["Ensure we're calling my-eval recursively" (my-eval {'+ +} '(+ (+ 1 10) 2)) 13]
    ["Do expressions evaluate all forms" (my-eval {'deref deref, 'reset! reset!, 'a (atom nil), '+ +} '(do (reset! a 10) (+ (deref a) 75))) 85]])

(doseq [[s f r n] (map conj test-cases (range))]
    (eval
     `(deftest ~(symbol (str "test-" (inc n)))
       (testing ~s
         (eval (is (= ~r ~f)))))))

(defn run-tests []
  (loop [tests (map conj test-cases (range))]
    (if tests
      (let [[test & next-tests] tests
            [s f r n] test
            result (eval `~f)]
        (print  "Test" (inc n) "-" (str s ": "))
        (if (= (eval r) result)
          (do
            (println "PASSED")
            (recur next-tests))
          (do
            (println "FAILED")
            (println "  We ran:" f)
            (println "    expecting:" r)
            (println "    but got:" result))))
      (println "All tests passed: YAY!"))))
