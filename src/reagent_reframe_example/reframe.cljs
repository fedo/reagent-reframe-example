(ns reagent-reframe-example.reframe
  (:require [re-frame.core :as re-frame])
  (:require-macros [reagent.ratom :refer [reaction]]))


(re-frame/register-handler
  :set-current-page
  (fn [app-state [_ page]]
    (assoc app-state :current-page page)))

(re-frame/register-handler
  :initialise-db
  (fn
    [_ _]
    {:current-page nil
     :items        ["One" "Two" "Three"]
     :text         "Text from the app-state!"}))


(re-frame/register-sub
  :items
  (fn [db]
    (reaction (:items @db))))


(re-frame/register-sub
  :current-page
  (fn [db]
    (reaction (:current-page @db))))
