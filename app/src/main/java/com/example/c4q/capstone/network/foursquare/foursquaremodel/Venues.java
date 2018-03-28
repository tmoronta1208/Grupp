package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venues {

    @SerializedName("url")
    private String url;
    @SerializedName("categories")
    private List<Categories> categories;
    @SerializedName("location")
    private Location location;
    @SerializedName("contact")
    private Contact contact;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    public String getUrl() {
        return url;
    }


    public List<Categories> getCategories() {
        return categories;
    }

    public Location getLocation() {
        return location;
    }

    public Contact getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
