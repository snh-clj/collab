(ns relatable-logic.core
  "Relatable relational logic"
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.pldb :as pldb]
            [clojure.core.logic.fd :as fd]
            [datomic.api :as d :refer [db q]]))

;; Recommended reading:
;;  - The Reasoned Schemer (by Friedman, Dan and Byrd, William)
;;  - http://minikanren.org/
;;  - https://github.com/clojure/core.logic/wiki/A-Core.logic-Primer

(defn problem-get-3
  "BROKEN: Make this return a result of '(3)"
  []
  (l/run 10 [q]
    (l/fresh [r s]
      (l/== s 3)
      (l/== r s)
      (l/== q r))))

(defn problem-membero-2-3-4 []
  "BROKEN: Make this return a result of '(2 3 4)"
  (l/run 10 [q]
    (l/fresh [a]
      (l/membero a [1 2 3 4])
      (l/membero q [2 3 4 5])
      (l/== a q))))

(defn problem-anja-orange []
  "BROKEN: Make Anja's favorite fruit be '(:orange)"
  (l/run 10 [anja]
    (l/fresh [you me hiro all]
      (l/== all [you me hiro anja])
      (l/== you :apple)
      (l/== [:banana :pear] [me hiro])
      (l/appendo [you me] [hiro :orange] all))))

;; Datomic
(def p1-db [[1 :person/name "Bob"]
            [1 :person/mother 3]
            [2 :person/name "Alice"]
            [2 :person/mother 3]
            [3 :person/name "Mari"]
            [3 :person/mother 4]
            [4 :person/name "Fran"]])

(defn problem-find-my-mother
  "BROKEN: Make this correctly find the mother of 'child.

  E.g.:
  (problem-find-my-mother \"Gary\")
  ;; => #{[\"Sheri\"]}
  "
  [child]
  (q '[:find ?mother
       :in $ ?child
       :where
       [?e :person/name ?child]
       [?e :person/mother ?f]
       [?f :person/name ?mother]]
     p1-db child))

;; PLDB - Prolog DB
(pldb/db-rel r-name my-id my-name)
(pldb/db-rel r-father my-id father-id)
(def p2-db (-> pldb/empty-db
               ;; You can add facts individually...
               (pldb/db-fact r-name 1 "Bob")
               (pldb/db-fact r-father 1 3)
               ;; ... or you can add groups of facts
               (pldb/db-facts [r-name 2 "Alice"]
                              [r-name 3 "Mart"]
                              [r-name 4 "Frank"]
                              [r-father 2 3]
                              [r-father 3 4])))

;; NOTE: If you squint, this problem is suspiciously like the
;; previous...
(defn problem-find-my-father
  "BROKEN: Make this correctly find the father of 'child.

  E.g.:
  (problem-find-my-father \"Gary\")
  ;; => #{[\"Sheri\"]}
  "
  [child]
  (pldb/with-db p2-db
    (l/run 10 [father]
      (l/fresh [my-id father-id]
        (r-name my-id child)
        (r-father my-id father-id)
        (r-name father-id father)))))

(defn magic-squares
  "In recreational mathematics, a magic square is an arrangement of
  distinct numbers (i.e. each number is used once), usually integers,
  in a square grid, where the numbers in each row, and in each column,
  and the numbers in the main and secondary diagonals, all add up to
  the same number. A magic square has the same number of rows as it
  has columns. (https://en.wikipedia.org/wiki/Magic_square)

  This magic square finder is broken because it doesn't ensure that
  each number is used only once, and because it fails to check all the
  sums.

  Can you fix it?"
  []
  (->>
   ;; We'll represent our square like this:
   (l/run* [a b c
            d e f
            g h i]
     ;; each square can have the value 1-9
     (fd/in a b c d e f g h i (fd/interval 1 9))
     ;; numbers 1-9 should only appear once
     (fd/distinct [a b c d e f g h i])
     (fd/eq
      #_you_may_want_something_here
      (= 15 (+ a b c))
      (= 15 (+ d e f))
      (= 15 (+ g h i))
      
      (= 15 (+ a d g))
      (= 15 (+ b e h))
      (= 15 (+ c f i))

      (= 15 (+ a e i))
      (= 15 (+ c e g))
 
      ))
   (map #(partition 3 %))))

(defn triangulizer
  "Our triangulizer computes integer dimensioned right triangles with
  sides up to 100 units.

  The function takes legs 'a 'b and/or hypotenuse 'h specified as
  integers or nil, if unspecified, and will return a list of solutions
  or nil if no solutions exist.

  Consider how you'd write a function like this without core.logic."
  [a b h]
  (let [a (or a (l/lvar))
        b (or b (l/lvar))
        h (or h (l/lvar))]
    (seq
     (l/run* [q]
       (l/== q [a b h])
       ;; We'll only consider triangles with sides 100 integer units
       ;; or smaller.
       (fd/in a b h (fd/interval 1 100))
       ;; Normalize the order of 'a and 'b so we don't get duplicate
       ;; results.
       (fd/<= a b)
       (fd/eq
        #_Think_about_Pythagoras_and_type_code_here
        )))))
