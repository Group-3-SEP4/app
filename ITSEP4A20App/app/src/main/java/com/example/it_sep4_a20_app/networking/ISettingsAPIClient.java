package com.example.it_sep4_a20_app.networking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.data.models.Settings;

public interface ISettingsAPIClient
{
    LiveData<Settings> getSettings();
    void requestSettings();
    void postSettings(Settings newSettings);
}
