(ns de.hh.graph.system
  (:require [de.otto.tesla.system :as ts]
            [com.stuartsierra.component :as c]
            [de.otto.tesla.serving-with-httpkit :as http]
            [de.hh.graph.graph-visualizer :as v]))

(defn graph-visualizer [runtime-config]
  (-> (ts/base-system runtime-config)
      (http/add-server :health)
      (assoc :view (c/using (v/new-visualizer) [:config :handler]))
      (dissoc :metering)
      (dissoc :app-status)))