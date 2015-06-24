(defproject meet-test-check "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/test.check "0.7.0"]
                 [com.gfredericks/test.chuck "0.1.18"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [junit/junit "4.12"]]
  :java-source-paths ["src/java" "test/java"])
