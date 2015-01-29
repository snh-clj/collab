(ns rottentomatoes-query.core
  (:require [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(def request (nodejs/require "request"))

(def all-da-data (atom #{}))

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

(defn resp->data [resp]
  (-> resp .-body js/JSON.parse js->clj))

(defn receive-cast [err resp]
  (let [response-data (resp->data resp)
        cast (get response-data "cast")
        cast-names (map #(get % "name") cast)]
    (if (empty? @all-da-data)
      (swap! all-da-data #(set (concat % cast-names)))
      (doseq [name cast-names]
        (println (@all-da-data name))))))

(defn receive-movie [err resp]
  (let [response-data (resp->data resp)
        movie (first (get response-data "movies"))
        cast-url (get-in movie ["links" "cast"])]
    (println :title (get movie "title"))
    (.get request (str cast-url "?apikey=" api-key) receive-cast)))

(defn -main [title title1]
  (.get request (str api-base "/movies.json" "?q=" title "&apikey=" api-key) receive-movie)
  (.get request (str api-base "/movies.json" "?q=" title1 "&apikey=" api-key) receive-movie))

(set! *main-cli-fn* -main)
