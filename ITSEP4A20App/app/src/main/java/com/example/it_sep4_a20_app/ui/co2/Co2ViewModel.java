
package com.example.it_sep4_a20_app.ui.co2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.it_sep4_a20_app.data.models.LiveMeasurements;
import com.example.it_sep4_a20_app.networking.dummy.APIDummy;
import com.example.it_sep4_a20_app.repositories.ReadingsRepository;

public class Co2ViewModel extends ViewModel {
    private ReadingsRepository mRepo;

    public Co2ViewModel() {
        this.mRepo = new ReadingsRepository();
    }

    public LiveData<LiveMeasurements> getLiveMeasurements(){
        return mRepo.getLiveMeasurements();
    }


}