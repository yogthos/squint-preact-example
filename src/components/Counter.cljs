(ns Counter
  (:require
   ["jotai" :as jotai :refer [useAtom]]
   [atoms :as atoms]))

(defn Counter []
  (let[[count set-count] (useAtom atoms/count-atom)]
    #jsx [:div {:class "section"}
          [:h2 "Count: " count]
          [:button {:class "button"
                    :onClick #(set-count (inc count))}
           "Increment"]
          [:button {:class "button"
                    :onClick #(set-count (dec count))}
           "Decrement"]
          [:button {:class "button"
                    :onClick #(set-count 0)}
           "Reset"]]))