package com.example.c4q.capstone.network.foursquare;

import com.example.c4q.capstone.network.FourSquareDetailCall;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.FourSquareModel;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.Venues;
import com.example.c4q.capstone.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import static com.example.c4q.capstone.utils.Constants.FOUR_SQUARE_CLIENT_ID;
import static com.example.c4q.capstone.utils.Constants.FOUR_SQUARE_CLIENT_SECRET;

/**
 * Created by c4q on 3/20/18.
 */

public interface FourSService {


    @GET("search?categoryId=4d4b7105d754a06376d81259&"
            + FOUR_SQUARE_CLIENT_ID +"&" + FOUR_SQUARE_CLIENT_SECRET)
    Call<FourSquareModel> getVenues(@Query("near") String zipCode, @Query("radius") String radius, @Query("query") String bars, @Query("v") String date);

    @GET("{id}" + "?" + FOUR_SQUARE_CLIENT_ID +"&" + FOUR_SQUARE_CLIENT_SECRET)
    Call<FourSquareDetailCall> getVenueDetail(@Path("id") String id, @Query("v") String date);
}
