package com.example.c4q.capstone.network;

import com.example.c4q.capstone.network.barzz.BarzzService;
import com.example.c4q.capstone.network.foursquare.FourSService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.c4q.capstone.utils.Constants.BARZZ_URL;
import static com.example.c4q.capstone.utils.Constants.FOUR_SQUARE_URL;

/**
 * Created by amirahoxendine on 3/27/18.
 */

public class RetrofitInstance {
    private static String four_square_base = "https://api.foursquare.com/v2/venues/";
    public static RetrofitInstance instance;

    public RetrofitInstance() {

    }

    public static RetrofitInstance getInstance() {
        if (instance == null) {
            instance = new RetrofitInstance();
        }
        return instance;
    }

    Retrofit getRetrofit(String base_url) { // getting the retofit builder to use in other methods
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public BarzzService getBarzzApi(){
        return getRetrofit(BARZZ_URL).create(BarzzService.class);
    }

    public FourSService getFourSApi(){
        return getRetrofit(FOUR_SQUARE_URL).create(FourSService.class);
    }

}
