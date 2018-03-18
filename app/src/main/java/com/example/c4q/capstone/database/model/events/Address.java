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

    public String getCross_street() {
        return cross_street;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getFormatted_address() {
        return formatted_address;
    }
}