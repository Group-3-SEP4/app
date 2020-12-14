package com.example.it_sep4_a20_app.networking;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedCo2;
import com.example.it_sep4_a20_app.data.models.detailedinfo.DetailedMeasurements;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IReadingsAPIClient
{
    LiveData<LiveMeasurements> getLiveMeasurements();
    void requestMeasurements();
    LiveData<NightOverview> NightOverview();
    LiveData<DetailedMeasurements> getDetailedMeasurements();
}
