package com.example.c4q.capstone.network.FourSquareNetwork.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    private List<Groups> groups;
    private int count;

    public List<Groups> getGroups() {
        return groups;
    }

    public int getCount() {
        return count;
    }
}
