package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HereNow {
    @SerializedName("groups")
    private List<String> groups;
    @SerializedName("summary")
    private String summary;
    @SerializedName("count")
    private int count;

    public List<String> getGroups() {
        return groups;
    }

    public String getSummary() {
        return summary;
    }

    public int getCount() {
        return count;
    }
}
