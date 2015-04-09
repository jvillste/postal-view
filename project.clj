(defproject postal-view "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.2"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [compojure "1.3.3"]]
                 [org.clojure/data.csv "0.1.2"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler postal-view.core/handler}
  :main postal-view.main)
