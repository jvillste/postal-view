(ns postal-view.core
  (require [clojure.string :as string]
           [compojure.core :as compojure]
           [compojure.route :as route]
           [postal-view.data :as data]
           [ring.middleware.params :as params]))

(defn polygon-coordinates [polygon-string]
  (partition 3 (read-string (str "["
                                 (second (re-find #"<coordinates>(.*)</coordinates>" polygon-string))
                                 "]"))))

(defn polygon-middle-point [polygon-string]
  (->> (polygon-coordinates polygon-string)
       (reduce (fn [[center-x center-y] [x y z]]
                 [(/ (+ center-x x)
                     2)
                  (/ (+ center-y y)
                     2)]))))

(defn app [polygons]
  (compojure/routes (compojure/GET "/kml/:code" [code]  (-> (slurp "template.kml")
                                                            (string/replace "%polygon%" (get polygons code))))
                    (compojure/GET "/:code" [code] (let [[x y] (polygon-middle-point (get polygons code))]
                                                     
                                                     (-> (slurp "template.html")
                                                         (string/replace "%kml-url%" #_"http://www.sirpakauppinen.fi/public/jakelualueet/cta.kml" (str "http://www.sirpakauppinen.fi:3000/kml/" code))
                                                         (string/replace "%center-x%" "24.768333" #_(str (format "%.6f" (float x))))
                                                         (string/replace "%center-y%" "60.269854" #_(str (format "%.6f" (float y)))))))))

(def handler (app (data/load-polygons "Postinumerot_20150102.csv")))
