(ns Profile
  (:require
   ["jotai" :refer [useAtom]]
   [atoms :refer [profile-atom]]))

(defn Profile []
  (let [[profile] (useAtom profile-atom)]
    #jsx [:section {:class "section"}
          [:div {:class "container"}
           [:div {:class "content"}
            [:div {:class "box"}
             [:div [:b "User: "] (:email profile)]]]]]))