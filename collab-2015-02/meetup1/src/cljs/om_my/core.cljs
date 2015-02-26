(ns ^:figwheel-always om-my.core
    (:require [om.core :as om :include-macros true]
              [om.dom :as dom :include-macros true]
              [om-my.utils :as utils]
              [cemerick.url :refer [url-encode]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state
  (atom {:text "Go to your figwheel repl and run (om-my.core/get-movie-by-title \"The Sting\")"
         :abridged_cast []
         :cast []
         :poster ""}))

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

(defn get-movie-by-title
  "Query RT's movies.json endpoint for a movie by title.

   After receiving a response, update app-state as following:
     (:all @app-state) is the entire response
     (:text @app-state) is the movie namne
     (:abridged_cast @app-state) is the abridged cast vector
  "
  [title]
  (let [title (url-encode title)]
    (utils/edn-xhr
     {:method :post
      :url "/rt"
      :data (str "movies.json?q=" title)
      :on-error (fn [response] (println response))
      :on-complete (fn [response]
                     (om/update! (om/root-cursor app-state)
                                 :all
                                 response)
                     (om/update! (om/root-cursor app-state)
                                 :text
                                 (get-movie-response-key response :title))
                     (om/update! (om/root-cursor app-state)
                                 :abridged_cast
                                 (get-movie-response-key response :abridged_cast))
                     (om/update! (om/root-cursor app-state)
                                 :poster
                                 (:detailed (get-movie-response-key response :posters))))})))

(om/root
 (fn [app owner]
   (reify
     om/IRender
     (render [_]
       (dom/div nil (dom/h1 nil (:text app))
                (dom/button #js {:onClick (fn [e]
                                            (get-movie-by-title
                                             (.-value (. js/document (getElementById "search")))))} "click me!")))))
 app-state
 {:target (. js/document (getElementById "heading"))})

(om/root
 (fn [app owner]
   (reify
     om/IRender
     (render [_]
       (display-list (map #(str (:name %) "-" (apply str (interpose ", " (:characters %)))) (:abridged_cast app))))))
 app-state
 {:target (. js/document (getElementById "abridged_cast"))})

(om/root
 (fn [app owner]
   (reify
     om/IRender
     (render [_]
       (dom/img #js {:src (:poster app)}))))
 app-state
 {:target (. js/document (getElementById "poster"))})
