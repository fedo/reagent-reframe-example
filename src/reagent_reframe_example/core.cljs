(ns ^:figwheel-always reagent-reframe-example.core
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as reagent :refer [atom]]
    [reagent-reframe-example.components :as components]
    [reagent-reframe-example.data :refer [app-state]]
    [reagent-reframe-example.reframe :as reframe]
    [reagent-reframe-example.routes]))


(enable-console-print!)


(println "Edits to this text should show up in your developer console.")


(re-frame/dispatch [:initialise-db])


(defn header
  []
  (reagent/create-class
    {:reagent-render (fn []

                       [:div
                        [:h1 "Reagent + re-frame + secretary"]
                        (into [:h3]
                          (interpose " "
                            (map (fn [[url title]]
                                   [:a {:href url} title]) [["#/" "Home"]
                                                            ["#/public" "Public"]
                                                            ["#/private" "Private"]])))])}))



(defn hello-world []
  (reagent/create-class
    {:componentWillMount (fn []
                           (re-frame/dispatch [:set-current-page #'components/home]))
     :reagent-render     (fn []
                           (let [current-page (re-frame/subscribe [:current-page])]
                             [:div
                              [header]
                              (when @current-page
                                [@current-page])
                              [:p "app-state: " (:text @app-state)]]))}))


(reagent/render-component [hello-world]
  (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  )

