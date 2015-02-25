(ns om-my.back-end
  (:require [ring.util.response :refer [file-response]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [clojure.edn :as edn]
            [org.httpkit.client :as http]
            [clojure.string :as str]
            [cheshire.core :refer [parse-string]]
            [cemerick.url :refer [url-encode]]))

(defn generate-response [data & [status]]
  {:status (or status 200)
   :headers {"Content-Type" "application/edn"}
   :body (pr-str data)})

(defn get-api-key []
  (if-let [api-key (System/getenv "RT_API_KEY")]
    (when (< 0 (count api-key))
      api-key)))

(def api-base "http://api.rottentomatoes.com/api/public/v1.0/")
(defn rt-fetch!
  "Takes a query string that completes the URL. The apikey is added
  from the environment variable RT_API_KEY, which must be set."
  [query]
  (if-let [api-key (get-api-key)]
    (let [;; graciously eat leading /
          query (str/replace query #"^/" "")
          url (str api-base query "&apikey=" api-key)]
      (-> url
          http/get
          deref
          :body
          (parse-string true)
          pr-str))
    {:status 500
     :body "Missing RT_API_KEY environment variable. Set it and restart your REPL."}))

(defn read-inputstream-edn [input]
  (edn/read
   {:eof nil}
   (java.io.PushbackReader.
    (java.io.InputStreamReader. input "UTF-8"))))

(defn parse-edn-body [handler]
  (fn [request]
    (handler (if-let [body (:body request)]
               (assoc request
                 :edn-body (read-inputstream-edn body))
               request))))

(defroutes routes
  (POST "/rt" {edn-body :edn-body} (rt-fetch! edn-body))
  (route/files "/" {:root "resources/public"}))

(def handler
  (-> routes
      parse-edn-body))
