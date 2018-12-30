package com.jl.dae.twitterDataStream.client.model;

import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("created_at")
    private String createionDate;

    @SerializedName("id")
    private String id;

    @SerializedName("id_str")
    private String idString;

    @SerializedName("text")
    private String text;

    @SerializedName("source")
    private String source;

    @SerializedName("truncated")
    private boolean truncated;

    @SerializedName("in_reply_to_status_id")
    private String replyToStatus;

    @SerializedName("in_reply_to_status_id_str")
    private String replyToStatusString;

    @SerializedName("in_reply_to_user_id")
    private String replyToUser;

    @SerializedName("in_reply_to_user_id_str")
    private String replyToUserString;

    @SerializedName("user")
    private User user;

    public String getCreateionDate() {
        return createionDate;
    }

    public String getId() {
        return id;
    }

    public String getIdString() {
        return idString;
    }

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public String getReplyToStatus() {
        return replyToStatus;
    }

    public String getReplyToStatusString() {
        return replyToStatusString;
    }

    public String getReplyToUser() {
        return replyToUser;
    }

    public String getReplyToUserString() {
        return replyToUserString;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "createionDate='" + createionDate + '\'' +
                ", id='" + id + '\'' +
                ", idString='" + idString + '\'' +
                ", text='" + text + '\'' +
                ", source='" + source + '\'' +
                ", truncated=" + truncated +
                ", replyToStatus='" + replyToStatus + '\'' +
                ", replyToStatusString='" + replyToStatusString + '\'' +
                ", replyToUser='" + replyToUser + '\'' +
                ", replyToUserString='" + replyToUserString + '\'' +
                ", user=" + user +
                '}';
    }
}
