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
        venue.setVenue_city(venueDetail.getLocation().getCity());
        venue.setVenue_postal_code(venueDetail.getLocation().getPostalCode());
        venue.setVenue_lat(venueDetail.getLocation().getLat());
        venue.setVenue_lng(venueDetail.getLocation().getLng());
        venue.setRating_avg(venueDetail.getRating());
        venue.setVenue_description(venueDetail.getDescription());
        if (venueDetail.getBestPhoto() != null){
            venue.setVenue_photo_url(venueDetail.getBestPhoto().getPrefix() +"500x500"+ venueDetail.getBestPhoto().getSuffix());
        }
        venue.setVenue_url(venueDetail.getUrl());
        return venue;
    }



}
