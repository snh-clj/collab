(ns bonus-round.core-test
  (:require [clojure.test :refer :all]
            [bonus-round.core :as br :refer :all]
            :reload))

#_
(deftest four-clojure-178-test
  (testing "4Clojure #178"
    (let [__ br/four-clojure-178]
      (is (= :high-card (__ ["HA" "D2" "H3" "C9" "DJ"])))
      (is (= :pair (__ ["HA" "HQ" "SJ" "DA" "HT"])))
      (is (= :two-pair (__ ["HA" "DA" "HQ" "SQ" "HT"])))
      (is (= :three-of-a-kind (__ ["HA" "DA" "CA" "HJ" "HT"])))
      (is (= :straight (__ ["HA" "DK" "HQ" "HJ" "HT"])))
      (is (= :straight (__ ["HA" "H2" "S3" "D4" "C5"])))
      (is (= :flush (__ ["HA" "HK" "H2" "H4" "HT"])))
      (is (= :full-house (__ ["HA" "DA" "CA" "HJ" "DJ"])))
      (is (= :four-of-a-kind (__ ["HA" "DA" "CA" "SA" "DJ"])))
      (is (= :straight-flush (__ ["HA" "HK" "HQ" "HJ" "HT"]))))))

(deftest magic-squares-test
  (testing "3x3 sum 15 Magic Square..."
    (let [result (br/magic-squares)]
      (testing "Result must be a sequence of sequences each of three sequences of three numbers, 1-9."
        (testing "Result is a sequence"
          (is (sequential? result))
          (is (sequential? (first result)))
          (is (every? sequential? (first result)))
          (is (= 3 (count (first result))))
          (is (every? (comp (partial = 3) count) (first result)))))
      (let [[[a b c] [d e f] [g h i]] (first result)]
        (testing "All squares must be different"
          (is (distinct? a b c d e f g h i)))
        (testing "All squares must be in the range of 1-9"
          (is (every? #(<= 1 % 9) [a b c d e f g h i])))
        (testing "Rows sum to 15"
          (is (= 15 (+ a b c)))
          (is (= 15 (+ d e f)))
          (is (= 15 (+ g h i))))
        (testing "Columns sum to 15"
          (is (= 15 (+ a d g)))
          (is (= 15 (+ b e h)))
          (is (= 15 (+ c f i))))
        (testing "Diagonals sum to 15"
          (is (= 15 (+ a e i)))
          (is (= 15 (+ c e g))))))))

(deftest test-problem-get-3
  (testing "problem-get-3"
    (is (= '(3) (problem-get-3)))))

(deftest test-problem-membero-2-3-4
  (testing "problem-membero-2-3-4"
    (is (= '(2 3 4) (problem-membero-2-3-4)))))

(deftest test-problem-anja-orange
  (testing "problem-anja-orange"
    (is (= '(:orange) (problem-anja-orange)))))

(deftest test-problem-find-my-mother
  (testing "problem-find-my-mother"
    (is (= #{["Fran"]} (problem-find-my-mother "Mari")))
    (is (= #{["Mari"]} (problem-find-my-mother "Bob")))
    (is (= #{["Mari"]} (problem-find-my-mother "Alice")))))

(deftest test-problem-find-my-father
  (testing "problem-find-my-father"
    (is (= '("Frank") (problem-find-my-father "Mart")))
    (is (= '("Mart") (problem-find-my-father "Bob")))
    (is (= '("Mart") (problem-find-my-father "Alice")))))

(deftest test-magic-squares
  (testing "3x3 sum 15 Magic Square..."
    (let [result (magic-squares)]
      (testing "Result must be a sequence of sequences each of three sequences of three numbers, 1-9."
        (testing "Result is a sequence"
          (is (sequential? result))
          (is (sequential? (first result)))
          (is (every? sequential? (first result)))
          (is (= 3 (count (first result))))
          (is (every? (comp (partial = 3) count) (first result)))))
      (let [[[a b c] [d e f] [g h i]] (first result)]
        (testing "All squares must be different"
          (is (distinct? a b c d e f g h i)))
        (testing "All squares must be in the range of 1-9"
          (is (every? #(<= 1 % 9) [a b c d e f g h i])))
        (testing "Rows sum to 15"
          (is (= 15 (+ a b c)))
          (is (= 15 (+ d e f)))
          (is (= 15 (+ g h i))))
        (testing "Columns sum to 15"
          (is (= 15 (+ a d g)))
          (is (= 15 (+ b e h)))
          (is (= 15 (+ c f i))))
        (testing "Diagonals sum to 15"
          (is (= 15 (+ a e i)))
          (is (= 15 (+ c e g))))))))

(deftest test-triangulizer
  (testing "Triangulizer"
    (is (= nil (triangulizer 1 2 3)))
    (is (= '([3 4 5]) (triangulizer 3 4 nil)))
    (is (= '([3 4 5]) (triangulizer 3 nil 5)))
    (is (= '([3 4 5]) (triangulizer 3 nil 5)))
    (is (= '([3 4 5]) (triangulizer 3 nil 5)))
    (is (= '([8 15 17]) (triangulizer nil nil 17)))
    (is (= '([7 24 25] [15 20 25]) (triangulizer nil nil 25)))))

(comment
  ;; You can run individual tests like this at the repl:
  ;;
  ;; (Note: You'll need to save and re-compile your
  ;; relatable-logic.core and test namespaces if you've made any
  ;; changes.)
  (clojure.test/test-var #'relatable-logic.core-test/test-problem-anja-orange)
  (clojure.test/test-var #'relatable-logic.core-test/test-triangulizer)
  )
