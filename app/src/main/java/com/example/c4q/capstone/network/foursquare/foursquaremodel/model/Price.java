package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.google.gson.annotations.SerializedName;

public class Price {
    @SerializedName("currency")
    private String currency;
    @SerializedName("message")
    private String message;
    @SerializedName("tier")
    private int tier;

    public String getCurrency() {
        return currency;
    }

    public String getMessage() {
        return message;
    }

    public int getTier() {
        return tier;
    }
}
