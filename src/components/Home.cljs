(ns Home
  (:require
   [components/Counter :refer [Counter]]))

(defn Home []
  #jsx [:section {:class "section"}
        [:div {:class "container"}
         [:div {:class "content"}
          [:h3 {:class "title is-3"} "My App"]
          [:div {:class "box"}
           [Counter]]]]])