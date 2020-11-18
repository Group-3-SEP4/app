package com.example.it_sep4_a20_app.ui.co2preferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.it_sep4_a20_app.Repository;

public class Co2PreferencesViewModel extends ViewModel {
    private Repository repo;
    private MutableLiveData<Integer> co2Min;
    private MutableLiveData<Integer> co2Max;

    public Co2PreferencesViewModel(){
        co2Min = new MutableLiveData<>();
        co2Max = new MutableLiveData<>();
        this.repo = new Repository();
    }

    public MutableLiveData<Integer> getCo2Min() {
        return co2Min;
    }

    public MutableLiveData<Integer> getCo2Max() {
        return repo.;
    }
}
