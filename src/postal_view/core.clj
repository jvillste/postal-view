(ns postal-view.core
  (require [clojure.string :as string]
           [compojure.core :as compojure]
           [compojure.route :as route]))

(compojure/defroutes handler
  (compojure/GET "/" [] (-> (slurp "template.html")
                            (string/replace "%kml-url%" #_"http://www.sirpakauppinen.fi/public/jakelualueet/cta.kml" "http://www.sirpakauppinen.fi:3000/kml")
                            (string/replace "%center-x%" "24.768333")
                            (string/replace "%center-y%" "60.269854")))
  (compojure/GET "/kml" [] (-> (slurp "template.kml")
                               (string/replace "%polygon%" "<Polygon><outerBoundaryIs><LinearRing><coordinates>24.768333,60.269854,0.0 24.774556,60.272557,0.0 24.780478,60.273834,0.0 24.789705,60.273706,0.0 24.797559,60.273089,0.0 24.803953,60.27394,0.0 24.805369,60.270982,0.0 24.807601,60.266555,0.0 24.796314,60.264065,0.0 24.79228,60.257306,0.0 24.785049,60.259339,0.0 24.778891,60.261127,0.0 24.773076,60.258945,0.0 24.767046,60.26498,0.0 24.768956,60.26796,0.0</coordinates></LinearRing></outerBoundaryIs></Polygon>")
                            (string/replace "%center-x%" "24.768333")
                            (string/replace "%center-y%" "60.269854"))))

#_(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (-> (slurp "template.html")
             (string/replace "%kml-url%" "http://www.sirpakauppinen.fi/public/jakelualueet/cta.kml")
             (string/replace "%center-x%" "24.768333")
             (string/replace "%center-y%" "60.269854"))})

