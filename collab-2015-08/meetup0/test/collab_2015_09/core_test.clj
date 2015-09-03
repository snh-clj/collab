(ns collab-2015-09.core-test
  (:require [clojure.test :refer :all]
            [collab-2015-09.core :refer :all]))

(deftest mapl-tests
  (is (= (mapl inc [1 2 3 4])
         (map inc [1 2 3 4])))
  (is (= (mapl reverse ["this" "is" "only" "a" "test"])
         (map reverse ["this" "is" "only" "a" "test"]))))


(deftest filterl-tests
  (is (= (filterl even? (range 50))
         (filter even? (range 50))))
  (is (= (filterl keyword? [1 :one "one" 'one 2 :two "two" 'two])
         (filter keyword? [1 :one "one" 'one 2 :two "two" 'two]))))

(deftest mapcatl-tests
  (is (= (mapcatl (partial map #(* % %)) [(range 7) (range 3)])
         (mapcat (partial map #(* % %)) [(range 7) (range 3)]))))
