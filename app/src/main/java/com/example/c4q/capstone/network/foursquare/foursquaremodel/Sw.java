package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

public class Sw {
    @SerializedName("lng")
    private double lng;
    @SerializedName("lat")
    private double lat;

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }
}
