package com.jl.dae.twitterDataStream.client;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.jl.dae.twitterDataStream.client.model.Tweet;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.Location;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.AbstractProcessor;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TwitterFollowingStreamClient {

    private static final String CLIENT_NAME = "Stream-Client-Host";
    private static final Logger LOG = Logger.getLogger(TwitterFollowingStreamClient.class.getName());

    private final Gson gson = new GsonBuilder().serializeNulls().create();

    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<String>();
    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();
    private List<String> followString;
    private OAuth1 authentication;
    private Client twitterClient;

    @PostConstruct
    public void init() {
        Hosts hostList = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint filterEndpoint = new StatusesFilterEndpoint();
        filterEndpoint.trackTerms(followString);
        twitterClient = new ClientBuilder()
                .name(CLIENT_NAME)
                .hosts(hostList)
                .authentication(authentication)
                .endpoint(filterEndpoint)
                .processor(new StringDelimitedProcessor(messageQueue))
                .eventMessageQueue(eventQueue)
                .build();
    }

    public void connect() {
        if (twitterClient == null) {
            LOG.log(Level.WARNING, "Twitter client not initialized - unable to connect");
        } else {
            twitterClient.connect();
        }
    }

    public void disconnect() {
        if (twitterClient == null) {
            LOG.log(Level.WARNING, "Twitter client not initialized - unable to disconnect");
        } else {
            twitterClient.stop();
        }
    }

    public Tweet read() {
        try {
            return gson.fromJson(messageQueue.take(), Tweet.class);
        } catch (InterruptedException e) {
            LOG.log(Level.WARNING, e.getMessage());
            return null;
        } catch (JsonSyntaxException e) {
            LOG.log(Level.WARNING, e.getMessage());
            return null;
        }
    }

    public void setFollowString(List<String> followString) {
        this.followString = followString;
    }

    public void setAuthentication(OAuth1 authentication) {
        this.authentication = authentication;
    }
}
