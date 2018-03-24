package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("geocode")
    private Geocode geocode;
    @SerializedName("venues")
    private List<Venues> venues;

    public Geocode getGeocode() {
        return geocode;
    }

    public List<Venues> getVenues() {
        return venues;
    }
}
