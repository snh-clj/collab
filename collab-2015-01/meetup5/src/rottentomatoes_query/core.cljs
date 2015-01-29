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

(def api-base "http://api.rottentomatoes.com/api/public/v2.0")

(def title "The Sting")

(defn print-cast
  [cast-url]
;  (println "cast-url:" cast-url)
  (let [rq (str cast-url "?apikey=" api-key)]
;    (println "rq:" rq)
    (.get request rq
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)
                  cast (get response-data "cast")]
;              (println "cast:" cast)
              (dorun (map #(println :name (get % "name") "plays"
                                    :character (get % "characters"))
                          cast)))))))


(defn -main [& _args]
  (let [rq (str api-base "/movies.json" "?q=" (first _args) "&page_limit=1" "&apikey=" api-key)]
;    (println "movie-rq:" rq)
    (.get request rq
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)
                  movies (get response-data "movies")]
              (dorun (map #(do (println :title (get % "title") )
                               (print-cast (get-in % ["links" "cast"])))
                          movies)))))))
      

(set! *main-cli-fn* -main)
