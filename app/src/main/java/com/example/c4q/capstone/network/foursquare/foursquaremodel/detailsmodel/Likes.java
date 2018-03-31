package com.example.c4q.capstone.network.foursquare.foursquaremodel.detailsmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Likes {
    @SerializedName("summary")
    private String summary;
    @SerializedName("groups")
    private List<Groups> groups;
    @SerializedName("count")
    private int count;

    public String getSummary() {
        return summary;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public int getCount() {
        return count;
    }
}
