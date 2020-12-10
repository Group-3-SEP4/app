package com.example.it_sep4_a20_app.networking;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.data.models.NightOverview;
import androidx.lifecycle.LiveData;

public interface IReadingsAPIClient
{
    LiveData<LiveMeasurements> getLiveMeasurements();
    void requestMeasurements();
    LiveData<NightOverview> NightOverview();
}
