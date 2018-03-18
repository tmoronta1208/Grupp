package com.example.c4q.capstone.network.FourSquareNetwork.model;

import java.util.List;

public class Location {
    private List<String> formattedAddress;
    private String country;
    private String state;
    private String city;
    private String cc;
    private String postalCode;
    private int distance;
    private List<LabeledLatLngs> labeledLatLngs;
    private double lng;
    private double lat;
    private String crossStreet;
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

    public int getDistance() {
        return distance;
    }

    public List<LabeledLatLngs> getLabeledLatLngs() {
        return labeledLatLngs;
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
