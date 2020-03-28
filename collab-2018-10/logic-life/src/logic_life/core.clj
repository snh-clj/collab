(ns logic-life.core
  (:require [clojure.core.logic :as l :refer [==]]))

#_
(let [block '((0 0 0)(1 1 1)(0 0 0))]
  (l/run 1 [o]
         (l/fresh [u1 u2 u3 m1 x m3 l1 l2 l3]
                (== o x)
                (== block (l/conso (l/conso u1 u2 u3)
                                   (l/conso m1 x m3)
                                   (l/conso l1 l2 l3))))))
#_
(defn compute-life
  [neighbors me next-me]
  ())


(def p1 ["      "
         " ##   "
         " ##   "
         "   ## "
         "   ## "
         "      "])

(def p2 ["     "
         "     "
         " ### "
         "     "
         "     "])

(def p3 ["      "
         "      "
         "  ### "
         " ###  "
         "      "
         "      "])


(defn fib1 [board]
  (let [blanks (repeat \space)
        board (map #(str " " % " ") board)]
    (for [rows (partition 3 1 (concat [blanks] board [blanks]))]
      (apply str
        (for [[nus [nl me nr] nds] (apply mapcat
                                    #(partition 3 %&)
                                    (map #(partition 3 1 %) rows))
              :let [n (count (filter #{\#} (concat [nl nr] nus nds)))]]
          (case n, 2 me, 3 "#", " "))))))
