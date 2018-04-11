package com.example.c4q.capstone.network;

import com.example.c4q.capstone.network.foursquare.foursquaremodel.Location;

/**
 * Created by amirahoxendine on 3/28/18.
 */

public class FourSquareVenueDetail {
    String id;
    String name;
    String url;
    BestPhoto bestPhoto;
    Location location;
    float rating;
    String description;

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BestPhoto getBestPhoto() {
        return bestPhoto;
    }

    public void setBestPhoto(BestPhoto bestPhoto) {
        this.bestPhoto = bestPhoto;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
