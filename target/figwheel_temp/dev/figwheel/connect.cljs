(ns figwheel.connect (:require [reagent-reframe-example.core] [figwheel.client] [figwheel.client.utils]))
(figwheel.client/start {:on-jsload (fn [& x] (if js/reagent-reframe-example.core.on-js-reload (apply js/reagent-reframe-example.core.on-js-reload x) (figwheel.client.utils/log :debug "Figwheel: :on-jsload hook 'reagent-reframe-example.core/on-js-reload' is missing"))), :build-id "dev", :websocket-url "ws://localhost:3449/figwheel-ws"})

