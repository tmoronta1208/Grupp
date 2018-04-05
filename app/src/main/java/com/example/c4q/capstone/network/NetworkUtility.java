package com.example.c4q.capstone.network;

import android.util.Log;

import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.network.barzz.barzzmodel.BarzzModel;
import com.example.c4q.capstone.network.barzz.barzzmodel.Results;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.FourSquareModel;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.Venues;
import com.example.c4q.capstone.userinterface.events.VenueNetworkListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amirahoxendine on 3/27/18.
 */

public class NetworkUtility {
    private static NetworkUtility networkUtility;

    public static NetworkUtility getNetworkUtility() {
        if (networkUtility == null) {
            networkUtility = new NetworkUtility();
        }
        return networkUtility;
    }

    public void getBarzzList(String zipCode, List<String> preferences, final VenueNetworkListener venueNetworkListener) {


        Call<BarzzModel> call = RetrofitInstance.getInstance()
                .getBarzzApi()
                .getBarzz(zipCode, preferences.get(0));

        call.enqueue(new Callback<BarzzModel>() {
            @Override
            public void onResponse(Call<BarzzModel> call, Response<BarzzModel> response) {

                if(response != null){
                    Log.d("SUCESSSS!", response.body().getSuccess().getResults().get(0).getName());}
                if(response != null) {
                    List<Results> venueResults = response.body().getSuccess().getResults();

                    List<Venue> venue = new ArrayList<>();
                    //venueNetworkListener.getBarzList(venue);
                }
            }

            @Override
            public void onFailure(Call<BarzzModel> call, Throwable t) {

                t.printStackTrace();

            }
        });
    }

    public void getFourSQList(String zipCode, final String radius, String preferences, final VenueNetworkListener venueNetworkListener) {

        Call<FourSquareModel> call = RetrofitInstance.getInstance()
                .getFourSApi()
                .getVenues(zipCode,radius, preferences,"20180331");

        call.enqueue(new Callback<FourSquareModel>() {
            @Override
            public void onResponse(Call<FourSquareModel> call, Response<FourSquareModel> response) {
                if (response != null){
                    if(response.body() != null){
                        if (response.body().getResponse() != null){
                            if (response.body().getResponse().getVenues() != null){
                                if (response.body().getResponse().getVenues().size() != 0){
                                    Log.d("SUCESSSS", response.body().getResponse().getVenues().get(0).getName());

                                    List<Venues> venueResults = new ArrayList<>();
                                    venueResults = response.body().getResponse().getVenues();
                                    ApiToVenueConverter venueConverter = new ApiToVenueConverter();
                                    List<Venue> venueList = venueConverter.fourSToVenue(venueResults);
                                    Log.d("venue list", "list size: " + venueList.size());
                                    Log.d("venue list", "first venue name: " + venueList.get(0).getVenue_name());
                                    venueNetworkListener.getFourSList(venueList);
                                }

                            } else{
                                Log.d("NULL", "response.body().getResponse.getVenues() is null");
                            }
                        } else{ Log.d("NULL", "response.body().getResponse() is null");}
                    } else {
                        Log.d("NULL", "response.body() is null");
                    }
                } else {
                    Log.d("RESPONSE:", "response is null");
                }
            }

            @Override
            public void onFailure(Call<FourSquareModel> call, Throwable t) {

                t.printStackTrace();
                Log.d("SUCESSSS", "unsucessful");

            }
        });
    }

    public void getFourSquareDetail(final String venueId, final FourSquareDetailListener detailListener) {

        Call<FourSquareDetailCall> call = RetrofitInstance.getInstance()
                .getFourSApi()
                .getVenueDetail(venueId,"20180328" );

        call.enqueue(new Callback<FourSquareDetailCall>() {
            @Override
            public void onResponse(Call<FourSquareDetailCall> call, Response<FourSquareDetailCall> response) {
                if (response != null){
                    if(response.body() != null){
                        if (response.body().getResponse() != null){
                            if (response.body().getResponse().getVenue() != null){
                                Log.d("SUCESSSS", response.body().getResponse().getVenue().getName());

                                FourSquareVenueDetail venueDetail = response.body().getResponse().getVenue();
                                ApiToVenueConverter venueConverter = new ApiToVenueConverter();
                                Venue venue = venueConverter.fourSDetailToVenue(venueDetail);
                                detailListener.getVenueDetail(venue);
                            } else{
                                Log.d("NULL", "response.body().getResponse.getVenue() is null");
                            }
                        } else{ Log.d("NULL", "response.body().getResponse() is null");}
                    } else {
                        Log.d("NULL", "response.body() is null");
                    }
                } else {
                    Log.d("RESPONSE:", "response is null");
                }
            }

            @Override
            public void onFailure(Call<FourSquareDetailCall> call, Throwable t) {

            }
        });
    }

}
