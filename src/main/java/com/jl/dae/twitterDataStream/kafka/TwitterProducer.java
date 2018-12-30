package com.jl.dae.twitterDataStream.kafka;

import com.jl.dae.twitterDataStream.client.TwitterFollowingStreamClient;
import com.jl.dae.twitterDataStream.client.model.Tweet;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class TwitterProducer {

    private static final Logger LOG = Logger.getLogger(TwitterProducer.class.getName());

    private static final long TWEET_STREAM_INTERVAL_SEC = 5;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer<String, String>(configProperties);
        //TODO make a factory-kit or factory method
        TwitterFollowingStreamClient nyStreamClient = (TwitterFollowingStreamClient) ctx.getBean("nyTwitterClient");
        TwitterFollowingStreamClient laStreamClient = (TwitterFollowingStreamClient) ctx.getBean("laTwitterClient");
        nyStreamClient.connect();
        laStreamClient.connect();

        executor.scheduleAtFixedRate(() -> {
            Tweet nextNYTweet = nyStreamClient.read();
            Tweet nextLATweet = laStreamClient.read();
            if (nextNYTweet != null) {
                //TODO Clean string and move to separate interface/class
                ProducerRecord<String, String> rec = new ProducerRecord<String, String>("NY", nextNYTweet.getId(), nextNYTweet.getText());
                producer.send(rec);
            }
            if (nextLATweet != null) {
                //TODO Clean string and move to separate interface/class
                ProducerRecord<String, String> rec = new ProducerRecord<String, String>("LA", nextLATweet.getId(), nextLATweet.getText());
                producer.send(rec);
            }
        }, 0, TWEET_STREAM_INTERVAL_SEC, TimeUnit.SECONDS);
    }
}
