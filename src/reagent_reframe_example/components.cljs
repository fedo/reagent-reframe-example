(ns reagent-reframe-example.components
  (:require
    [re-frame.core :as re-frame]
    [reagent.core :as reagent :refer [atom]]
    [reagent-reframe-example.reframe :as reframe]))



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
