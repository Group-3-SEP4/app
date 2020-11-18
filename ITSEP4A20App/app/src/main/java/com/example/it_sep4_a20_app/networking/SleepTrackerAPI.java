package com.example.it_sep4_a20_app.networking;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface SleepTrackerAPI
{
    @GET("/CO2/value")
    Call<Double> getCO2();

    @GET("/Settings")
    Call<Object> getSettings();
}
