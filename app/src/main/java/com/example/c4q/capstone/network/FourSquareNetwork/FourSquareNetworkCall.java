package com.example.c4q.capstone.network.FourSquareNetwork;

import android.util.Log;

import com.example.c4q.capstone.network.FourSquareNetwork.model.FourSquareModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 3/18/18.
 */

public class FourSquareNetworkCall {


    public static final String CLIENT_ID = "1VAPAUMKNRRWESSSF5QGTCE4OTJ43TMOYM1S0X1LHBFFGWGB";
    public static final String CLIENT_SECRET = "YL54NLBRUR41WMSNQGX1P33PICLW0V1VQUGKKJOH5NNLDKHM";
    private static final String URL = " https://api.foursquare.com/v2/";


    public static void start(String cityAndState) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        FourSquareService fourSquareService = retrofit.create(FourSquareService.class);

        Call<FourSquareModel> call1 = fourSquareService.getVenues(cityAndState);

        call1.enqueue(new Callback<FourSquareModel>() {
            @Override
            public void onResponse(Call<FourSquareModel> call, Response<FourSquareModel> response) {

                Log.d("SUCESSSSSSSSSSSSSSSSSS", response.body().toString());
          //      Log.d("First Item: ", response.body().getResponse().getVenues().get(1).getName() );


            }

            @Override
            public void onFailure(Call<FourSquareModel> call, Throwable t) {

                t.printStackTrace();

            }
        });
    }
}




