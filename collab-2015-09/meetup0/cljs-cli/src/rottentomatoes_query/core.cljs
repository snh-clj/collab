(ns rottentomatoes-query.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def request (nodejs/require "request"))

(def api-key (-> (.-env nodejs/process) js->clj
                 ;; OPTIONAL: Feel free to substitute your own API key
                 ;; in your environment, and change the name here if
                 ;; necessary.
                 ;;
                 ;; Please do not:
                 ;;   1. edit ~/.bashrc, because that will affect
                 ;;      other groups
                 ;;   2. paste your API key in this file, because this
                 ;;      will be pushed to github and your key cannot
                 ;;      be made public
                 (get "RT_API_KEY")))

(def api-base "http://api.rottentomatoes.com/api/public/v1.0")

(def title "The Sting")

(defn -main [& _args]
  (let [rq (str api-base "/movies.json" "?q=" title "&apikey=" api-key)]
    (.get request rq
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)
                  movies (get response-data "movies")]
              (dorun (map #(println :title (get % "title") "\n\t"
                                    :cast-query-url (get-in % ["links" "cast"]))
                          movies)))))))

(set! *main-cli-fn* -main)
