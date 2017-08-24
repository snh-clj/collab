(ns four-clojure-four-fun.core)

(defn problem-32 [x]
  (mapcat vector x x))

(defn problem-44-1 [n coll]
  (cond
   (pos? n)
   (recur (dec n) (concat (drop 1 coll) (take 1 coll)))

   (neg? n)
   (recur (inc n) (concat [(last coll)] (butlast coll)))

   (zero? n)
   coll

   :anything
   (throw (ex-info "Holy cow, we got a thing that's not positive, negative, or zero!" {}))))

(defn problem-44 [n coll]
  (let [c (count coll)]
    (take c
          (drop (mod n c)
                (cycle coll))))
  )
