# fxStream

## Streaming RBS FX Rates for the Deloitte GoneHacking Hackathon


Here we have example code to hook up to streaming FX rates from RBS via [`pusher.com`](http://pusher.com).

Clients available right now:

* html/javascript
* ruby
* java

### Message format

    [
      {
        "sell": "AED",
        "buy": "DKK",
        "rate": 1.5920476605,
        "expiryDateTime": "2014-11-05T12:18:59.000Z"
      },
      {
        "sell": "USD",
        "buy": "JPY",
        "rate": 112.51339605,
        "expiryDateTime": "2014-11-05T12:18:59.000Z"
      }
    ]

### Ruby Client

This uses Logan Koester's [`https://github.com/pusher/pusher-ruby-client`](pusher-ruby-client).  More examples there.  
Note that you need to

    gem install pusher-client

...to get this to work.

### Java Client

You'll need maven to build the sample code. It uses the [offical pusher.com java client](https://github.com/pusher/pusher-java-client) and [Gson](https://code.google.com/p/google-gson/) for json parsing.