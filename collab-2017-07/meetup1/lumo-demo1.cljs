#!/usr/bin/env lumo
(ns lumo-demo1.dot
  "TODO ideas:
     1. \"port\" this program to planck
        1. What set of decisions has been been made by choosing lumo? Not just CLJS. Also node, also lumo.core.
     2. refactor this to handle input data that exceeds system memory
     3. add real getopt-style command-line parsing
     4. What other crucial bash-replacement features are not demonstrated here? Can you add them?
     5. How to pull in other CLJS deps? Examples: https://clojurescript.org/community/libraries
     6. How to build this as a standalone tool that could be distributed easily?
     6. add transform features, add a flight sim easter egg, do something completely different, etc. :)"
  (:require [cljs.nodejs :as node]
            [clojure.pprint :refer [pprint]]
            [clojure.string :as string :refer [join split starts-with?]]
            [cognitect.transit :as t]
            [cljs.reader :as reader]
            [lumo.core :refer [*command-line-args*]]))

(defonce version "0.1")

(node/enable-util-print!)


;; Load the Javascript docopt module
(defonce docopt (node/require "docopt"))

;; Goal: use docopt to parse the described command line
;; 1. install javascript impl of docopt (done)
;; 2. require docopt (done)
;; 3. describe usage based on delicious-arguments below (done)

(def lumo-demo1-usage
  "Lumo Demo1 (showing how to use transit to serialize and deserialize)

  Usage:
      lumo-demo1.cljs [--debug-argv] write-transit (string | json-clj | json-transit | edn)
      lumo-demo1.cljs [--debug-argv] read-transit
      lumo-demo1.cljs [--debug-argv] -h | --help
      lumo-demo1.cljs [--debug-argv] --version

  Options:
      -h --help      Show this screen.
      --version      Show version.
      --debug-argv   Show the parsed command line.

  Examples:
      cat data.txt | ./lumo-demo1.cljs write-transit string | ./lumo-demo1.cljs read-transit
      cat data.json | ./lumo-demo1.cljs write-transit json-clj | ./lumo-demo1.cljs read-transit
      cat data.json | ./lumo-demo1.cljs write-transit json-transit | ./lumo-demo1.cljs read-transit
      cat data.edn | ./lumo-demo1.cljs write-transit edn | ./lumo-demo1.cljs read-transit
  ")

(def cli-map (js->clj
               (.docopt docopt
                        lumo-demo1-usage
                        #js{:version version
                            :argv (clj->js (vec *command-line-args*))})
               :keywordize-keys true))

(when (:--debug-argv cli-map)
  (println "\n Parsed command line:")
  (pprint cli-map))


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Demonstrate running shell sub-commands, capturing results, and printing to STDERR

;; See https://github.com/shelljs/shelljs
(defonce shell (node/require "shelljs"))

(defn println-stderr
  "See https://cljs.github.io/api/cljs.core/STARprint-err-fnSTAR"
  [& args]
  (binding [*print-fn* *print-err-fn*]
    (apply println args)))

(println-stderr :which-whatsit? (.which shell "whatsit"))
(println-stderr :which-git? (.which shell "git"))
(println-stderr :etc-issue (.cat shell "/etc/issue"))

;; TODO: Why doesn't this work?
#_(println-stderr :exec-node-v (.exec shell "node -v"))
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Demonstrate reading STDIN to make a pipe-able program
(defn roundtrip [x]
  (let [w (t/writer :json)
        r (t/reader :json)]
    (t/read r (t/write w x))))

(defn read-stdin-into-memory
  "TODO: Whoa, scary! We're reading *all of STDIN* into memory.
   It is possible this could not work, someday, probably after it's
   been in production for 3 years without being touched and all the
   original devs have moved to other projects. Maybe it would be
   better to be streamy and less steamy-piley."
  [cb]
  (let [stdinput (atom "")]
    (.setEncoding js/process.stdin "utf8")
    (.on js/process.stdin "data"
         (fn [data]
           (swap! stdinput #(str % data))))

    (.on js/process.stdin "end"
         #(cb @stdinput)
         )))

(defn transit-some-stuff [op stuff-type stuff]
  #_(pprint {:op op :stuff-type stuff-type :stuff stuff})
  (condp = op
    ;; 'print transit
    "write-transit" (print (t/write (t/writer :json)
                                    (condp = stuff-type
                                      "string" stuff
                                      "json-clj" (js->clj (.parse js/JSON stuff) :keywordize-keys true)
                                      "json-transit" (t/read (t/reader :json) stuff)
                                      "edn" (reader/read-string stuff)
                                      )))
    ;; 'prn clj data
    "read-transit" (prn (t/read (t/reader :json) stuff))
    ))

(def delicious-arguments
  "These are exact matches of acceptable arguments to this program."
  #{["write-transit" "string"]
    ["write-transit" "json-clj"]
    ["write-transit" "json-transit"]
    ["write-transit" "edn"]
    ["read-transit"]})

(if (not (delicious-arguments (vec *command-line-args*)))
  (prn "Whoa, there. Garbage arguments!! Here are the delicious arguments: " delicious-arguments)
  (let [[op input-data-type] *command-line-args*]
    (read-stdin-into-memory
     (partial transit-some-stuff op input-data-type))))


"
Usage examples:

cat data.txt | ./lumo-demo1.cljs write-transit string | ./lumo-demo1.cljs read-transit
cat data.json | ./lumo-demo1.cljs write-transit json-clj | ./lumo-demo1.cljs read-transit
cat data.json | ./lumo-demo1.cljs write-transit json-transit | ./lumo-demo1.cljs read-transit
cat data.edn | ./lumo-demo1.cljs write-transit edn | ./lumo-demo1.cljs read-transit

"
