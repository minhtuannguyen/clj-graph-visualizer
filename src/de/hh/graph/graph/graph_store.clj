(ns de.hh.graph.graph.graph-store
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [loom.graph :as g]))

(defprotocol Igraph
  (nodes [self])
  (edges [self])
  (reset-graph! [self])
  (add-node! [self node])
  (add-edge! [self edge]))

(defn- create-graph []
  (let [graph (g/digraph)]
    (g/add-nodes graph "root")))

(defrecord Graph []
  component/Lifecycle
  (start [self]
    (log/info "starting Graph")
    (let [self (assoc self :graph (atom (create-graph)))]
      self))
  (stop [_]
    (log/info "stoping  Graph"))

  Igraph
  (nodes [self] (g/nodes @(:graph self)))
  (edges [self] (g/edges @(:graph self)))
  (reset-graph! [self] (reset! (:graph self) (create-graph)))
  (add-node! [self node]
    (swap! (:graph self) g/add-nodes node))
  (add-edge! [self edge]
    (swap! (:graph self) g/add-edges edge)))

(defn new-graph []
  (map->Graph {}))