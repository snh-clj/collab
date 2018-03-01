(ns clara-rules-2.core
  (:require [zprint.core :refer [zprint]]
            [clara.rules :as cr]
            [clara.rules.accumulators :as acc]))

(defrecord Engine [engine cost weight horsepower type])
(defrecord Transmission [transmission cost weight gear-min gear-max gear-count hybrid?])
(defrecord Powertrain [transmission engine1 engine2])
(defrecord Chassis [chassis cost weight ctype])
(defrecord Trim [chassis trim cost weight])
(defrecord Vehicle [engine transmission chassis trim])

;; Engineering defines facts about what products there are and how
;; they go together
(def product-facts
  [(->Engine "Zoom 400" 1000.0 350.0 400.0 :gasoline)
   (->Engine "Putt 90" 100.0 150.0 90.0 :gasoline)
   (->Engine "Zap 500" 1500.0 100.0 500.0 :electric)
   (->Transmission "Fixed Ratio" 500.0 50.0 2/1 2/1 1 false)
   (->Transmission "Hybridizer 2600" 800.0 100.0 1/2 7/1 7 true)
   (->Transmission "Boring 3.5" 700.0 75.0 1/2 5/1 7 false)])

;; Sales defines how many vehicles per category and minimum
;; performance

;; Government defines emissions costs and max weight

;; Custmer defines desired characteristics

#_
(cr/defrule valid-power-trains
  [?engines <- (acc/all) :from [Engine]]
  [Transmission (= ?transmission transmission) (= ?hybrid? hybrid?)]
  =>
  (if ?hybrid?
    (->
     (for [a ?engines
           b ?engines]
       (set [a b]))
     (set)
     (->> (map vec))
     (->> (map (fn [[a b]] (->Powertrain ?transmission a b))))
     (cr/insert-all!))
    (cr/insert-all!
     (map #(->Powertrain ?transmission % nil) ?engines))))

(cr/defrule valid-unhybrid-powertrains
  [Transmission (= hybrid? false) (= ?transmission transmission)]
  [Engine (= ?engine engine)]
  =>
  (cr/insert! (->Powertrain ?transmission ?engine nil)))

(cr/defrule valid-hybrid-powertrains
  [Transmission (= hybrid? true) (= ?transmission transmission)]
  [Engine (= ?engine1 engine)]
  [Engine (= ?engine2 engine)]
  =>
  (cr/insert! (->Powertrain ?transmission ?engine1 ?engine2)))

(cr/defrule remove-dupe-powertrains
  [?pt1 <- Powertrain (= ?engine1a engine1) (= ?engine2a engine2)]
  [?pt2 <- Powertrain (= ?engine1b engine1) (= ?engine2b engine2)]
  [:test (and (not= ?pt1 ?pt2)
              (= ?engine1a ?engine2b)
              (= ?engine2a ?engine1b))]
  =>
  (cr/retract! ?pt2))


(cr/defquery basic-query
  []
  [Powertrain
   (= ?engine1 engine1)
   (= ?engine2 engine2)
   (= ?transmission transmission)])


(comment
  (-> (cr/mk-session)
      (cr/insert-all product-facts)
      (cr/fire-rules)
      (cr/query basic-query)
      (zprint {:style :justified})))
