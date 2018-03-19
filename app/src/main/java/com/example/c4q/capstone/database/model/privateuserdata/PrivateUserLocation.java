package com.example.c4q.capstone.database.model.privateuserdata;


class PrivateUserLocation {
    private boolean share_location;
    private double lat;
    private double lng;

    public boolean isShare_location() {
        return share_location;
    }

    public void setShare_location(boolean share_location) {
        this.share_location = share_location;
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
