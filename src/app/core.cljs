(ns app.core
  (:require [uix.core :as uix :refer [defui $]]
            [uix.dom]
            ["@uiw/react-codemirror$default" :as CodeMirror]
            ["@codemirror/lang-javascript" :refer [javascript]]
            ["@nextjournal/clojure-mode" :refer [default_extensions, complete_keymap]]
            ["@codemirror/view" :refer [keymap]]))

(defui app []
  (let [[value set-value] (uix/use-state "(fn [a b] (+ a b))")
        onchange (uix/use-callback (fn [val, viewupdate] (set-value val)) [])]
    ($ :div
       ($ :h1 "Hello, UIx!")
       ($ CodeMirror
          {:value value
           :height "200px"
           :extensions
           #js [(.of keymap complete_keymap)
                default_extensions]
           :on-change onchange}))))

(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root
   ($ uix/strict-mode
      ($ app))
   root))

(defn ^:export init []
  (render))
