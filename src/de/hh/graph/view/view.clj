(ns de.hh.graph.view.view
  (:require [net.cgrand.enlive-html :as html]))

(html/deftemplate view "html/view.html"
                  []
                  [:head :title] (html/content "Graph Visualizer"))

(defn html []
  (reduce str (view)))

