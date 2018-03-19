package com.example.c4q.capstone.database.model.publicuserdata;


class PublicUserLocation {
    private double lat;
    private double lng;
    private int primary_zip;
    private boolean share_current_location;

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

    public int getPrimary_zip() {
        return primary_zip;
    }

    public void setPrimary_zip(int primary_zip) {
        this.primary_zip = primary_zip;
    }

    public boolean isShare_current_location() {
        return share_current_location;
    }

    public void setShare_current_location(boolean share_current_location) {
        this.share_current_location = share_current_location;
    }
}