(ns de.hh.graph.stateful.graph-visualizer
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [compojure.core :as cpj]
            [de.hh.graph.view.view :as view]
            [de.hh.graph.graph.graph-store :as gs]
            [de.otto.tesla.stateful.handler :as handler]
            [compojure.core :as cpj]))

(defn get-data* [graph]
  (log/info "answer graph request")
  (str (gs/nodes graph)))

(defn routes [graph]
  (cpj/routes
    (cpj/GET "/view/" [request] (view/html))
    (cpj/GET "/view/resources/css/netjsongraph.css/" [request] (clojure.core/slurp "resources/css/netjsongraph.css"))
    (cpj/GET "/view/resources/js/netjsongraph.js/" [request] (clojure.core/slurp "resources/libs/netjsongraph.js"))
    (cpj/GET "/view/data/" [request] (clojure.core/slurp "resources/netjson.json"))
    (cpj/GET "/view/graph/" [request] (get-data* graph))))

(defrecord Visualizer [handler graph]
  component/Lifecycle
  (start [_]
    (log/info (str "-> starting Visualizer"))
    (handler/register-handler handler (routes graph)))
  (stop [_]
    (log/info (str "-> stopping Visualizer"))))

(defn new-visualizer []
  (map->Visualizer {}))
