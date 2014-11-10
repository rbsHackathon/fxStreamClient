package com.rbs.de.fxstreamclient;

import java.util.Date;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

/**
 * Example code to read fxEvent messages from pusher.com {@link Channel} and
 * convert the json to an array of {@link FXRateQuote} objects using
 * {@link Gson}.
 */
public class ExampleClient {
  private static final String COMMA = ",";
  private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
  private static final String API_KEY = "ec68fe8cadf14e52b659";
//  private static final String EVENT_NAME = "fxEvent";
//  private static final String CHANNEL_NAME = "fxRateStream";
  private static final String EVENT_NAME = "fxEvent.dev";
  private static final String CHANNEL_NAME = "fxRateStream.dev";
  private static final Gson GSON = new GsonBuilder().setDateFormat(ISO_DATE_FORMAT).create();

  public static void main(String[] args) {
    final Pusher pusher = new Pusher(API_KEY);

    pusher.connect();

    final Channel channel = pusher.subscribe(CHANNEL_NAME);

    channel.bind(EVENT_NAME, new SubscriptionEventListener() {

      public void onEvent(final String channel, final String event, final String data) {
        final FXStreamMessage fxRateQuotes = GSON.fromJson(data, FXStreamMessage.class);

        for (final Map.Entry<String, FXQuote> quote : fxRateQuotes.getFX().entrySet()) {
          System.out.format("%s %s%n", quote.getKey(), quote.getValue());
        }
      }
    });

    // Run for 60 seconds
    try {
      Thread.sleep(60000);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

  private static class FXStreamMessage {
    private static final String COMMA = ",";

    private Date expiryDateTime;
    private Map<String, FXQuote> fx;

    public Map<String, FXQuote> getFX() {
      return fx;
    }

    public Date getExpirtyDateTime() {
      return expiryDateTime;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(getExpirtyDateTime()).append(COMMA);
      for (Map.Entry<String, FXQuote> entry : fx.entrySet()) {
        sb.append(entry.getKey()).append(COMMA).append(entry.getValue());
      }
      return sb.toString();
    }
  }

  private static class FXQuote {
    private double buy;
    private double sell;
    
    public double getBuy() {
      return buy;
    }
    
    public double getSell() {
      return sell;
    }
    
    @Override
    public String toString() {
      return Double.toString(buy) + COMMA + Double.toString(sell);
    }
  }
}
