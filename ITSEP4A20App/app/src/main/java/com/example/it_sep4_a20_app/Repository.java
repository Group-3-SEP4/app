package com.example.it_sep4_a20_app;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.networking.APIClient;
import com.example.it_sep4_a20_app.networking.IAPIClient;
import com.example.it_sep4_a20_app.util.APIFactory;

public class Repository
{
    private IAPIClient apiClient;

    public Repository()
    {
        apiClient = APIFactory.getAPIClientDummy();
    }

    public MutableLiveData<Double> getCO2()
    {
        Log.i("Repository", "Calling request co2...");
        return apiClient.requestCO2();
    }
}
