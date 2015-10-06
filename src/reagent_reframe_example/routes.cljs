(ns reagent-reframe-example.routes
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [re-frame.core :as re-frame]
            [reagent-reframe-example.components :as components]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.History))


(enable-console-print!)


(secretary/set-config! :prefix "#")


(defroute "/public" []
  (re-frame/dispatch [:set-current-page #'components/public]))


(defroute "/private" []
  (re-frame/dispatch [:set-current-page #'components/private]))



;; nop
(defroute "/" []
  (re-frame/dispatch [:set-current-page #'components/home]))
;; page not found
(defroute "*" []
  (println "[routes] *"))


;; Quick and dirty history configuration.
(let [h (History.)]
  (events/listen h EventType/NAVIGATE (fn [%]
                                        (if-let [token (.-token %)]
                                          (do (secretary/dispatch! token)

                                              (set! (.. js/document -body -scrollTop) 0))
                                          (do (println "EventType/NAVIGATE")))))
  (doto h (.setEnabled true)))