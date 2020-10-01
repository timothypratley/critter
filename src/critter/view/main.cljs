(ns critter.view.main)

(defn <doc> [doc]
  (pr-str doc))

(defn <app> [actor-id app mutations]
  (let [{:keys [net doc]} app
        {:keys [app-set app-change]} mutations]
    [:div
     [:div
      [:div "actor-id: " [:span {:style {:color "blue"}} actor-id]]
      [:div "net: " [:span {:style {:color (if (= net :online) "green" "red")}} (name net)]
       [:button {:on-click (fn [e]
                             (app-set actor-id :net (if (= :offline net)
                                                      :online
                                                      :offline)))}
        "toggle"]]]
     [<doc> (js->clj doc)]
     [:div
      [:textarea
       {:style     {:width "100%"}
        :value     (.-text doc)
        :on-change (fn [e]
                     (app-change actor-id
                                 (fn [d]
                                   (set! (.-text d) (-> e (.-target) (.-value))))))}]]]))

(defn <main> [model mutations]
  [:div
   [:h2 "Critter"]
   [:button {:on-click (fn [e]
                         ((:add-app mutations)))}
    "+ Add app"]
   [:div {:style {:display "flex"}}
    (doall
      (for [[actor-id app] (:apps model)]
        [:div {:key   actor-id
               :style {:order     0
                       :flex-grow 1
                       :width     "100%"
                       :border    "1px solid black"
                       :padding   "10px"}}
         [<app> actor-id app mutations]]))]])
