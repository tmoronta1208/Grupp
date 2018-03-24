package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Location {
    @SerializedName("formattedAddress")
    private List<String> formattedAddress;
    @SerializedName("country")
    private String country;
    @SerializedName("state")
    private String state;
    @SerializedName("city")
    private String city;
    @SerializedName("cc")
    private String cc;
    @SerializedName("postalCode")
    private String postalCode;
    @SerializedName("lng")
    private double lng;
    @SerializedName("lat")
    private double lat;
    @SerializedName("crossStreet")
    private String crossStreet;
    @SerializedName("address")
    private String address;

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getCc() {
        return cc;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public String getAddress() {
        return address;
    }
}
