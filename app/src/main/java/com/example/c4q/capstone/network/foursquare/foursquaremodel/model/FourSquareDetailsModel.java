package com.example.c4q.capstone.network.foursquare.foursquaremodel.model;

import com.example.c4q.capstone.network.foursquare.foursquaremodel.Response;

/**
 * Created by c4q on 3/27/18.
 */

public class FourSquareDetailsModel {


    @com.google.gson.annotations.SerializedName("response")
    private Response response;
    @com.google.gson.annotations.SerializedName("notifications")
    private java.util.List<Notifications> notifications;
    @com.google.gson.annotations.SerializedName("meta")
    private Meta meta;
}
