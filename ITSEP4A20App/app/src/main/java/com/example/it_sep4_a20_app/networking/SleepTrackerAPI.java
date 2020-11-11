package com.example.it_sep4_a20_app.networking;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SleepTrackerAPI
{
    @GET("") //TODO Change endpoint
    Call<Double> getCO2();
}
