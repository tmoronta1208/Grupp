package com.example.c4q.capstone.database.model.events;


class VenueAddress {
    private String address;
    private String city;
    private String cross_street;
    private String state;
    private int zip;
    private double lat;
    private double lng;

    public VenueAddress() {
    }

    public VenueAddress(String address, String city, String cross_street, String state, int zip, double lat, double lng) {
        this.address = address;
        this.city = city;
        this.cross_street = cross_street;
        this.state = state;
        this.zip = zip;
        this.lat = lat;
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCross_street() {
        return cross_street;
    }

    public void setCross_street(String cross_street) {
        this.cross_street = cross_street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
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
}
