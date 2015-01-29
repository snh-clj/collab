(ns rottentomatoes-query.core
  (:require [clojure.walk :as walk]
            [clojure.string :as s]
            [cljs.nodejs :as nodejs]))

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

(defn GET [path params cb-fn]
  (let [params (merge {"apikey" api-key} params)
        param-str (->> params
                       (map (fn [[k v]] (str k \= v)))
                       (s/join \&)
                       (str \?))
        url-str (str path param-str)]
    (.get request url-str cb-fn)))

(defn parse-response [resp]
  (-> resp .-body js/JSON.parse js->clj))

(defn get-cast [movie-id]
  (let [rq (str api-base "/movies/" movie-id "/cast.json")]
    (GET rq nil
         (fn [err resp]
           (prn (parse-response resp))))))

(defn get-movie-info [movie]
  (let [movie (walk/keywordize-keys movie )
        rq (-> movie :links :self)]
    (GET rq {"apikey" api-key}
         (fn [err resp]
           (let [response-data (parse-response resp)
                 movie-id (get response-data "id")]
             (get-cast movie-id))))))

(defn -main [& args]
  (GET (str api-base "/movies.json")
       {"q" (first args)}
       (fn [err resp]
         (let [response-data (parse-response resp)
               movies (get response-data "movies")]
           (prn response-data)
           (get-movie-info (first movies))))))


(set! *main-cli-fn* -main)
