(defproject rottentomatoes-query "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2725"]]

  :node-dependencies [[source-map-support "0.2.8"]
                      [request "2.51.0"]]

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-npm "0.4.0"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "rottentomatoes-query"
              :source-paths ["src"]
              :compiler {
                :output-to "out/rottentomatoes_query.js"
                :output-dir "out"
                :target :nodejs
                :optimizations :none
                :source-map true}}]})
