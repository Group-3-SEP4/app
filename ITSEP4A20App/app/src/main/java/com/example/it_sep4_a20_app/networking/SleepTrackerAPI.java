package com.example.it_sep4_a20_app.networking;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.Settings;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SleepTrackerAPI
{
        //TODO add devideEui as querry
    @GET("/Settings")
    Call<Settings> getSettings();

    @POST("/Settings")
    Call<Settings> setSettings(@Body Settings settings);

    @GET("/Measurements")
    Call<LiveMeasurements> getLiveMeasurement(@Query("deviceEui") String deviceEui);
}
