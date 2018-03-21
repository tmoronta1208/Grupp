package com.example.c4q.capstone.network.barzz;

import com.example.c4q.capstone.network.barzz.barzzmodel.BarzzModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by c4q on 3/17/18.
 */

public interface BarzzService {

    //   @GET("search"+ BarzzNetworkCall.BARZZ_KEY)
//    Call<BarzzModel> getBarzz(@QueryMap Map<String, String> options);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip);


    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel,
                              @Query("type") String karaoke);


    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel,
                              @Query("type") String karaoke,
                              @Query("type") String cocktail);

    @GET("search?" + BarzzNetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel,
                              @Query("type") String karaoke,
                              @Query("type") String cocktail,
                              @Query("type") String pubs);


}
