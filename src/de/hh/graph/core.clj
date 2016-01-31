(ns de.hh.graph.core
  (:require [de.hh.graph.system :as system]
            [de.otto.tesla.system :as tesla]
            [me.lomin.component-restart :as restart]
            [environ.core :as env :only [env]])
  (:gen-class))

(defn -main
  "starts up the system."
  [& args]
  (let [system (tesla/start (system/graph-visualizer {}))]
    (restart/watch (var -main) system)))
