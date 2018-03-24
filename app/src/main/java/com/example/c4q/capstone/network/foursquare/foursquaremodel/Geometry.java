package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

public class Geometry {
    @SerializedName("bounds")
    private Bounds bounds;
    @SerializedName("center")
    private Center center;

    public Bounds getBounds() {
        return bounds;
    }

    public Center getCenter() {
        return center;
    }
}
