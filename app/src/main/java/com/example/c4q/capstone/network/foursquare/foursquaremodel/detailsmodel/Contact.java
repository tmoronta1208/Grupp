package com.example.c4q.capstone.network.foursquare.foursquaremodel.detailsmodel;

import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("facebookName")
    private String facebookName;
    @SerializedName("facebookUsername")
    private String facebookUsername;
    @SerializedName("facebook")
    private String facebook;
    @SerializedName("twitter")
    private String twitter;
    @SerializedName("formattedPhone")
    private String formattedPhone;
    @SerializedName("phone")
    private String phone;

    public String getFacebookName() {
        return facebookName;
    }

    public String getFacebookUsername() {
        return facebookUsername;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getTwitter() {
        return twitter;
    }

    public String getFormattedPhone() {
        return formattedPhone;
    }

    public String getPhone() {
        return phone;
    }
}
