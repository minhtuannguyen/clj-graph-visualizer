(ns de.hh.graph.graph-visualizer
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [compojure.core :as cpj]
            [de.hh.graph.view.view :as view]
            [de.otto.tesla.stateful.handler :as handler]
            [compojure.core :as cpj]))

(defn run-view []
  (view/html))

(defn routes [config]
  (cpj/routes
    (cpj/GET "/view/" [request] (run-view))
    (cpj/GET "/view/resources/css/netjsongraph.css/" [request] (clojure.core/slurp "resources/css/netjsongraph.css"))
    (cpj/GET "/view/resources/js/netjsongraph.js/" [request] (clojure.core/slurp "resources/libs/netjsongraph.js"))
    (cpj/GET "/view/data/" [request] (clojure.core/slurp "resources/netjson.json"))))

(defrecord Visualizer [config handler]
  component/Lifecycle
  (start [_]
    (log/info (str "-> starting Visualizer"))
    (handler/register-handler handler (routes (:config config))))
  (stop [_]
    (log/info (str "-> stopping Visualizer"))))

(defn new-visualizer []
  (map->Visualizer {}))
