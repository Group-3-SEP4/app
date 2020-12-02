package com.example.it_sep4_a20_app.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.util.objects.Settings;

public interface ISettingsAPIClient
{
    MutableLiveData<Settings> getSettings();
    void requestSettings();
    void postSettings(Settings newSettings);
}
