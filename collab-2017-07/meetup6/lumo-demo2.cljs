#!/usr/bin/env lumo
;; https://gist.github.com/yogthos/d9d2324016f62d151c9843bdac3c0f23#file-gallery-cljs
;; This downloads http://www.windowsonearth.org/ to your pwd!
(ns spacedl.spacedl
  (:require [cljs.nodejs :as node]
            [clojure.pprint :refer [pprint]]
            [clojure.string :as string :refer [join split starts-with?]]
            [lumo.core :refer [*command-line-args*]]))

(node/enable-util-print!)

;; https://nodejs.org/api/process.html#process_event_uncaughtexception
(.on js/process "uncaughtException" #(js/console.error %))

;; https://nodejs.org/api/http.html
(defonce http (node/require "http"))

;; https://nodejs.org/api/https.html
(defonce https (node/require "https"))

;; https://nodejs.org/api/fs.html
(defonce fs (node/require "fs"))

(defn page [gallery-url page-number]
  (string/replace gallery-url #"PageNumber=\d+&" (str "PageNumber=" page-number "&")))

(defn parse-image-url [{:keys [GalleryUrl Index UrlSignature URLFilename ImageKey PhotoBy Sizes]}]
  (let [size      (->> [:O :5K :4K :X5 :X4 :X3]
                       (filter #(Sizes %))
                       (first))
        ext       (-> size Sizes :ext)
        file-name (str URLFilename "-" (name size) "." ext)]
    (when size
      {:url       (join "/" [GalleryUrl Index UrlSignature (name size) file-name])
       :file-name file-name})))

(defn get-url [url cb]
  (.get (if (starts-with? url "https") https http) url cb))

(defn parse-page [url]
  (fn [resolve reject]
    (get-url url
             (fn [res]
               (let [body (atom "")]
                 (-> res
                     (.on "data" #(swap! body str (.toString %)))
                     (.on "end" #(-> @body
                                     js/JSON.parse
                                     (js->clj :keywordize-keys true)
                                     resolve))))))))

(defn follow-redirects [url cb]
  (get-url
    url
    (fn [res]
      (if-let [location (aget (.-headers res) "location")]
        (follow-redirects location cb)
        (cb res)))))

(defn save-file [{:keys [url file-name]} cb]
  (follow-redirects
    url
    (fn [res]
      (println "saving" url)
      (.pipe res (.createWriteStream fs file-name)
             (js/setTimeout cb 100)))))

(defn save-links [[link & links]]
  (when link
    (if (.existsSync fs (:file-name link))
      (do
        (println "skipping" (:file-name link))
        (save-links links))
      (save-file link #(save-links links)))))

(defn parse-image-links [pages]
  (-> (map #(js/Promise. (parse-page %)) pages)
      (js/Promise.all)
      (.then #(->> % (map :Images) (apply concat) (map parse-image-url) set save-links))
      (.catch js/console.error)))

(defn parse-gallery [url {:keys [TotalItems TotalPages]}]
  (println "gallery contains" TotalPages "pages with" TotalItems "images")
  (parse-image-links (map (partial page url) (range TotalPages))))

(defn parse-url [url]
  (-> (js/Promise. (parse-page url))
      (.then #(parse-gallery url (:Pagination %)))
      (.catch js/console.error)))

(if (empty? *command-line-args*)
  (println "please provide a gallery URL as the argument")
  (parse-url (first *command-line-args*)))
