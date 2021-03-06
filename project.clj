(defproject clj-graph-visualizer "0.1.0-SNAPSHOT"
  :description "a graph visualizer"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :uberjar-name ~(str "graph-visualizer.jar")
  :main ^:skip-aot de.hh.graph.core
  :test-paths ["test" "test-resources"]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.stuartsierra/component "0.3.0"]
                 [de.otto/tesla-microservice "0.1.26"]
                 [de.otto/tesla-basic-logging "0.1.4"]
                 [de.otto/tesla-httpkit "0.1.4"]
                 [ring "1.4.0"]
                 [compojure "1.4.0"]
                 [enlive "1.1.6"]
                 [aysylu/loom "0.5.4"]
                 [me.lomin/component-restart "0.1.0"]]
  :target-path "target/%s"
  :test-selectors {:default (constantly true)
                   :unit    :unit
                   :focused :focused}
  :profiles {
             :uberjar {:aot :all}
             :dev     {:dependencies []
                       :plugins      [[com.jakemccrary/lein-test-refresh "0.11.0"]]}})
