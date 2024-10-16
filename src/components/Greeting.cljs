(ns Greeting
  (:require
   ["jotai" :as jotai :refer [useAtom]]
   [atoms :as atoms]))

(defn Greeting []
  (let [[name set-name] (useAtom atoms/name-atom)]
    #jsx [:div {:class "section"}
          [:h1 "Hello, " name]
          [:input
           {:class "input"
            :type "text"
            :placeHolder "Enter your name"
            :value name
            :onInput #(set-name (-> % .-target .-value))}]]))