(ns ^:figwheel-always reagent-reframe-example.core
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as reagent :refer [atom]]
    [reagent-reframe-example.data :refer [app-state]]
    [reagent-reframe-example.reframe :as reframe]
    [reagent-reframe-example.routes]))


(enable-console-print!)


(println "Edits to this text should show up in your developer console.")


;; define your app data so that it doesn't get over-written on reload


(re-frame/dispatch [:initialise-db])


(defn header
  []
  (reagent/create-class
    {:reagent-render (fn []
                       (let [current-page (re-frame/subscribe [:current-page])]
                         [:div
                          [:h1 "Reagent + re-frame + secretary"]
                          (into [:h3]
                            (interpose " "
                              (map (fn [[url title]]
                                     [:a {:href url} title]) [["#/" "Home"]
                                                              ["#/public" "Public"]
                                                              ["#/private" "Private"]])))
                          [:div "current-page=" (str @current-page)]]))}))


(defn home
  []
  (reagent/create-class
    {:reagent-render (let [items (re-frame/subscribe [:items])]
                       (fn []
                         [:div [:div "Home"]
                          [:strong "List:"]
                          (when @items
                            (into [:ul]
                              (mapv (fn [item]
                                      [:li (str item)]) @items)))]))}))


(defn public
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div "Public"])}))


(defn private
  []
  (reagent/create-class
    {:reagent-render (fn []
                       [:div "Private"])}))


(defn hello-world []
  [:div
   [header]
   [home]
   [:p "app-state: " (:text @app-state)]])


(reagent/render-component [hello-world]
  (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

