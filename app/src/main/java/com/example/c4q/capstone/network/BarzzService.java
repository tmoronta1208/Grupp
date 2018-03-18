package com.example.c4q.capstone.network;

import com.example.c4q.capstone.network.model.BarzzModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by c4q on 3/17/18.
 */

public interface BarzzService {

    @GET("search?&user_key=" + NetworkInfo.barzzKey)
    Call<BarzzModel> getBarzz(@Query("zip") String zipCode);

}
