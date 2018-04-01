package com.example.c4q.capstone.network;

import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.network.barzz.barzzmodel.Results;
import com.example.c4q.capstone.network.foursquare.FourSNetworkCall;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.FourSquareModel;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.Venues;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

/**
 * Created by amirahoxendine on 3/27/18.
 */

public class ApiToVenueConverter {
    private String ID;
    private String Name;
    private String Address;
    private String City;
    private String State;
    private String Zip;
    private String Latitude;
    private String Longitude;
    private String Phone;
    private String Bar_Website;
    private String Hours;
    private String Type;
    private String Bar_Image;
    private String Amenity;


    private String venue_name;
    private String venue_phone;
    private String venue_type;
    private String hours;
    private String payment_options;
    private String price_range;
    private boolean cover_charge;
    private float rating_avg;
    private int rating_count;
    private boolean reservations;

    public List<Venue> barzzToVenue(List<Results> barzzResults) {
        List<Venue> venueList = new ArrayList<>();


        return venueList;
    }

    public List<Venue> fourSToVenue(List<Venues> fourSquareModels) {

        List<Venue> venueList = new ArrayList<>();

        for (int i = 0; i < fourSquareModels.size(); i++) {
                Venue venue = new Venue();
                venue.setVenue_name(fourSquareModels.get(i).getName());
                venue.setVenue_id(fourSquareModels.get(i).getId());
                venue.setVenue_address(fourSquareModels.get(i).getLocation().getAddress());
            venueList.add(venue);
        }

        return venueList;
    }

    public Venue fourSDetailToVenue(FourSquareVenueDetail venueDetail){
        Venue venue = new Venue();
        venue.setVenue_name(venueDetail.getName());
        venue.setVenue_id(venueDetail.getId());
        venue.setVenue_address(venueDetail.getLocation().getAddress());
        if (venueDetail.getBestPhoto() != null){
            venue.setVenue_photo_url(venueDetail.getBestPhoto().getPrefix() +"500x500"+ venueDetail.getBestPhoto().getSuffix());
        }
        venue.setVenue_url(venueDetail.getUrl());
        return venue;
    }



}
