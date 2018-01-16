package com.abdullah.coindataprovider.di;

import com.abdullah.coindataprovider.service.CoinService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abdullah on 13.1.2018.
 */
@Module
public class NetworkModule {

    private String endpointUrl = "https://api.coinmarketcap.com/v1/";

    public NetworkModule(String endpointUrl) {
        this.endpointUrl = endpointUrl;
    }

    public NetworkModule() {
    }

    @Provides
    Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(endpointUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    CoinService provideCoinService(Retrofit retrofit) {
        return retrofit.create(CoinService.class);
    }

}
