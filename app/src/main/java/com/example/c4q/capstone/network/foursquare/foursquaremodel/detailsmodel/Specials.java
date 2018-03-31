package com.example.c4q.capstone.network.foursquare.foursquaremodel.detailsmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Specials {
    @SerializedName("items")
    private List<String> items;
    @SerializedName("count")
    private int count;

    public List<String> getItems() {
        return items;
    }

    public int getCount() {
        return count;
    }
}
