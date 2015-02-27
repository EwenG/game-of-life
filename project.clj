(defproject game-of-life "0.1.0"
            :min-lein-version "2.0.0"
            :source-paths ["src/cljs"]
            :resource-paths ["assets"]
            :dependencies [[org.clojure/clojure "1.7.0-alpha5"]
                           [org.clojure/clojurescript "0.0-2850"]]
            :plugins [[lein-cljsbuild "1.0.4"]]
            :cljsbuild {
                        :builds [{:id "dev"
                                  :source-paths ["src/cljs"]
                                  :compiler {:output-to "resources/cljs/game-of-life.js"
                                             :output-dir "resources/cljs/out"
                                             :optimizations :none
                                             :source-map true}}]})
