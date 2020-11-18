package com.example.it_sep4_a20_app.repositories;

import android.util.Log;

import com.example.it_sep4_a20_app.networking.IAPIClient;
import com.example.it_sep4_a20_app.util.APIFactory;
import com.example.it_sep4_a20_app.util.Settings;

public class SettingsRepository {
    private IAPIClient apiClient;

    public SettingsRepository(){
        apiClient = APIFactory.getAPIClient();
    }

    public Settings getSettings(){
        Log.i("Repository", "Calling request settings...");
        return apiClient.requestSettings();
    }
}
