(ns postal-view.main
  (:require [ring.adapter.jetty :as jetty]
            [postal-view.core :as core]))

(defn -main [& args]
  (jetty/run-jetty core/handler {:port (read-string (first args))}))


(comment
  (.start (Thread. (fn [] (-main "3000")))))


