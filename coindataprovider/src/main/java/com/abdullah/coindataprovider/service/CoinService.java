package com.abdullah.coindataprovider.service;

import com.abdullah.coindataprovider.model.CoinResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by abdullah on 13.1.2018.
 */

public interface CoinService {
    @GET("ticker/{id}/")
    Single<List<CoinResponse>> getCoinResponse(@Path("id") String coinId, @Query("convert") String convertType);
}
