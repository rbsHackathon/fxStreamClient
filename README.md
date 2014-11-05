# fxStream

## Streaming RBS FX Rates for the Deloitte GoneHacking Hackathon


Here we have example code to hook up to streaming FX rates from RBS via [`pusher.com`](http://pusher.com).

Clients available right now:

* html/javascript

### Message format

    [
      {
        "sell": "AED",
        "buy": "DKK",
        "rate": 1.5920476605,
        "valueDate": "2014-11-10T05:00:00.000Z",
        "rateQuoteId": 13952852727,
        "expiryDateTime": "2014-11-05T12:18:59.000Z"
      },
      {
        "sell": "USD",
        "buy": "JPY",
        "rate": 112.51339605,
        "valueDate": "2014-11-07T05:00:00.000Z",
        "rateQuoteId": 13952852728,
        "expiryDateTime": "2014-11-05T12:18:59.000Z"
      }
    ]
