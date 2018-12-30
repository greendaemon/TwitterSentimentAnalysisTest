package com.jl.dae.twitterDataStream.kafka;

import com.jl.dae.twitterDataStream.analysis.SentimentAnalysis;
import com.jl.dae.twitterDataStream.db.MongoDbManager;
import com.jl.dae.twitterDataStream.db.model.TwitterSentiment;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

public class TwitterConsumer {

    private static final Logger LOG = Logger.getLogger(TwitterConsumer.class.getName());

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SentimentAnalysis sentimentAnalysis = (SentimentAnalysis) ctx.getBean("sentimentAnalysis");
        MongoDbManager dbManager = (MongoDbManager) ctx.getBean("mongoDbManager");
        Properties configProperties = new Properties();
        configProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        configProperties.put(ConsumerConfig.CLIENT_ID_CONFIG, "simple");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(configProperties);
        kafkaConsumer.subscribe(Arrays.asList("NY", "LA"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    Double twitterSentimentScore = sentimentAnalysis.getSentiment(record.value());
                    LOG.info(String.format("Tweet sentiment received %s %s %f", record.topic(), record.value(), twitterSentimentScore));
                    dbManager.writeTwitterData(record.topic(), new TwitterSentiment(record.key(), record.value(), twitterSentimentScore));
                }
            }
        } catch (WakeupException e) {
            System.out.println(e.getMessage());
        } finally {
            kafkaConsumer.close();
        }
    }
}
