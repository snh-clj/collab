(ns om-my.dev
  (:require-macros [om-my.dev :as dev])
    (:require
     [om-my.core]
     [figwheel.client :as fw]))

(fw/start {
  :websocket-url (dev/websocket-url)
  :on-jsload (fn []
               ;; (stop-and-start-my app)
               )})
