package com.example.it_sep4_a20_app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel
{
    private Repository repo;
    private MutableLiveData<Double> co2;

    public MainActivityViewModel()
    {
        co2 = new MutableLiveData<>();
        this.repo = new Repository();
    }

    public LiveData<Double> getCO2()
    {
        return repo.getCO2();
    }
}
