(ns ^:figwheel-always reagent-reframe-example.core
    (:require
              [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(def app-state (atom {:text "Text from the app-state!"}))

(defn header
  []
  [:h1 "Reagent + re-frame + secretary"])

(defn home
  []
  [:div "Home"])

(defn public
  []
  [:div "public"])

(defn private
  []
  [:div "public"])

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

