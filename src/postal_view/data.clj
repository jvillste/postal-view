(ns postal-view.data
  (:require [clojure.data.csv :as csv]))

(defn read-csv-lines [file-name]
  (with-open [rdr (clojure.java.io/reader file-name)]
    (doall (csv/read-csv rdr))))

(defn load-polygons [file-name]
  (->> (read-csv-lines file-name)
       (reduce (fn [polygons [_ code polygon]]
                 (assoc polygons code polygon))
               {})))
