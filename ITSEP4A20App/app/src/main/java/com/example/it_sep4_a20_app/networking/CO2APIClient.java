package com.example.it_sep4_a20_app.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.util.ServiceGenerator;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CO2APIClient implements ICO2APIClient
{
    OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

    private INetworking networking;
    private MutableLiveData<Double> co2;

    public CO2APIClient()
    {
        networking = ServiceGenerator.getAPI();
        co2 = new MutableLiveData<>();
    }

    @Override
    public MutableLiveData<Double> requestCO2()
    {
        Call<Double> call = networking.getCO2();
        call.enqueue(new Callback<Double>()
        {
            @Override
            public void onResponse(Call<Double> call, Response<Double> response)
            {
                if(response.code() == 200)
                {
                     co2.setValue(response.body());
                     Log.i("Retrofit", "Got value: " + co2);
                }
            }

            @Override
            public void onFailure(Call<Double> call, Throwable t)
            {
                Log.i("Retrofit", "Something went wrong :<");
                Log.i("Retrofit", t.getLocalizedMessage());
                Log.i("Retrofit", t.toString());
            }
        });
        return co2;
    }
}
