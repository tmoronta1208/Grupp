package com.example.c4q.capstone.database.model.sharedUserData;


class SharedUserLocation {
    private boolean share_current_location;
    private String primary_zip;
    private double lat;
    private double lng;

    public boolean isShare_current_location() {
        return share_current_location;
    }

    public void setShare_current_location(boolean share_current_location) {
        this.share_current_location = share_current_location;
    }

    public String getPrimary_zip() {
        return primary_zip;
    }

    public void setPrimary_zip(String primary_zip) {
        this.primary_zip = primary_zip;
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
