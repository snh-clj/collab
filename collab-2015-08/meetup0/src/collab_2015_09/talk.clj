(ns collab-2015-09.talk
  (:require [clojure.core.async :as async
             :refer [chan go >!! <!! close!]]
            [clojure.walk]))

;; applying functions
(+ 1 2 3)
(apply + [1 2 3])
(reduce + [1 2 3])

(reductions + [1 2 3])
(reductions + 0 [1 2 3])
(reductions conj nil [1 2 3])
(reductions conj [nil 1 2 3])
(reductions conj [] [1 2 3])
(reductions conj [[] 1 2 3])

(defn map-monitor
  "Transforms map-fn to have the side-effect of printing the input
  and output"
  [map-fn]
  (fn [item]
    (let [r (map-fn item)]
      (println "Mapping" item "with" map-fn "to get" r)
      r)))

(defn reduce-monitor
  "Transforms reduce-fn to have the side-effect of printing the inputs
  and output"
  [reduce-fn]
  (fn [so-far item]
    (let [r (reduce-fn so-far item)]
      (println "Reducing" item "into" so-far "with" reduce-fn "to get" r)
      r)))

(reduce (reduce-monitor #'+) [1 2 3])
(reduce (reduce-monitor #'+) 0 [1 2 3])
(reduce (reduce-monitor #'conj) nil [1 2 3])
(reduce (reduce-monitor #'conj) [] [1 2 3])

;; Mapping is lazy
(def mapped-yet? (map (map-monitor #'inc) (range 10)))
;; Reducing is eager
(def reduced-yet? (reduce (reduce-monitor #'+) (range 10)))
;; ... therefore this will get us into trouble...
#_(reduce (reduce-monitor #'+) (range))

;; ... but we can stop reduction, even on infinite sequences
(reduce #(if (< %2 5)
           (conj %1 %2)
           (reduced %1)) [] (range))

;; reduced? ?

;; Strong notion of order
(reduce vector [1 2 3 4 5 6])

(reduce - [1 2 3 4 5 6])
(reduce - (reverse [1 2 3 4 5 6]))
;; decided to chuck this later...
(reduce vector [1 2 3 4 5 6])
(reduce vector (reverse [1 2 3 4 5 6]))
(clojure.walk/postwalk #(if (coll? %) (vec (reverse %)) %)
                       (reduce vector (reverse [1 2 3 4 5 6])))

;; Clojure project name generator

;; cat /usr/share/dict/words \
;;    |tr A-Z a-z \
;;    |grep -v '[^a-z]' \
;;    |grep 'zh\|shur\|zur\|jour\|jur'

(defn lower-duce []
  (fn [xf]
    (fn
      ([] (xf))
      ([r] (xf r))
      ([r s] (xf r (.toLowerCase s))))))

(defn grep-duce [pred re]
  (fn [xf]
    (fn
      ([] (xf))
      ([r] (xf r))
      ([r s] (if (pred (re-find re s))
               (xf r s)
               r)))))

(def clojure-duce
  (comp
   (lower-duce)
   (grep-duce not #"[^a-z]")
   (grep-duce identity #"shur|zur|jour|jur")))

(defn run-clojure-duce []
  (let [coll (line-seq
              (clojure.java.io/reader "/usr/share/dict/words"))]
    (transduce clojure-duce conj [] coll)))

(defn run-chan-clojure-duce []
  (let [coll (line-seq
              (clojure.java.io/reader "/usr/share/dict/words"))
        c (chan 1 clojure-duce)]
    (future (doseq [x coll] (>!! c x))
            (close! c))
    (loop [r []]
      (let [v (<!! c)]
        (if v
          (recur (conj r v))
          r)))))

(defn =duce-duce? []
  (when (= (run-clojure-duce) (run-chan-clojure-duce))))


;; Bad example
(defn bad-duce
  "Bad example of transducer which looks at 'r"
  []
  (fn [xf]
    (fn
      ([] (xf))
      ([r] (xf r))
      ([r i]
       (if (contains? (into #{} r) i)
         (xf r i)
         r)))))

(comment
  (transduce (bad-duce) conj [1 2 3] [1 2 4 2 2 4 5 2])
  )




(defn string-duce
  []
  (fn [xf]
    (fn
      ([] (xf))
      ([r] (xf r))
      ([r i]
       (loop [r r chrs (seq i)]
         (if chrs
           (recur (xf r (first chrs)) (next chrs))
           (xf r " ")))))))

(defn classify [x]
  (let [c (-> x str first)]
    (cond
      (= c nil) c
      (<= (int \a) (int c) (int \z)) :letter
      (<= (int \A) (int c) (int \Z)) :letter
      (<= (int \0) (int c) (int \9)) :number
      :else c)))

(def syntax-chr? (into #{} "()[]"))

(defn token-break? [so-far i]
  (or
    (syntax-chr? i)
    (not= (classify i)
          (classify so-far))
    (empty? so-far)))

(defn token-duce
  []
  (fn [xf]
    (let [so-far (volatile! " ")]
      (fn
        ([] (vreset! so-far " ") (xf))
        ([r] (->> (xf r @so-far)
                  unreduced
                  xf))
        ([r i]
         (if (token-break? @so-far i)
           (let [token @so-far]
             (vreset! so-far (str i))
             (xf r token))
           (do
             (vswap! so-far str i)
             r)))))))

(defn drop-space-duce
  []
  (fn [xf]
    (fn
      ([] (xf))
      ([r] (xf r))
      ([r i]
       (if (= \  (first i))
         r
         (xf r i))))))

(defn assemble [so-far to-go]
  )

#_
(defn emit-duce
  []
  (fn [xf]
    (let [so-far (volatile! [])]
      (fn
        ([] (xf))
        ([r]
         (loop [r nil so-far @so-far]
           (if (empty? so-far)
             (xf r)
             (let [[out so-far] (assemble nil so-far)]
                   (xf r out)))))
        ([r i]
         (vswap! so-far conj i))))))

(def read-process
  (comp
   (string-duce)
   (token-duce)
   (drop-space-duce)
   #_(emit-duce)))

(comment
  (transduce read-process conj [] ["()()"
                                   "1X2"
                                   "123XyZ321"
                                   "123 XyZ 321"
                                   "1  3  X  Z  3  1"
                                   "(()((()()())))"
                                   ""
                                   "(())"
                                   "(()())"])
  )

