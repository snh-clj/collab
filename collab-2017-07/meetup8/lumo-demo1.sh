#!/usr/bin/env bash

cat data.txt | ./lumo-demo1.cljs write-transit string | ./lumo-demo1.cljs read-transit
cat data.json | ./lumo-demo1.cljs write-transit json-clj | ./lumo-demo1.cljs read-transit
cat data.json | ./lumo-demo1.cljs write-transit json-transit | ./lumo-demo1.cljs read-transit
cat data.edn | ./lumo-demo1.cljs write-transit edn | ./lumo-demo1.cljs read-transit
