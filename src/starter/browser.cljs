(ns starter.browser
  (:require [reagent.core :as r]))

(defonce fibby (r/atom [0 1]))

(defn next-fib [a b]
  (let [sum (+ a b)]
    [b sum]))

(defn fib-component []
  [:div
   [:p "Current fib: "]
   [:h3 (first @fibby)]
   [:input {:type "button" :value "Click me!"
            :on-click #(swap! fibby (partial apply next-fib))}]])


(defn body []
  [:div  
   [:h1 "Hello Reagent World"]
   [:p "A counter example 😀"]
   [fib-component]])

;; start is called by init and after code reloading finishes
(defn ^:dev/after-load start []
  (js/console.log "start")
  (r/render [body] (js/document.getElementById "app")))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; it is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (js/console.log "init")
  (start))

;; this is called before any code is reloaded
(defn ^:dev/before-load stop []
  (js/console.log "stop"))
