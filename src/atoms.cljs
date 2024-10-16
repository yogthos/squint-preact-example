(ns atoms
  (:require ["jotai" :as jotai]))

(def count-atom (jotai/atom 0))
(def name-atom (jotai/atom ""))