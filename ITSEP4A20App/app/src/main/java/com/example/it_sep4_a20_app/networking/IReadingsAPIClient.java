package com.example.it_sep4_a20_app.networking;

import android.icu.number.IntegerWidth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;

public interface IReadingsAPIClient
{
    LiveData<LiveMeasurements> getLiveMeasurements();
    void requestMeasurements();
}
