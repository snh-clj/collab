(defproject birdie-tell "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [server-socket "1.0.0"]
                 [org.clojure/tools.cli "0.3.3"]
                 ;; TODO: [clojurewerkz/vclock "1.0.0"]
                 ;; TODO: [com.cognitect/transit-clj "0.8.285"]
                 [cheshire "5.5.0"]]
  :main ^:skip-aot birdie-tell.core/main
;;  :plugins [[lein-ring "0.9.7"]]
;;  :ring {:handler birdie-tell.core/handler}
  )
