package com.example.it_sep4_a20_app.util;

import com.example.it_sep4_a20_app.networking.SleepTrackerAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static SleepTrackerAPI mApi;
    public static SleepTrackerAPI getAPI() {
        if(mApi == null)
        {
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl("https://rokasapi.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = retrofitBuilder.build();
            mApi = retrofit.create(SleepTrackerAPI.class);
        }
        return mApi;
    }
}
