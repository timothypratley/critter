(ns critter.main
  (:require [reagent.dom :as rd]
            [critter.view.main :as critter]
            [critter.db :as db]))

(defn hello-world []
  [critter/<main> @db/*app-state db/mutations])

(defn init []
  (rd/render [hello-world] (js/document.getElementById "app")))

(init)

(defn on-js-reload [])
