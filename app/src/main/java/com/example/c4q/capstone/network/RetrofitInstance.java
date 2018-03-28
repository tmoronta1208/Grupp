package com.example.c4q.capstone.network;

import com.example.c4q.capstone.network.barzz.BarzzService;
import com.example.c4q.capstone.network.foursquare.FourSService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amirahoxendine on 3/27/18.
 */

public class RetrofitInstance {
    private static String base_url = "https://data.cityofnewyork.us/";
    public static RetrofitInstance instance;

    public RetrofitInstance() {

    }

    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    Retrofit getRetrofit() { // getting the retofit builder to use in other methods
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public BarzzService getBarzzApi(){
        return getRetrofit().create(BarzzService.class);
    }

    public FourSService getFourSApi(){
        return getRetrofit().create(FourSService.class);
    }

}
