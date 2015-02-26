(ns ^:figwheel-always om-my.core
    (:require-macros [om-my.back-end :as om-my])
    (:require [om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]
              [om-my.utils :as utils]
              [cemerick.url :refer [url-encode]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload
(def api-key (om-my/get-api-key))

(def app-state
  (atom {:api-key api-key
         :text "Go to your figwheel repl and run (om-my.core/get-movie-by-title \"The Sting\")"
         :abridged_cast []
         :cast []}))


(defn display-list [items]
  (apply dom/ul #js {:title "This is how you set attributes."}
         (map #(dom/li nil %) items)))

(defn get-movie-response-key
  "Take a response from a query to /movies.json, assume the first
  listed movie is the correct one, and return the given 'k from the
  map for that movie.

  Available keys include:
  :posters
  :abridged_cast
  :release_dates
  :alternate_ids
  :title
  :year
  :id
  :runtime
  :critics_consensus
  :synopsis
  :ratings
  :mpaa_rating
  :links
  "
  [response k]
  (-> response
      :movies
      first
      k))

(defn update-details [response]
  (fn [current]
    (-> current
        (assoc-in [:all] response)
        (assoc-in [:text] (str "The title -- " (get-movie-response-key response :title)))
        (assoc-in [:abridged_cast] (get-movie-response-key response :abridged_cast))
        (assoc-in [:cast] (get response :cast)))))

(defn get-cast
  [response]
  (let [id (get-movie-response-key response :id)]
    (utils/edn-xhr
     {:method :post
      :url "/rt"
      :data (str "movies/" id "/cast.json?")
      :on-error (fn [response] (println response))
      :on-complete #(om/transact! (om/root-cursor app-state)
                                  (update-details (merge response %)))})))

(defn get-movie-by-title
  "Query RT's movies.json endpoint for a movie by title.

   After receiving a response, update app-state as following:
     (:all @app-state) is the entire response
     (:text @app-state) is the movie name
     (:abridged_cast @app-state) is the abridged cast vector
  "
  [title]
  (let [title (url-encode title)]
    (utils/edn-xhr
     {:method :post
      :url "/rt"
      :data (str "movies.json?q=" title)
      :on-error (fn [response] (println response))
      :on-complete get-cast})))

(om/root
 (fn [app owner]
   (reify
     om/IRender
     (render [_]
       (dom/h1 nil (:text app)))))
 app-state
 {:target (. js/document (getElementById "heading"))})

(om/root
 (fn [app owner]
   (reify
     om/IRender
     (render [_]
       (display-list (map :name (:cast app))))))
 app-state
 {:target (. js/document (getElementById "abridged_cast"))})

(om-my.core/get-movie-by-title "The Sting")
