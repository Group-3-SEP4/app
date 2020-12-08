package com.example.it_sep4_a20_app.ui.nightoverview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.it_sep4_a20_app.data.models.NightOverview;
import com.example.it_sep4_a20_app.repositories.ReadingsRepository;

public class NightOverviewViewModel extends ViewModel
{
    private ReadingsRepository mRepo;

    public NightOverviewViewModel()
    {
        mRepo = ReadingsRepository.getInstance();
    }

    public LiveData<NightOverview> getNightOverview()
    {
        return mRepo.getNightOverview();
    }
}
