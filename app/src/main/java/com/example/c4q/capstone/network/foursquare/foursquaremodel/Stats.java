package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

public class Stats {
    @SerializedName("checkinsCount")
    private int checkinsCount;
    @SerializedName("usersCount")
    private int usersCount;
    @SerializedName("tipCount")
    private int tipCount;

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public int getTipCount() {
        return tipCount;
    }
}
