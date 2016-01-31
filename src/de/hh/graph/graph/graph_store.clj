(ns de.hh.graph.graph.graph-store
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.logging :as log]
            [loom.graph :as g]))

(defprotocol Igraph
  (nodes [self])
  (edges [self])
  (add-node [self])
  (add-edge [self]))

(defn create-graph []
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
  (add-node [self])
  (add-edge [self]))

(defn new-graph []
  (map->Graph {}))