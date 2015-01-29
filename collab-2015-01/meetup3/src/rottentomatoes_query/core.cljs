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

(def title "Bacon")

(defn title-search [query]
  (let [rq (str api-base "/movies.json" "?q=" query "&apikey=" api-key)]
    (println rq)
    (.get request rq
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)
                  movies (get response-data "movies")]
              (dorun (map #(println :title (get % "title") "\n\t"
                                    :id (get % "id") "\n\t"
                                    :cast-query-url (get-in % ["links" "cast"]))
                          movies)))))))

(defn make-url [movie-id verb]
  (str api-base "/movies/" movie-id "/" verb  ".json?apikey=" api-key))

(defn similar [movie-id]
  (let [rq (make-url movie-id "similar")]
    (.get request rq
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)
                  movies (get response-data "movies")]
              (println response-data)
              (dorun (map #(println :title (get % "title") "\n\t"
                                    :id (get % "id") "\n\t")
                          movies)))))))

(defn cast [movie-id]
  (let [rq (make-url movie-id "cast")]
    (println rq)
    (.get request rq
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)
                  cast (get response-data "cast")]
              (println response-data)
              (dorun (map #(println :name (get % "name") "\n\t"
                                    :id (get % "id") "\n\t"
                                    :characters (get % "characters"))
                          cast)))))))

(defn -main [& _args]
  (let [verb (first _args)
        subject (second _args)]
    (case verb
      "search" (title-search subject)
      "cast" (cast subject)
      "similar" (similar subject)
      (println "Unkown verb"))))

(set! *main-cli-fn* -main)
