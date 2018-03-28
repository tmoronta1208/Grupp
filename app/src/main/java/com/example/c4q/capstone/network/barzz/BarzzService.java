package com.example.c4q.capstone.network.barzz;

import com.example.c4q.capstone.network.barzz.barzzmodel.BarzzModel;
import com.example.c4q.capstone.utils.Constants;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by c4q on 3/17/18.
 */

public interface BarzzService {

    //Unable to do a query-map - "key" for type has to be the same
    //@GET("search"+ BarzzNetworkCall.BARZZ_KEY)
    //Call<BarzzModel> getBarzz(@QueryMap Map<String, String> options);

    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip);


    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String type);

    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club);

    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach);

    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay);

    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah);

    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel);

    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel,
                              @Query("type") String karaoke);


    @GET("search?" + Constants.BARZZ_KEY)
    Call<BarzzModel> getBarzz(@Query("zip") String zip,
                              @Query("type") String beer,
                              @Query("type") String club,
                              @Query("type") String beach,
                              @Query("type") String gay,
                              @Query("type") String hookah,
                              @Query("type") String hotel,
                              @Query("type") String karaoke,
                              @Query("type") String cocktail);

    @GET("search?" + Constants.BARZZ_KEY)
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
