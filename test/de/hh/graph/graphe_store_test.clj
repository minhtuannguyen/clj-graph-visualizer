(ns de.hh.graph.graphe-store-test
  (:require [clojure.test :refer :all]
            [de.hh.graph.graph.graph-store :as gs]
            [com.stuartsierra.component :as component]))

(deftest ^:focused gs-test
  (testing "test graph store functionalities"
    (let [graph-store (component/start (gs/new-graph))]
      (is (= #{"root"} (gs/nodes graph-store)))
      (is (= (list) (gs/edges graph-store)))

      (let [_ (gs/add-node! graph-store "node1")
            _ (gs/add-edge! graph-store ["root", "node1"])]
        (is (= #{"root", "node1"} (gs/nodes graph-store)))
        (is (= (list ["root", "node1"]) (gs/edges graph-store)))

        (let [_ (gs/add-node! graph-store "node2")
              _ (gs/add-edge! graph-store ["root", "node2"])
              ]
          (is (= #{"root", "node1", "node2"} (gs/nodes graph-store)))
          (is (= (list ["root", "node1"] ["root", "node2"]) (gs/edges graph-store)))))

      (component/stop graph-store))))
