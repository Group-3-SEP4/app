package com.example.it_sep4_a20_app.networking;

import com.example.it_sep4_a20_app.util.Settings;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SleepTrackerAPI
{
    @GET("/CO2/value")
    Call<Double> getCO2();

    @GET("/Settings")
    Call<Settings> getSettings();

    @POST("/Settings")
    Call<Settings> setSettings(@Body Settings settings);
}
