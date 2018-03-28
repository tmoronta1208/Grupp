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


            /*venueList.get(i).setVenue_name(fourSquareModels.get(i).getResponse().getVenues().get(i).getName());
            venueList.get(i).setVenue_phone(fourSquareModels.get(i).getResponse().getVenues().get(i).getContact().getFormattedPhone());
            venueList.get(i).setVenue_type(fourSquareModels.get(i).getResponse().getVenues().get(i).getCategories().get(i).getName());*/

//Need to do another service call in order to get the details below

//           venueList.get(i).setHours();
//           venueList.get(i).setPayment_options();
//           venueList.get(i).setPrice_range(fourSquareModels.get(i).getResponse().getVenues().get(i).getName());
//           venueList.get(i).setCover_charge(fourSquareModels.get(i).getResponse().getVenues().get(i).getName());
//           venueList.get(i).setRating_avg(fourSquareModels.get(i).getResponse().getVenues().get(i).getName());
//           venueList.get(i).setRating_count(fourSquareModels.get(i).getResponse().getVenues().get(i).getName());
//           venueList.get(i).setReservations(fourSquareModels.get(i).getResponse().getVenues().get(i).getName());


        return venueList;
    }




}
