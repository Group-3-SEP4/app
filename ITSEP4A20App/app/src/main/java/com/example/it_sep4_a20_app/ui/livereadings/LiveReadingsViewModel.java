
package com.example.it_sep4_a20_app.ui.livereadings;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.repositories.ReadingsRepository;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

public class LiveReadingsViewModel extends AndroidViewModel {
    private ReadingsRepository mRepo;
    private SettingsRepository mSettingsRep;

    public LiveReadingsViewModel(Application application) {
        super(application);
        this.mRepo = ReadingsRepository.getInstance();
        this.mSettingsRep = SettingsRepository.getInstance(application);
    }

    public LiveData<LiveMeasurements> getLiveMeasurements(){
        return mRepo.getLiveMeasurements();
    }

    public int getMaxCo2() {return mSettingsRep.getMaxCo2Setting();}
    public int getMinCo2() {return mSettingsRep.getMinCo2Setting();}
    public float getTemperatureSetPoint() {return mSettingsRep.getTemperatureSetPoint();}
    public int getMaxHumidity() {return mSettingsRep.getMaxHumiditySetting();}
    public int getMinHumidity() {return mSettingsRep.getMinHumiditySetting();}

}