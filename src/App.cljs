(ns App
  (:require
   ["bulma/css/bulma.min.css"]
   ["@fortawesome/fontawesome-free/css/all.min.css"]
   ["jotai" :as jotai]
   ["preact" :as preact]
   ["preact-router" :as router :refer [route]]
   [components/About :refer [About]]
   [components/Home :refer [Home]]
   [components/Login :refer [Login]]
   [components/Logout :refer [Logout]]
   [components/Nav :refer [Nav]]
   [components/Profile :refer [Profile]]
   [atoms :as atoms]))

(defn NotFound []
  #jsx [:div {:class "box"}
        [:h1 "404 Not Found"]])

(defn ProtectedRoute [{:keys [component]}]
  (let [[profile] (jotai/useAtom atoms/profile-atom)]
    (when (empty? profile)
      (route "/login"))
    (component)))

(defn App []
  #jsx [:div
        [Nav]
        [router/Router
         [ProtectedRoute {:path "/" :component Home}]
         [router/Route {:path "/about" :component About}]
         [router/Route {:path "/login":component Login}]
         [router/Route {:path "/logout" :component Logout}]
         [ProtectedRoute {:path "/profile" :component Profile}]
         [NotFound {:type "404" :default true}]]
        [:footer {:class "footer"}
         [:div {:class "content has-text-centered"}
          [:p [:strong "some app"]]]]])


(preact/render #jsx [App] (js/document.querySelector "#app"))