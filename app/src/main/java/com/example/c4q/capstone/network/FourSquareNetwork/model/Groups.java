package com.example.c4q.capstone.network.FourSquareNetwork.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Groups {
    private List<Items> items;
    private int count;
    private String name;
    private String type;

    public List<Items> getItems() {
        return items;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
