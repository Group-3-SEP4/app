package com.example.it_sep4_a20_app.ui.co2preferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;

public class Co2PreferencesViewModel extends ViewModel {
    private SettingsRepository repo;

    public Co2PreferencesViewModel(){
        this.repo = new SettingsRepository();
    }

    public int getCo2Min() {
        return repo.getSettings().getCo2Min();
    }

    public int getCo2Max() {
        return repo.getSettings().getCo2Max();
    }
}
