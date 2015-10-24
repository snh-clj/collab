(ns collab-2015-10.core-test
  (:require [clojure.test :refer :all]
            [collab-2015-10.core :refer [my-eval]]))

(deftest numbers-test
  (testing "Numbers eval to themselves"
    (is (= 1
           (my-eval {} 1)))))

(deftest strings-test
  (testing "Strings eval to themselves"
    (is (= "hello"
           (my-eval {} "hello")))))

(deftest symbols-test-1
  (testing "Symbols eval to their value in the environment"
    (is (= 10
           (my-eval {'x 10} 'x)))))

(deftest symbols-test-2
  (testing "Symbols eval to their value in the environment"
    (is (= "yo!"
           (my-eval {'x "yo!"} 'x)))))

(deftest function-test-1
  (testing "Functions evaluate to themselves"
    (is (= +
           (my-eval {} +)))))

(deftest function-test-2
  (testing "Functions evaluate to themselves"
    (is (= *
           (my-eval {} *)))))

(deftest if-nil-test
  (testing "If statements with nil test eval else"
    (is (= 1
           (my-eval {} '(if nil 0 1))))))

(deftest if-non-nil-test
  (testing "If statements with non-nil test eval then"
    (is (= 0
           (my-eval {'test 1 'x 0 'y 1} '(if test x y))))))

(deftest empty-do-test
  (testing "Empty do statement evals to nil"
    (is (= nil
           (my-eval {} '(do))))))

(deftest one-do-test
  (testing "One-expression do evals to expression"
    (is (= 1
           (my-eval {} '(do 1))))))

(deftest last-do-test
  (testing "Multi-expression do evals to last expression"
    (is (= 4
           (my-eval {} '(do 1 2 3 4))))))

(deftest let-test
  (testing "Let statements add to environment"
    (is (= 1
           (my-eval {} '(let [x 1] x))))))

(deftest function-application-test-1
  (testing "Function application"
    (is (= 2
           (my-eval {'+ +} '(+ 1 1))))))

(deftest function-application-test-2
  (testing "Function application"
    (is (= 5
           (my-eval {'/ /} '(/ 20 4))))))

(deftest recursive-test-1
  (testing "Ensure we're calling my-eval recursively"
    (is (= 3
           (my-eval {'+ +} '(do (+ 1 2)))))))

(deftest recursive-test-2
  (testing "Ensure we're calling my-eval recursively"
    (is (= 13
           (my-eval {'+ +} '(+ (+ 1 10) 2))))))

(deftest do-all-test
  (testing "Do expressions evaluate all forms"
    (is (= 85
           (my-eval {'deref deref 'reset! reset! 'a (atom nil) '+ +}
           '(do (reset! a 10) (+ (deref a) 75)))))))
