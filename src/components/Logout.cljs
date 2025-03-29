(ns Logout
  (:require
   ["jotai" :as jotai]
   ["preact-router" :refer [route]]
   [atoms :as atoms]))

(defn Logout []
  (let [[_ set-profile] (jotai/useAtom atoms/profile-atom)]
    (set-profile {})
    (route "/")))