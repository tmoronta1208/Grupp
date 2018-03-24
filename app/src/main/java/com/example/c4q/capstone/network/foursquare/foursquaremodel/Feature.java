package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

public class Feature {
    @SerializedName("geometry")
    private Geometry geometry;
    @SerializedName("longId")
    private String longId;
    @SerializedName("id")
    private String id;
    @SerializedName("slug")
    private String slug;
    @SerializedName("woeType")
    private int woeType;
    @SerializedName("highlightedName")
    private String highlightedName;
    @SerializedName("matchedName")
    private String matchedName;
    @SerializedName("displayName")
    private String displayName;
    @SerializedName("name")
    private String name;
    @SerializedName("cc")
    private String cc;

    public Geometry getGeometry() {
        return geometry;
    }

    public String getLongId() {
        return longId;
    }

    public String getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public int getWoeType() {
        return woeType;
    }

    public String getHighlightedName() {
        return highlightedName;
    }

    public String getMatchedName() {
        return matchedName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name;
    }

    public String getCc() {
        return cc;
    }
}
