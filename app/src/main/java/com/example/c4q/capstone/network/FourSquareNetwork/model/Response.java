package com.example.c4q.capstone.network.FourSquareNetwork.model;

import java.util.List;

public class Response {
    private boolean confident;
    private List<Venues> venues;

    public boolean getConfident() {
        return confident;
    }

    public List<Venues> getVenues() {
        return venues;
    }
}
