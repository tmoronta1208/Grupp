package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Geocode {
    @SerializedName("parents")
    private List<String> parents;
    @SerializedName("feature")
    private Feature feature;
    @SerializedName("where")
    private String where;
    @SerializedName("what")
    private String what;

    public List<String> getParents() {
        return parents;
    }

    public Feature getFeature() {
        return feature;
    }

    public String getWhere() {
        return where;
    }

    public String getWhat() {
        return what;
    }
}
