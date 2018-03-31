package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.google.gson.annotations.SerializedName;

public class Items {
    @SerializedName("visibility")
    private String visibility;
    @SerializedName("user")
    private User user;
    @SerializedName("height")
    private int height;
    @SerializedName("width")
    private int width;
    @SerializedName("suffix")
    private String suffix;
    @SerializedName("prefix")
    private String prefix;
    @SerializedName("source")
    private Source source;
    @SerializedName("createdAt")
    private int createdAt;
    @SerializedName("id")
    private String id;

    public String getVisibility() {
        return visibility;
    }

    public User getUser() {
        return user;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getPrefix() {
        return prefix;
    }

    public Source getSource() {
        return source;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public String getId() {
        return id;
    }
}
