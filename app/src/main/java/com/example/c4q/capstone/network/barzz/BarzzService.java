package com.example.c4q.capstone.network.barzz;

import com.example.c4q.capstone.network.barzz.barzzmodel.BarzzModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by c4q on 3/17/18.
 */

public interface BarzzService {

    //   @GET("search"+ NetworkCall.BARZZ_KEY)
//    Call<BarzzModel> getBarzz(@QueryMap Map<String, String> options);

    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip);


    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer);

    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club);

    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach);

    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay);

    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah);

    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel);

    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel,
                              @Query("type") String karaoke);


    @GET("search?" + NetworkCall.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel,
                              @Query("type") String karaoke,
                              @Query("type") String cocktail);

    @GET("search?" + NetworkCall.BARZZ_KEY)
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
