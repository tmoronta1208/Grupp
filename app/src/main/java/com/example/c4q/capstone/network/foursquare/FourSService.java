package com.example.c4q.capstone.network.foursquare;

import com.example.c4q.capstone.network.foursquare.foursquaremodel.FourSquareModel;
import com.example.c4q.capstone.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by c4q on 3/20/18.
 */

public interface FourSService {


    @GET("search?categoryId=4d4b7105d754a06376d81259&"
            + Constants.FOUR_SQUARE_CLIENT_ID +"&" + Constants.FOUR_SQUARE_CLIENT_SECRET)
    Call<FourSquareModel> getVenues(@Query("near") String zipCode, @Query("radius") String radius, @Query("query") String bars, @Query("v") String date);
}
