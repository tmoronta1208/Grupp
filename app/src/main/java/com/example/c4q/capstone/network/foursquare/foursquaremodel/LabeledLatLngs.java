package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

public class LabeledLatLngs {
    @SerializedName("lng")
    private double lng;
    @SerializedName("lat")
    private double lat;
    @SerializedName("label")
    private String label;

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getLabel() {
        return label;
    }
}
