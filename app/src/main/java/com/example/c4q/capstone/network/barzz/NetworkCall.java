package com.example.c4q.capstone.network.barzz;

import android.util.Log;

import com.example.c4q.capstone.network.barzz.barzzmodel.BarzzModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 3/17/18.
 */

public class NetworkCall {

    public static final  String BARZZ_KEY = "&user_key=96b42918912ec3f1d85660e18356617c";
    public static final String BARZZ_URL = "https://api.barzz.net/api/";


    public static void start(String zipCode, String type) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BARZZ_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        BarzzService barzzService = retrofit.create(BarzzService.class);

        Call<BarzzModel> call = barzzService.getBarzz(zipCode, type, null);

        call.enqueue(new Callback<BarzzModel>() {
            @Override
            public void onResponse(Call<BarzzModel> call, Response<BarzzModel> response) {

                Log.d("SUCCESSSS!!!!!!!", response.body().toString());
                Log.d("First Item: ", response.body().getSuccess().getResults().get(0).getName() );

            }

            @Override
            public void onFailure(Call<BarzzModel> call, Throwable t) {

                t.printStackTrace();

            }
        });
    }


}
