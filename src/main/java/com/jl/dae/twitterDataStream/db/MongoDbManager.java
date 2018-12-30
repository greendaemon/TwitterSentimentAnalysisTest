package com.jl.dae.twitterDataStream.db;

import com.jl.dae.twitterDataStream.db.model.TwitterSentiment;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.annotation.PostConstruct;

public class MongoDbManager {

    private static final String MONGO_DB_HOST = "localhost";
    private static final int MONGO_DB_PORT = 27017;
    private static final String TWITTER_SENTIMENT_DB_NAME = "twitterSentiment";
    private MongoClient mongoClient;

    @PostConstruct
    public void init() {
        mongoClient = new MongoClient(MONGO_DB_HOST, MONGO_DB_PORT);
    }

    public void writeTwitterData(String tag, TwitterSentiment sentiment) {
        MongoDatabase database = mongoClient.getDatabase(TWITTER_SENTIMENT_DB_NAME);
        MongoCollection<Document> collection = database.getCollection(tag);
        Document twitterDocument = new Document("_id", sentiment.getId())
                .append("text", sentiment.getText())
                .append("sentiment", sentiment.getSentiment());
        collection.insertOne(twitterDocument);
    }
}
