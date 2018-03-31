package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}
