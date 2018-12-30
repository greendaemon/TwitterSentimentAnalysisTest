package com.jl.dae.twitterDataStream.client.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("friends_count")
    public int friends_count;

    @SerializedName("profile_background_color")
    public String profile_background_color;

    @SerializedName("profile_background_image_url")
    public String profile_background_image_url;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("description")
    public String description;

    @SerializedName("favourites_count")
    public int favourites_count;

    @SerializedName("lang")
    public String lang;

    @SerializedName("notifications")
    public boolean notifications;

    @SerializedName("id_str")
    public String id_str;

    @SerializedName("default_profile_image")
    public boolean default_profile_image;

    @SerializedName("profile_text_color")
    public String profile_text_color;

    @SerializedName("default_profile")
    public boolean default_profile;

    @SerializedName("show_all_inline_media")
    public boolean show_all_inline_media;

    @SerializedName("contributors_enabled")
    public boolean contributors_enabled;

    @SerializedName("geo_enabled")
    public boolean geo_enabled;

    @SerializedName("screen_name")
    public String screen_name;

    @SerializedName("profile_sidebar_fill_color")
    public String profile_sidebar_fill_color;

    @SerializedName("profile_image_url")
    public String profile_image_url;

    @SerializedName("profile_background_tile")
    public boolean profile_background_tile;

    @SerializedName("follow_request_sent")
    public boolean follow_request_sent;

    @SerializedName("url")
    public String url;

    @SerializedName("statuses_count")
    public int statuses_count;

    @SerializedName("following")
    public boolean following;

    @SerializedName("time_zone")
    public String time_zone;

    @SerializedName("profile_link_color")
    public String profile_link_color;

    @SerializedName("protected")
    public boolean protectedd;

    @SerializedName("verified")
    public boolean verified;

    @SerializedName("profile_sidebar_border_color")
    public String profile_sidebar_border_color;

    @SerializedName("followers_count")
    public int followers_count;

    @SerializedName("location")
    public String location;

    @SerializedName("name")
    public String name;

    @SerializedName("is_translator")
    public boolean is_translator;

    @SerializedName("id")
    public long id;

    @SerializedName("listed_count")
    public int listed_count;

    @SerializedName("profile_use_background_image")
    public boolean profile_use_background_image;

    @SerializedName("utc_offset")
    public int utc_offset;

    public int getFriends_count() {
        return friends_count;
    }

    public String getProfile_background_color() {
        return profile_background_color;
    }

    public String getProfile_background_image_url() {
        return profile_background_image_url;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getDescription() {
        return description;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public String getLang() {
        return lang;
    }

    public boolean isNotifications() {
        return notifications;
    }

    public String getId_str() {
        return id_str;
    }

    public boolean isDefault_profile_image() {
        return default_profile_image;
    }

    public String getProfile_text_color() {
        return profile_text_color;
    }

    public boolean isDefault_profile() {
        return default_profile;
    }

    public boolean isShow_all_inline_media() {
        return show_all_inline_media;
    }

    public boolean isContributors_enabled() {
        return contributors_enabled;
    }

    public boolean isGeo_enabled() {
        return geo_enabled;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public String getProfile_sidebar_fill_color() {
        return profile_sidebar_fill_color;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public boolean isProfile_background_tile() {
        return profile_background_tile;
    }

    public boolean isFollow_request_sent() {
        return follow_request_sent;
    }

    public String getUrl() {
        return url;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public boolean isFollowing() {
        return following;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public String getProfile_link_color() {
        return profile_link_color;
    }

    public boolean isProtectedd() {
        return protectedd;
    }

    public boolean isVerified() {
        return verified;
    }

    public String getProfile_sidebar_border_color() {
        return profile_sidebar_border_color;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public boolean isIs_translator() {
        return is_translator;
    }

    public long getId() {
        return id;
    }

    public int getListed_count() {
        return listed_count;
    }

    public boolean isProfile_use_background_image() {
        return profile_use_background_image;
    }

    public int getUtc_offset() {
        return utc_offset;
    }
}
