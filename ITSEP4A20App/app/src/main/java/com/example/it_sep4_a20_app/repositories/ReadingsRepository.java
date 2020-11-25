package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import com.example.it_sep4_a20_app.networking.IAPIClient;
import com.example.it_sep4_a20_app.util.APIFactory;


public class ReadingsRepository
{
    private IAPIClient apiClient;

    public ReadingsRepository()
    {
        apiClient = APIFactory.getAPIClient();
    }

    public MutableLiveData<Double> getCO2() {
        Log.i("Repository", "Calling request co2...");
        return apiClient.requestCO2();
    }
}
