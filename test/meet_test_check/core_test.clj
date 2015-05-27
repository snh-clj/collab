(ns meet-test-check.core-test
  (:require [clojure.test :refer :all]
            [clojure.string :as str]
            [meet-test-check.core :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [clojure.test.check.clojure-test :refer [defspec]]))

(def gen-string
  ;; See gen/string-alphanumeric ... and use it instead. ;)
  ;; But this shows how a still-simple generator can be constructed
  ;; out of simple parts.
  (gen/fmap str/join (gen/vector gen/char-alphanumeric)))

(defspec test-bad-fn-2
  10000
  ;; This fn and test are silly, but demonstrate test.check shrinking.
  ;; #_ it out and move on after you understand it.
  (prop/for-all [s gen-string]
                (= (count s) (count (bad-fn-2 s)))))

(defspec test-bad-fn-3
  100000
  ;; 'bad-fn-3 has a bug and should be fixed
  (prop/for-all [i gen/int]
                (let [x (bad-fn-3 i)]
                  (or (integer? x) (ratio? x)))))

(defspec test-bad-fn-4
  1000
  ;; 'bad-fn-4 is fine, but this test is based on the incorrect
  ;; 'bad-fn-4 docstring
  (prop/for-all [s (gen/not-empty gen-string)]
                (< (count (bad-fn-4 s)) (count s))))

(defspec shrink-noah?
  1000
  ;; The point of this "test" is merely to show how test.check shrinks
  ;; the generated value to a local minimum, to help you find a
  ;; helpful failing test case that is as small as possible.
  (prop/for-all [things (gen/not-empty (gen/vector gen/int))]
                (noah? things)))

(def gen-rgb
  ;; gen/such-that would be another possibility, here.
  ;; What are the trade-offs of 'gen/such-that vs. 'gen/fmap ?
  (gen/fmap #(mod % 256) gen/pos-int))

(def gen-palette
  (gen/bind (gen/not-empty (gen/vector (gen/tuple gen-rgb gen-rgb gen-rgb)))
            (fn pick-fg-bg [colors]
              (gen/hash-map
               :palette (gen/return colors)
               :fg (gen/elements colors)
               :bg (gen/elements colors)))))

(comment
  ;; REPL-loading line
  (require 'meet-test-check.core-test :reload-all) (in-ns 'meet-test-check.core-test) (use 'clojure.repl)

  ;; test some generators in the REPL:
  (gen/sample gen/int)
  (gen/sample gen/int 3)
  (gen/sample (gen/vector gen/keyword 2))
  (gen/sample (gen/tuple gen/int gen/char-alphanumeric gen/keyword))
  (gen/sample gen-palette)

  ;; Ideas:
  ;; 1. Generate IPv4 addresses. IPv6 addresses.
  ;; 2. Generate directed graphs (of any kind). Possible example: [5 4 3 0 2 1] Traversal: 5 1 4 2 3 0
  )
