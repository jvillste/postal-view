(ns postal-view.core
  (require [clojure.string :as string]))

(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (slurp "template.html")
             (string/replace "%kml-url%" "http://www.sirpakauppinen.fi/public/jakelualueet/cta.kml")
             (string/replace "%center-x%" "24.768333")
             (string/replace "%center-y%" "60.269854"))})

