package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.google.gson.annotations.SerializedName;

public class Menu {
    @SerializedName("mobileUrl")
    private String mobileUrl;
    @SerializedName("url")
    private String url;
    @SerializedName("anchor")
    private String anchor;
    @SerializedName("label")
    private String label;
    @SerializedName("type")
    private String type;

    public String getMobileUrl() {
        return mobileUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getAnchor() {
        return anchor;
    }

    public String getLabel() {
        return label;
    }

    public String getType() {
        return type;
    }
}
