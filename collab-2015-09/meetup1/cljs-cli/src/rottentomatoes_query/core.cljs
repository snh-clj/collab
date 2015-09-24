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

(def title "7")

(def search-keys (range 0 10))

(defn -main [& _args]
  (map 
   (fn [key] (let [rq (str api-base "/movies.json" "?q=" key "&apikey=" api-key)]
    (.get request rq
          (fn [err resp]
            (let [response-data (-> resp .-body js/JSON.parse js->clj)
                  total (get response-data "total")]
              (println :query key :total total))))))
   search-keys))

(set! *main-cli-fn* -main)

            #_(let [response-data (-> resp .-body js/JSON.parse js->clj)
                  movies (get response-data "movies")]
              (dorun (map
                      #_(println :obj %)
                      #(println :title (get % "title") "\n\t"
                                 :year (get % "year")
                                    ;;:cast-query-url (get-in %
                                    ;["links" "cast"])
                                 )
                      movies)))
