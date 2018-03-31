package com.example.c4q.capstone.network.foursquare.foursquaremodel.detailsmodel;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("requestId")
    private String requestId;
    @SerializedName("code")
    private int code;

    public String getRequestId() {
        return requestId;
    }

    public int getCode() {
        return code;
    }
}
