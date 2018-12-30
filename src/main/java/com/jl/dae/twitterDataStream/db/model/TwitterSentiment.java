package com.jl.dae.twitterDataStream.db.model;

public class TwitterSentiment {

    private String id;
    private String text;
    private double sentiment;

    public TwitterSentiment(String id, String text, double sentiment) {
        this.id = id;
        this.text = text;
        this.sentiment = sentiment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getSentiment() {
        return sentiment;
    }

    public void setSentiment(double sentiment) {
        this.sentiment = sentiment;
    }
}
