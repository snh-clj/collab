(ns clara.core
  (:require [clara.rules :as cr]
            [clara.tools.inspect :as ci]
            [clara.tools.fact-graph :as fg]
            [zprint.core :as zp]))

(defrecord Engine [id fuel hp eff])

(defrecord Transmission [id type])

(defrecord Car [eng-id tran-id])

(cr/defrule
  TestRule
  [Engine (= fuel :gas) (= ?eng-id id)]
  [Transmission (= type :manual) (= ?tran-id id)]
  =>
  (cr/insert! (->Car ?eng-id ?tran-id)))

(cr/defrule
  TestDeleteRule
  [?tran <- Transmission (= id "WWhirr")]
  =>
  (cr/retract! ?tran))

(cr/defquery
  car-query
  []
  [Car (= ?car-name (str eng-id "**" tran-id))]
  [Car (= ?myeng-id eng-id)])

(comment
  (->
   (cr/mk-session 'clara.core)
   (cr/insert (->Engine "ZZoom" :gas 1.0 0.01)
              (->Engine "PPutt" :gas 0.1 0.10)
              (->Engine "ZZapp" :electric 2.0 0.99)
              (->Transmission "WWhirr" :manual)
              (->Transmission "SScreetch" :manual)
              (->Transmission "KKlunkk" :automatic))
   (cr/fire-rules)
   (ci/inspect)
   (zp/zprint)))

   ;(fg/session->fact-graph)))
   ;(cr/query car-query)))
