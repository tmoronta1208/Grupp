package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by c4q on 3/20/18.
 */

public class FourSquareModel {


    @SerializedName("response")
    private Response response;
    @SerializedName("notifications")
    private List<Notifications> notifications;
    @SerializedName("meta")
    private Meta meta;

    public Response getResponse() {
        return response;
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public Meta getMeta() {
        return meta;
    }
}
