(ns Nav
  (:require
   ["jotai" :refer [useAtom]]
   ["preact-router" :refer [Link]]
   ["preact/hooks" :refer [useState]]
   [atoms :refer [profile-atom]]))

(defn Nav []
  (let [[profile] (useAtom profile-atom)
        [is-dropdown-active set-dropdown-active] (useState false)]
    #jsx [:nav {:class "navbar is-primary" :role "navigation" :aria-label "main navigation"}
          [:div {:class "navbar-brand"}
           [:a {:class "navbar-item" :href "/"}
            [:h1 {:class "title is-4 has-text-white"} "Some App"]]]
          [:div {:class "navbar-menu"}
           [:div {:class "navbar-start"}]
           [:div {:class "navbar-end"}
            [:div {:class (str "navbar-item has-dropdown " (when is-dropdown-active "is-active"))
                   :onMouseLeave (fn [] (set-dropdown-active false))}
             [:a {:class "navbar-link"
                  :onClick (fn [] (set-dropdown-active (not is-dropdown-active)))}
              [:span {:class "icon"}
               [:i {:class "fas fa-user"}]]
              [:span (:email profile)]]
             (if (:email profile)
               #jsx [:div {:class "navbar-dropdown"}
                     [:a {:class "navbar-item" :href "/profile"} "Profile"]
                     [:hr {:class "navbar-divider"}]
                     #jsx [Link {:class "navbar-item" :href "/logout"} "Logout"]]
               #jsx [:div {:class "navbar-dropdown"}
                     [Link {:class "navbar-item" :href "/login"} "Login"]])]]]]))