(ns Login
  (:require
   ["jotai" :refer [useAtom]]
   ["preact/hooks" :refer [useState]]
   ["preact-router" :refer [route]]
   [atoms :refer [profile-atom]]))

(defn Login []
  (let [[profile set-profile] (useAtom profile-atom)
        [is-loading set-is-loading] (useState false)
        [error set-error] (useState nil)
        [invalid-email set-invalid-email] (useState nil)
        do-login (^:async fn [e]
                   (try
                     (.preventDefault e)
                     (set-is-loading true)
                     (let [form (.-target e)
                           form-data (js/FormData. form)
                           email (.get form-data "email")
                           password (.get form-data "password")]
                       (when-not (re-matches #"\S+@\S+\.\S+" email)
                         (set-invalid-email true)
                         (throw (Error. "invalid email")))
                       ;; simulate remote call
                       (js-await (Promise.resolve #(setTimeout 1000)))
                       (set-profile {:email email})
                       (console.log "redirecting")
                       (route "/"))
                     (catch Error e
                       (set-error (.-message e)))
                     (finally
                       (set-is-loading false))))]

    #jsx [:section {:class "section"}
          [:div {:class "container"}
           [:div {:class "columns is-centered"}
            [:div {:class "column is-half"}
             [:div {:class "box"}
              [:h1 {:class "title has-text-centered mb-5"} "Login"]
              [:form {:onSubmit do-login}
               [:div {:class "field"}
                [:label {:class "label" :html-for "email"} "Email"]
                [:div {:class "control has-icons-left"}
                 [:input
                  {:class (str "input " (when invalid-email "is-danger"))
                   :default-value (:email profile "")
                   :id "email"
                   :type "text"
                   :name "email"
                   :onChange seq
                   :onChangeCapture seq
                   :placeholder "email"}]
                 [:span {:class "icon is-small is-left"}
                  [:i {:class "fas fa-link"}]]]]

               [:div {:class "field"}
                [:label {:class "label" :html-for "password"} "Password"]
                [:div {:class "control has-icons-left"}
                 [:input
                  {:class "input"
                   :value ""
                   :id "password"
                   :type "password"
                   :required true
                   :name "password"
                   :placeholder "password"}]
                 [:span {:class "icon is-small is-left"}
                  [:i {:class "fas fa-key"}]]]]

               (when error
                 #jsx [:div {:class "notification is-danger is-light"} error])

               [:div {:class "field"}
                [:div {:class "control"}
                 [:button
                  {:disabled is-loading
                   :class (str "button is-primary is-fullwidth "
                               (when is-loading "is-loading"))}
                  "Sign In"]]]]]]]]]))
