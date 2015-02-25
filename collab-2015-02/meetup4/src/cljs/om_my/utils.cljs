(ns ^:figwheel-always om-my.utils
    (:require [goog.events :as events]
              [cljs.reader :as reader])
    (:import [goog.net XhrIo]
             goog.net.EventType
             [goog.events EventType]))

;; -------------------------------------------
;; Ripped off from om-sync
(def ^:private meths
  {:get "GET"
   :put "PUT"
   :post "POST"
   :delete "DELETE"})

(defn edn-xhr [{:keys [method url data on-complete on-error]}]
  (let [xhr (XhrIo.)]
    (events/listen xhr goog.net.EventType.SUCCESS
                   (fn [e]
                     (on-complete (reader/read-string (.getResponseText xhr)))))
    (events/listen xhr goog.net.EventType.ERROR
                   (fn [e]
                     (on-error {:error (.getResponseText xhr)})))
    (. xhr
       (send url (meths method) (when data (pr-str data))
             #js {"Content-Type" "application/edn" "Accept" "application/edn"}))))
