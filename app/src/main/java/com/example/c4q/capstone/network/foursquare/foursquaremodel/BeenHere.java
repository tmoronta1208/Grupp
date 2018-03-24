package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

public class BeenHere {
    @SerializedName("lastCheckinExpiredAt")
    private int lastCheckinExpiredAt;

    public int getLastCheckinExpiredAt() {
        return lastCheckinExpiredAt;
    }
}
