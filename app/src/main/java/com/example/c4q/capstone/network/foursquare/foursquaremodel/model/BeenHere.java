package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.google.gson.annotations.SerializedName;

public class BeenHere {
    @SerializedName("lastCheckinExpiredAt")
    private int lastCheckinExpiredAt;
    @SerializedName("marked")
    private boolean marked;
    @SerializedName("unconfirmedCount")
    private int unconfirmedCount;
    @SerializedName("count")
    private int count;

    public int getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }

    public boolean getMarked() {
        return marked;
    }

    public int getUnconfirmedCount() {
        return unconfirmedCount;
    }

    public int getCount() {
        return count;
    }
}
