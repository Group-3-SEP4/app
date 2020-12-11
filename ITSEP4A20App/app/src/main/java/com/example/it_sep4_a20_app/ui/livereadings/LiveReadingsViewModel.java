
package com.example.it_sep4_a20_app.ui.livereadings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.repositories.ReadingsRepository;

public class LiveReadingsViewModel extends ViewModel {
    private ReadingsRepository mRepo;

    public LiveReadingsViewModel() {
        this.mRepo = ReadingsRepository.getInstance();
    }

    public LiveData<LiveMeasurements> getLiveMeasurements(){
        return mRepo.getLiveMeasurements();
    }


}