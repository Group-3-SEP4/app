package com.example.it_sep4_a20_app.util;

import com.example.it_sep4_a20_app.networking.SleepTrackerAPI;
import com.example.it_sep4_a20_app.networking.UnsafeOkHttpClient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    //For online hosted API
    private static SleepTrackerAPI api;

    public static SleepTrackerAPI getAPI() {
        if(api == null)
        {
            Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                    .baseUrl("https://rokasapi.azurewebsites.net/")
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofit = retrofitBuilder.build();
            api = retrofit.create(SleepTrackerAPI.class);
        }
        return api;
    }
    //For locally hosted API
    //NOTE Only works with emulator
    private static SleepTrackerAPI apiLocalhost;

    public static SleepTrackerAPI getAPILocalHost() {
        if(apiLocalhost == null)
        {
            Retrofit.Builder retrofitBuilderDummy = new Retrofit.Builder()
                    .baseUrl("https://10.0.2.2:5001/")
                    .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create());
            Retrofit retrofitDummy = retrofitBuilderDummy.build();
            apiLocalhost = retrofitDummy.create(SleepTrackerAPI.class);
        }
        return apiLocalhost;
    }
}
