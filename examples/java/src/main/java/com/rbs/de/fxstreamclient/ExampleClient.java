package com.rbs.de.fxstreamclient;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

/**
 * Example code to read fxEvent messages from pusher.com {@link Channel} and convert
 * the json to an array of {@link FXRateQuote} objects using {@link Gson}.
 */
public class ExampleClient {

	private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	private static final String API_KEY = "ec68fe8cadf14e52b659";
	private static final String CHANNEL_NAME = "fxRateStream";
	private static final String EVENT_NAME = "fxEvent";
	private static final Gson GSON = new GsonBuilder().setDateFormat(
			ISO_DATE_FORMAT).create();

	public static void main(String[] args) {
		final Pusher pusher = new Pusher(API_KEY);

		pusher.connect();

		final Channel channel = pusher.subscribe(CHANNEL_NAME);

		channel.bind(EVENT_NAME, new SubscriptionEventListener() {

			public void onEvent(final String channel, final String event,
					final String data) {
				final FXRateQuote[] fxRateQuotes = GSON.fromJson(data,
						FXRateQuote[].class);

				for (final FXRateQuote quote : fxRateQuotes) {
					System.out.println(quote);
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

	private static class FXRateQuote {
		
		private static final String COMMA = ",";

		private String sell;
		private String buy;
		private double rate;
		private Date expiryDateTime;

		public String getSell() {
			return sell;
		}

		public String getBuy() {
			return buy;
		}

		public double getRate() {
			return rate;
		}

		public Date getExpiryDateTime() {
			return expiryDateTime;
		}

		@Override
		public String toString() {
			return getBuy() + COMMA + getSell() + COMMA + getRate() + COMMA
					+ getExpiryDateTime();
		}
	}
}