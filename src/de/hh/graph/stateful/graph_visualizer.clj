(ns de.hh.graph.stateful.graph-visualizer
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [compojure.core :as cpj]
            [de.hh.graph.view.view :as view]
            [de.hh.graph.graph.graph-store :as gs]
            [clojure.walk :as cw]
            [ring.util.codec :as rc]
            [clojure.data.json :as json]
            [ring.util.response :as r]
            [de.otto.tesla.stateful.handler :as handler]
            [compojure.core :as cpj]))

(defn- node-representation [node]
  {:id node})

(defn- edge-representation [edge]
  {:source (first edge)
   :target (second edge)
   :cost   "1.0"})

(defn get-representation-of [graph]
  (log/info "get graph representation of graph")
  (r/response (json/write-str {:type     "NetworkGraph",
                               :label    "Ninux Roma",
                               :protocol "OLSR",
                               :version  "0.6.6.2",
                               :metric   "ETX",
                               :nodes    (map node-representation (gs/nodes graph)),
                               :links    (map edge-representation (gs/edges graph))})))

(defn add-node [graph request]
  (let [{parent :parent child :id} (cw/keywordize-keys (rc/form-decode (slurp (:body request))))]
    (log/info (str "add node: " child " to graph"))
    (gs/add-node! graph child)
    (log/info (str "add edge: " parent "->" child))
    (gs/add-edge! graph [parent child]))
  {:status 200})

(defn reset-graph [graph]
  (log/info "reset graph")
  (gs/reset-graph! graph)
  {:status 200})

(defn routes [graph]
  (cpj/routes
    (cpj/GET "/view/" request (view/html))
    (cpj/GET "/view/resources/css/netjsongraph.css/" request (clojure.core/slurp "resources/css/netjsongraph.css"))
    (cpj/GET "/view/resources/js/netjsongraph.js/" request (clojure.core/slurp "resources/libs/netjsongraph.js"))
    (cpj/GET "/view/graph/" request (get-representation-of graph))
    (cpj/POST "/node/" request (add-node graph request))
    (cpj/DELETE "/node/" request (reset-graph graph))))

(defrecord Visualizer [handler graph]
  component/Lifecycle
  (start [_]
    (log/info (str "-> starting Visualizer"))
    (handler/register-handler handler (routes graph)))
  (stop [_]
    (log/info (str "-> stopping Visualizer"))))

(defn new-visualizer []
  (map->Visualizer {}))
