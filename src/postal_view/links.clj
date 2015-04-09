(ns postal-view.links
  (:require [clojure.data.csv :as csv]))

(defn format-code [code-string]
  (format "%05d" (read-string code-string)))

(->> (with-open [rdr (clojure.java.io/reader "numerot.csv")]
       (doall (csv/read-csv rdr)))
     (map first)
     (map #(if (re-matches #"[0-9]+" %)
             (str "=HYPERLINK(\"http://sirpakauppinen.fi:3000/" (format-code %) "\";\"" (format-code %) "\")")
             ""))
     (interpose "\n")
     (apply str)
     (spit "linkit.txt"))
