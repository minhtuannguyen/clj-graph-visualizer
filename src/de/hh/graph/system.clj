(ns de.hh.graph.system
  (:require [de.otto.tesla.system :as ts]
            [com.stuartsierra.component :as c]
            [de.otto.tesla.serving-with-httpkit :as http]
            [de.hh.graph.graph.graph-store :as graph]
            [de.hh.graph.stateful.graph-visualizer :as v]))

(defn graph-visualizer [runtime-config]
  (-> (ts/base-system runtime-config)
      (http/add-server :health)
      (assoc :graph (c/using (graph/new-graph) []))
      (assoc :view (c/using (v/new-visualizer) [:handler :graph]))
      (dissoc :metering)
      (dissoc :app-status)))