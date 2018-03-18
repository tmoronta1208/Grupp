package com.example.c4q.capstone.network.FourSquareNetwork;

import com.example.c4q.capstone.network.FourSquareNetwork.model.FourSquareModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by c4q on 3/18/18.
 */

public interface FourSquareService {

    @GET("venues/explore?&client_id="+ FourSquareNetworkCall.CLIENT_ID +"&client_secret="
            +FourSquareNetworkCall.CLIENT_SECRET+
            "&v=20180318")
    Call<FourSquareModel> getVenues(@Query("near") String cityAndState);
}
