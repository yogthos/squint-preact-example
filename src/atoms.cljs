(ns atoms
  (:require
   ["jotai" :as jotai]
   ["jotai/utils" :as jotai-utils]))

(def profle-key "profile")
(def profile-atom (jotai-utils/atomWithStorage profle-key {}))

(def count-atom (jotai/atom 0))
(def name-atom (jotai/atom ""))

(defn clear-storage [storage-key]
  (localStorage.removeItem storage-key))