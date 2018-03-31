package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.google.gson.annotations.SerializedName;

public class Notifications {
    @SerializedName("item")
    private Item item;
    @SerializedName("type")
    private String type;

    public Item getItem() {
        return item;
    }

    public String getType() {
        return type;
    }
}
