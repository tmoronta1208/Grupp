package com.example.c4q.capstone.network.FourSquareNetwork.model;


import java.util.List;

/**
 * Created by c4q on 3/18/18.
 */

public class FourSquareModel {


    private Response response;
    private List<Notifications> notifications;
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
