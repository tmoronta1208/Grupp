package com.example.c4q.capstone.network.foursquare.foursquaremodel.detailsmodel;

import com.google.gson.annotations.SerializedName;

public class Categories {
    @SerializedName("primary")
    private boolean primary;
    @SerializedName("icon")
    private Icon icon;
    @SerializedName("shortName")
    private String shortName;
    @SerializedName("pluralName")
    private String pluralName;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    public boolean getPrimary() {
        return primary;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getShortName() {
        return shortName;
    }

    public String getPluralName() {
        return pluralName;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
