package com.example.c4q.capstone.network.FourSquareNetwork.model;

import com.google.gson.annotations.SerializedName;

public class Stats {
    private int visitsCount;
    private int tipCount;
    private int usersCount;
    private int checkinsCount;

    public int getVisitsCount() {
        return visitsCount;
    }

    public int getTipCount() {
        return tipCount;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public int getCheckinsCount() {
        return checkinsCount;
    }
}
