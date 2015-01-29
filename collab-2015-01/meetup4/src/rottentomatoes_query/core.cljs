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

(defn api
  [full-url callback-fn]
    (.get request full-url
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)]
              (callback-fn response-data)))))

(defn movie-url
  [title]
  (str api-base "/movies.json" "?q=" title "&apikey=" api-key))

(defn add-cast-key
  [url]
  (str url "?apikey=" api-key))

(defn -main [& _args]
  (api
   (movie-url (first _args))
   (fn [i]
     (let [movies (get i "movies")
           first-movie (first movies)
           cast-url (get-in first-movie ["links" "cast"])]
       (println (get (first movies) "title"))
       (println (add-cast-key cast-url))
       (api
        (add-cast-key cast-url)
        (fn [n]
          (println (keys n))
          (let [cast (get n "cast")]
            (doall
             (map
              #(println (get % "name"))
              cast)))))))))



(set! *main-cli-fn* -main)
