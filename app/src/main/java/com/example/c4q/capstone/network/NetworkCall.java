package com.example.c4q.capstone.network;

import android.util.Log;

import com.example.c4q.capstone.network.model.BarzzModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 3/17/18.
 */

public class NetworkCall {


    public static void start(String zipCode) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkInfo.barzzURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        BarzzService barzzService = retrofit.create(BarzzService.class);

        Call<BarzzModel> call = barzzService.getBarzz(zipCode);

        call.enqueue(new Callback<BarzzModel>() {
            @Override
            public void onResponse(Call<BarzzModel> call, Response<BarzzModel> response) {

                Log.d("SUCCESSSS!!!!!!!", response.body().toString());
                Log.d("First Item: ", response.body().getSuccess().getResults().get(0).getName() );
                Log.d("Second Item: ", response.body().getSuccess().getResults().get(1).getName() );
                Log.d("Third Item: ", response.body().getSuccess().getResults().get(2).getName() );
                Log.d("Fourth Item: ", response.body().getSuccess().getResults().get(3).getName() );
                Log.d("Fifth Item: ", response.body().getSuccess().getResults().get(4).getName() );
                Log.d("Sixth Item: ", response.body().getSuccess().getResults().get(5).getName() );
                Log.d("Seventh Item: ", response.body().getSuccess().getResults().get(6).getName() );
                Log.d("Eight Item: ", response.body().getSuccess().getResults().get(7).getName() );
            }

            @Override
            public void onFailure(Call<BarzzModel> call, Throwable t) {

                t.printStackTrace();

            }
        });
    }


}
