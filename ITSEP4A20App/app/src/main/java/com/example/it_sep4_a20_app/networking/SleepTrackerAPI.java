package com.example.it_sep4_a20_app.networking;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.data.models.Settings;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author Tobias Sønderbo, David Nguyen
 */
public interface SleepTrackerAPI
{
    @GET("/Settings")
    Call<Settings> getSettings(@Query("deviceEui") String deviceEui);

    @POST("/Settings")
    Call<Settings> postSettings(@Body Settings settings);

    @GET("/Measurements")
    Call<LiveMeasurements> getLiveMeasurement(@Query("deviceEui") String deviceEui);

    @GET("/HistoricalOverview")
    Call<DetailedMeasurements> getDetailedMeasurements(@Query("deviceEui") String deviceEui,
                                                       @Query("validFrom") String validFrom,
                                                       @Query("validTo") String validTo);
    @GET("/Overview")
    Call<NightOverview> getNightOverview(@Query("deviceEui") String deviceEui);
}
