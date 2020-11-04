package com.example.it_sep4_a20_app.util;

import com.example.it_sep4_a20_app.networking.ICO2APIClient;
import com.example.it_sep4_a20_app.networking.INetworking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator
{
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://localhost:44378/") //TODO Change to cloud address
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static INetworking api = retrofit.create(INetworking.class);

    public static INetworking getAPI()
    {
        return api;
    }
}
