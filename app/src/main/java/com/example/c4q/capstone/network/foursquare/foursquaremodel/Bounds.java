package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

public class Bounds {
    @SerializedName("sw")
    private Sw sw;
    @SerializedName("ne")
    private Ne ne;

    public Sw getSw() {
        return sw;
    }

    public Ne getNe() {
        return ne;
    }
}
