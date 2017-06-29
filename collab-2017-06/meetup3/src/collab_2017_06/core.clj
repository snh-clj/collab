(ns collab-2017-06.core
  (:require [org.httpkit.client :as http]
            [hiccup.core :as hic]
            [clojure.data.xml :as xml]
            [net.cgrand.enlive-html :as html]
            [net.cgrand.tagsoup :as tagsoup]
            [clojure.java.io :as io]
            ))

(defn string->stream
  ([s] (string->stream s "UTF-8"))
  ([s encoding]
     (-> s
         (.getBytes encoding)
         (java.io.ByteArrayInputStream.))))

(def ^:dynamic *base-url* "http://www.online-literature.com/")

(defn fetch-url [url]
    (html/html-resource (java.net.URL. url)))

(defn string->nodes [s]
  (-> s
      string->stream
      (html/get-resource tagsoup/parser)))

(defn get-lit []
  (let [top-site (:body @(http/get "http://www.online-literature.com/"))
        ;; site-stream (string->stream top-site)
        ;; nodes (html/get-resource site-stream tagsoup/parser)
        nodes (string->nodes top-site)
        authors (html/select nodes [:table.table-striped :tr :td :a])
        author->url (map #(let [{:keys [title href]} (:attrs %)] [title href]) authors)
        author-page-futures (map (fn [[author url]] [author (http/get url)])
                                 author->url)
        author-pages (map (fn [[author http-get]]
                            [author (-> http-get deref :body string->nodes)])
                          author-page-futures)
        ]
    author-pages
    ))


(comment
  (clojure.pprint/pprint (->> mef first :content (map :content) (map #(-> % first :attrs :href)))))



(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
