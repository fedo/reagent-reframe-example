(ns reagent-reframe-example.routes
  (:require [goog.events :as events]
            [goog.history.EventType :as EventType]
            [reagent-reframe-example.data :as data]
            [secretary.core :as secretary :refer-macros [defroute]])
  (:import goog.History))


(secretary/set-config! :prefix "#")



;; nop
(defroute "/" []
          )
;; page not found
(defroute "*" [] (go-to-path "#/"))


;; Quick and dirty history configuration.
(let [h (History.)]
  (events/listen h EventType/NAVIGATE (fn [%]
                                        (if-let [token (.-token %)]
                                          (do (secretary/dispatch! token)

                                              (set! (.. js/document -body -scrollTop) 0))
                                          (do (println "EventType/NAVIGATE")))))
  (doto h (.setEnabled true)))