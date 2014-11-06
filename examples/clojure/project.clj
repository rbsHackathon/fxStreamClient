(defproject fx-stream-client "1.0.0"
  :description "fx streaming quotes clojure client library"
  :dependencies [[org.clojure/clojure "1.6.0"] [com.pusher/pusher-java-client "0.3.1"]]
  :main ^:skip-aot fx-stream-client.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
