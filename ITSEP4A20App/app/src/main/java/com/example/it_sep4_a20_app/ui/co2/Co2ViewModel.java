
package com.example.it_sep4_a20_app.ui.co2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.it_sep4_a20_app.Repository;

public class Co2ViewModel extends ViewModel {
    private Repository repo;
    private MutableLiveData<Double> co2;

    public Co2ViewModel()
    {
        co2 = new MutableLiveData<>();
        this.repo = new Repository();
    }

    public LiveData<Double> getCO2()
    {
        return repo.getCO2();
    }
}