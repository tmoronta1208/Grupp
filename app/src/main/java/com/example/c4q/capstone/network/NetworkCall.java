package com.example.c4q.capstone.network;

import android.util.Log;

import com.example.c4q.capstone.model.BarzzModel;
import com.example.c4q.capstone.model.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 3/17/18.
 */

public class NetworkCall {


    public static void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkInfo.barzzURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        BarzzService barzzService = retrofit.create(BarzzService.class);

        Call<BarzzModel> call = barzzService.getBarzz("10001");

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
