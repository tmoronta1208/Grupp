package com.example.c4q.capstone.network.FourSquareNetwork.model;

import com.google.gson.annotations.SerializedName;

public class Contact {
    private String facebookName;
    private String facebookUsername;
    private String facebook;
    private String instagram;
    private String twitter;
    private String formattedPhone;
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

    public String getInstagram() {
        return instagram;
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
