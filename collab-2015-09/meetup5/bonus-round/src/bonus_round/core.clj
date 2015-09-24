(ns bonus-round.core
  (:require [clojure.core.logic :as l]
            [clojure.core.logic.pldb :as pldb]
            [clojure.core.logic.fd :as fd]
            [datomic.api :as d :refer [db q]])
  )

;; 2014-04
;; Penultimate Problem: 4Clojure 178
;; http://www.4clojure.com/problem/178
;;
;; Following on from Recognize Playing Cards, determine the best poker
;; hand that can be made with five cards. The hand rankings are listed
;; below for your convenience.
;;
;; * Straight flush: All cards in the same suit, and in sequence
;; * Four of a kind: Four of the cards have the same rank
;; * Full House: Three cards of one rank, the other two of another rank
;; * Flush: All cards in the same suit
;; * Straight: All cards in sequence (aces can be high or low, but not both at once)
;; * Three of a kind: Three of the cards have the same rank
;; * Two pair: Two pairs of cards have the same rank
;; * Pair: Two cards have the same rank
;; * High card: None of the above conditions are met

(defn four-clojure-178 [hand]
  :fifty-two-pickup)

;; 2014-04
;; Ultimate Problem: Magic Squares
;;
;; Given a three-by-three square, fill each square with a unique
;; number 1-9 so all rows, columns and diagonals sum to 15.
;; Each number should be used once and only once.
;;
;; The output of your function should return a sequence (or list or vector) of
;; all solutions, with each solution being a sequence of three sequences of
;; three numbers, representing each row. e.g.
;;
;; [[[1 2 3][4 5 6][7 8 9]] [[2 3 4][5 6 7][8 9 1]] ... ]
;; ^--- the above doesn't meet summation constraints
;;
;; P.S. For those already comfortable with Clojure, this problem has a
;; nice solution using core.logic.

(defn magic-squares []
  [[[1 2 3] [4 5 6] [7 8 9]]
   [[9 8 7] [6 5 4] [3 2 1]]
   ,,,])

;; 2014-09
;; 2048 -- come back to this?
;; https://github.com/moquist/twenty-forty-eight/blob/master/src/twenty_forty_eight.clj


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
      #_(l/== r 4)
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
       [?e :person/name ?mother]
       [?f :person/mother ?e]
       [?f :person/name ?child]]
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
        (r-name father-id father)
        (r-father my-id father-id)
        (r-name my-id child)))))

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
     (l/fresh [x]
              (fd/in x (fd/interval 1 100))
              (fd/eq
               (= x (+ a b c))
               (= x (+ d e f))
               (= x (+ g h i))

               (= x (+ a d g))
               (= x (+ b e h))
               (= x (+ c f i))

               (= x (+ a e i))
               (= x (+ c e g)))))
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
