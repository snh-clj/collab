(ns meet-async.dev
  (:require-macros [meet-async.dev :as dev])
  (:require [meet-async.core]
            [figwheel.client :as fw]))

(fw/start
 {:websocket-url (dev/websocket-url)
  :on-jsload (fn []
               (js/window.location.reload)
               (println "Cynically reloading the page because this code is not actually figwheel-safe. See https://github.com/bhauman/lein-figwheel#writing-reloadable-code if you care."))})
