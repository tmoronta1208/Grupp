package com.example.c4q.capstone.network.FourSquareNetwork.model;

import com.google.gson.annotations.SerializedName;

public class BeenHere {
    private int lastCheckinExpiredAt;
    private boolean marked;
    private int unconfirmedCount;
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
