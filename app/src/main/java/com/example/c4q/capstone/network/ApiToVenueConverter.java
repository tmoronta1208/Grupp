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
;

    public List<Venue> barzzToVenue(List<Results> barzzResults) {
        List<Venue> venueList = new ArrayList<>();


        return venueList;
    }

    public List<Venue> fourSToVenue(List<Venues> fourSquareModels) {

        List<Venue> venueList = new ArrayList<>();

        for (int i = 0; i < fourSquareModels.size(); i++) {
            Venue venue = new Venue();
            venue.setVenue_name(fourSquareModels.get(i).getName());
            venueList.add(venue);
        }



        return venueList;
    }




}
