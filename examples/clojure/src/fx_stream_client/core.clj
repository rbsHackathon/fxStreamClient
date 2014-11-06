(ns fx-stream-client.core
  (:gen-class))

(import com.pusher.client.Pusher)

(import com.pusher.client.channel.SubscriptionEventListener)

(def callback (proxy [SubscriptionEventListener] [] (onEvent [channel event data] (println data))))

(def pusher (Pusher. "ec68fe8cadf14e52b659"))

(.connect pusher)

(def channel (.subscribe pusher "fxRateStream"))

(.bind channel "fxEvent" callback)

(.disconnect pusher)
