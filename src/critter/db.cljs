(ns critter.db
  (:require [reagent.core :as reagent]
            [critter.model.swarm :as swarm]))

(defonce *app-state
  (reagent/atom (swarm/init)))

(defn add-app []
  (swap! *app-state swarm/add-app))

(defn app-set [app-id k v]
  (swap! *app-state swarm/app-set app-id k v))

(defn app-change [app-id new-doc]
  (swap! *app-state swarm/app-change app-id new-doc))


;; TODO: maybe use a macro to be less duplicative? pass the ns?
(def mutations
  {:add-app add-app
   :app-set app-set
   :app-change app-change})
