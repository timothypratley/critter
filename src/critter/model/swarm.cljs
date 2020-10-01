(ns critter.model.swarm
  (:require [meander.epsilon :as m]
            [meander.strategy.epsilon :as s]))

(defn init []
  {:apps {}})

(defn add-app [app-state]
  (let [doc (js/Automerge.init)
        actor-id (js/Automerge.getActorId doc)]
    (update app-state :apps assoc actor-id
            {:net :online
             :doc doc})))

(defn app-set [app-state app-id k v]
  (assoc-in app-state [:apps app-id k] v))

(defn maybe-apply-changes [app new-doc]
  (m/rewrite app
    {:net :online
     :doc ?doc
     &    ?more}
    {:net :online
     :doc ~(js/Automerge.merge ?doc new-doc)
     &    ?more}

    ?app
    ?app))

(defn broadcast [app-state actor-id new-doc]
  (m/rewrite app-state
    {:apps {~actor-id ?app
            &         (m/seqable [!actor-id !app] ...)}
     &     ?more}
    {:apps {~actor-id ?app
            &         (m/seqable [!actor-id (m/app maybe-apply-changes !app ~new-doc)] ...)}
     &     ?more}))

(defn app-change [app-state actor-id f]
  (let [{:keys [doc net]} (get-in app-state [:apps actor-id])
        new-doc (js/Automerge.change doc f)]
    (-> app-state
        (assoc-in [:apps actor-id :doc] new-doc)
        (cond-> (= net :online)
                (broadcast actor-id new-doc)))))
