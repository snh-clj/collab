(ns meet-test-check.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [meet-test-check.core :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]
            [com.gfredericks.test.chuck :as chuck]
            [com.gfredericks.test.chuck.properties :as prop'])
  (:import [clojure.test.check.generators Generator]))

(def replace-me-gen
  (Generator. (fn [& _] (throw (Exception. "Replace this generator.")))))

(def test-integer-gen
  "A generator returning integers."
  replace-me-gen)

(defspec test-bad-fn-1
  100000
  (prop/for-all [i test-integer-gen]
                (try
                  (let [x (bad-fn-1 i)]
                    (or (integer? x) (ratio? x)))
                  (catch Throwable t
                    false))))

(defspec test-bad-fn-2
  1000
  (prop/for-all [s gen/string-alphanumeric]
                ;; Write property checking for string to not contain "Q".
                false ; <- replace me
                ))

(def non-4-int-gen
  "A generator returning all integers not including 4."
  replace-me-gen)

(defspec test-bad-fn-3
  1000
;; Assure that bad-fn-3 always returns a clojure.lang.Ratio for all integers, not including 4.
  (prop/for-all [n non-4-int-gen]
                ;; If this triggers, your generator is wrong.
                (is (not= n 4))
                ;; If this triggers, you've found the problem with bad-fn-3
                (is (= clojure.lang.Ratio
                       (type (bad-fn-3 n))))))


;; Spongebob Granddad

(def sponge-child-parent-gen
  ;; A generator returning pairs of natural numbers.
  replace-me-gen)

(defn has-loop?
  "Searches for loops in the ancestry starting with start-child.
  Returns true if a loop is found, false otherwise."
  [ancestry start-child]
  (loop [this-child start-child visited? #{start-child}]
    (if-let [next-child (get ancestry this-child)]
      (if (visited? next-child)
        ;; We found a loop
        true
        (recur next-child (conj visited? this-child)))
      ;; No loop found
      false)))

(defspec spongebob-granddad-test
  100
  (prop/for-all [ancestors (gen/vector sponge-child-parent-gen)]
                (let [ancestry (reduce add-sponge-ancestry {} ancestors)]
                  (every? (partial (complement has-loop?) ancestry)
                          (keys ancestry)))))

;; Rich Hickey's Ark

(def animal-pairs-gen
  "Generates vector pairs of animals."
  (->
   (gen/elements hickeys-animal-types)
   (gen/bind #(gen/return [% %]))))

(defspec hickeys-ark-test
  100
  (prop/for-all [pairs (gen/vector animal-pairs-gen)]
                ;; Write a property checking that there are only 2 of each kind on the ark.
                false)) ; <- replace me

(defn animal-shipments-gen-fn
  "A function taking a series of animal arguments returning a generator of:
   [[[animal1]],[[animal2][animal2]],[[animal1][animal1][animal2]]]

  An animal is in a vector cage.
  A shipment is a vector 1-3 animals in cages.
  A shipments list is a vector of shipments. "
  [& animals]
  replace-me-gen)

(defspec zoo-test
  100
  (prop/for-all [shipments (animal-shipments-gen-fn :tiger :bunny)]
                ;; for every type of animal
                (every? (fn [[animal-type animal-pen]]
                          ;; Every animal in that pen should be the
                          ;; right type of animal
                          (every? #{animal-type} @animal-pen))
                        (let [pens {:tiger (atom [])
                                    :bunny (atom [])}]
                          (zoo pens shipments)))))

(comment
  ;; REPL-loading line
  (do
    (require 'meet-test-check.core-test :reload-all)
    (in-ns 'meet-test-check.core-test)
    (use 'clojure.repl))

  ;; test some generators in the REPL:
  (gen/sample gen/int)
  (gen/sample gen/int 3)
  (gen/sample (gen/vector gen/keyword 2))
  (gen/sample (gen/tuple gen/int gen/char-alphanumeric gen/keyword))
  (gen/sample gen-palette)
  )
