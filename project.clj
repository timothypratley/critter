(defproject critter "0.1.0-SNAPSHOT"
  :description "Stack based"
  :url "http://github.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.9.1"

  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/clojurescript "1.10.773"]
                 [org.clojure/core.async  "1.3.610"]
                 [meander/epsilon "0.0.502"]
                 [reagent "0.10.0"]]

  :plugins [[lein-figwheel "0.5.20"]
            [lein-cljsbuild "1.1.8" :exclusions [[org.clojure/clojure]]]]

  :source-paths ["src"]

  :cljsbuild {:builds
              [{:id "dev"
                :source-paths ["src"]
                :figwheel {:on-jsload "critter.main/on-js-reload"
                           :open-urls ["http://localhost:3449/index.html"]}

                :compiler {:main critter.main
                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/critter.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true
                           ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
                           ;; https://github.com/binaryage/cljs-devtools
                           :preloads [devtools.preload]}}
               {:id "min"
                :source-paths ["src"]
                :compiler {:output-to "resources/public/js/compiled/critter.js"
                           :main critter.main
                           :optimizations :advanced
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles {:dev {:dependencies [[binaryage/devtools "1.0.2"]
                                  [figwheel-sidecar "0.5.20"]]
                   :source-paths ["src" "dev"]
                   :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                                     :target-path]}})
