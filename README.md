# fxStream

## Streaming RBS FX Rates for the Deloitte [GoneHacking](http://gonehacking.com) Hackathon

We have provided example code to hook up to a stream of FX rates from RBS via [`pusher.com`](http://pusher.com).

Clients available at present:

* html/javascript
* ruby
* java
* clojure
* python

### Instructions

Clone this repo and run an example. 

If you have any questions or want to report a feature request or bug, please raise issues on this project. We will be tracking these and responding accordingly.

### Message format

An example snippet from the messages received is as follows:

```
  {
      "expiryDateTime": "2014-11-10T15:53:26.000Z",
      "fx": {
          "EUR/CNY": {
              "buy": "7.5433",
              "sell": "7.7791"
          },
          "EUR/USD": {
              "buy": "1.2255",
              "sell": "1.2633"
          }
          // ...
     }
  }
```

The `fx` property is a map of curency pairs to rate quotes. Each pair is defined as **base**/**quote**. The rate quote field definitions are as follows:

**buy:** the amount of the **quote** currency received by the client in exchange for one unit of the **base** currency.

**sell:** the amount of the **quote** currency given by the client in exchange for one unit of the **base** currency.

### Ruby Client

This uses Logan Koester's [`https://github.com/pusher/pusher-ruby-client`](pusher-ruby-client).  More examples there.  
Note that you need to

    gem install pusher-client

...to get this to work.

### Java Client

You'll need maven to build the sample code. It uses the [offical pusher.com java client](https://github.com/pusher/pusher-java-client) and [Gson](https://code.google.com/p/google-gson/) for json parsing.

### Clojure Client

An example Clojure client that simply prints out the data on receipt of an event. This code uses the same official java pusher client as the java example above.

### Python Client

An example Python client that parses the data to a python objct. This code uses the client library from Erik Kulyk. Instructions for build and use provided in the respective directory.
