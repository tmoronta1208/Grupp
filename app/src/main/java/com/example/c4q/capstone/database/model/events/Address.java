package com.example.c4q.capstone.database.model.events;

public class Address{
    private String address;
    private String cross_street;
    private double lat;
    private double lng;
    private String postal_code;
    private String formatted_address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCross_street() {
        return cross_street;
    }

    public void setCross_street(String cross_street) {
        this.cross_street = cross_street;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
}