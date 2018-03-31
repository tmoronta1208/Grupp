package com.example.c4q.capstone.network.foursquare.foursquaremodel.detailsmodel;

import com.google.gson.annotations.SerializedName;

public class Photo {
    @SerializedName("suffix")
    private String suffix;
    @SerializedName("prefix")
    private String prefix;

    public String getSuffix() {
        return suffix;
    }

    public String getPrefix() {
        return prefix;
    }
}
