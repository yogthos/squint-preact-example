(ns App
  (:require
   ["preact" :as preact]
   [components/Counter :as Counter]
   [components/Greeting :as Greeting]))

(defn App []
  #jsx [:div {:class "app"}
        [:h1 {:class "title"} "Jotai Preact Example"]
        [Greeting/Greeting]
        [:hr]
        [Counter/Counter]])

(preact/render #jsx [App] (js/document.querySelector "#app"))