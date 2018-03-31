package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Groups {
    @SerializedName("items")
    private List<String> items;
    @SerializedName("count")
    private int count;
    @SerializedName("type")
    private String type;

    public List<String> getItems() {
        return items;
    }

    public int getCount() {
        return count;
    }

    public String getType() {
        return type;
    }
}
