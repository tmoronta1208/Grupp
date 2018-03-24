package com.example.c4q.capstone.network.foursquare;

import com.example.c4q.capstone.network.foursquare.foursquaremodel.FourSquareModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by c4q on 3/20/18.
 */

public interface FourSService {


    @GET("search?")
    Call<FourSquareModel> getVenues(@Query("categoryId=4d4b7105d754a06376d81259") String bars);
}
