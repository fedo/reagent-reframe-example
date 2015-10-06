(ns reagent-reframe-example.reframe
  (:require [re-frame.core :as re-frame])
  (:require-macros [reagent.ratom :refer [reaction]]))

(re-frame/register-handler
  :initialise-db             ;; usage: (dispatch [:initialise-db])
  (fn
    [_ _]                   ;; Ignore both params (db and v).
    {:items ["One" "Two" "Three"]
     :text "Text from the app-state!"}))


(re-frame/register-sub
  :items
  (fn [db]
    (reaction (:items @db))))
