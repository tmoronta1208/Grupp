package com.example.c4q.capstone.network.FourSquareNetwork.model;

import com.google.gson.annotations.SerializedName;

public class Items {
    private String visibility;
    private User user;
    private int height;
    private int width;
    private String suffix;
    private String prefix;
    private Source source;
    private int createdAt;
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
