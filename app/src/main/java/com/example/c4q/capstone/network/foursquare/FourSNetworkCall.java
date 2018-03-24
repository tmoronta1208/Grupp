package com.example.c4q.capstone.network.foursquare;

import android.util.Log;

import com.example.c4q.capstone.network.barzz.BarzzService;
import com.example.c4q.capstone.network.barzz.barzzmodel.BarzzModel;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.PreferencesFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by c4q on 3/20/18.
 */

public class FourSNetworkCall {



    public static final String FOUR_SQUARE_CLIENT_ID = "&user_key=96b42918912ec3f1d85660e18356617c";
    public static final String FOUR_SQUARE_CLIENT_SECRET = "&user_key=96b42918912ec3f1d85660e18356617c";
    public static final String FOUR_SQUARE_URL = "https://api.foursquare.com/v2/venues/";


    public static void start(final String zipcode) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FOUR_SQUARE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final BarzzService barzzService = retrofit.create(BarzzService.class);

        for (int i = 0; i < PreferencesFragment.selectedPrefs.size(); i++) {


            Call<BarzzModel> call = barzzService.getBarzz(zipcode, PreferencesFragment.selectedPrefs.get(i));

            call.enqueue(new Callback<BarzzModel>() {
                @Override
                public void onResponse(Call<BarzzModel> call, Response<BarzzModel> response) {

                    Log.d("SUCESSSS", response.body().getSuccess().getResults().get(0).getName());


                }

                @Override
                public void onFailure(Call<BarzzModel> call, Throwable t) {

                    t.printStackTrace();

                }
            });


        }


    }


}
