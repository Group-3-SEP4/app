package com.example.it_sep4_a20_app.ui.co2preferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;
import com.example.it_sep4_a20_app.util.Settings;

public class Co2PreferencesViewModel extends ViewModel {

    private SettingsRepository repo;
    private MutableLiveData<Settings> settings;
    private final int MAXCO2 = 1500;
    private final int MINCO2 = 200;

    public Co2PreferencesViewModel() {
        this.repo = new SettingsRepository();
        this.settings = new MutableLiveData<>();
    }

    public MutableLiveData<Settings> getSettings() {
        settings.setValue(repo.getSettings());
        return settings;
    }

    public void setMINCO2(int minco2) {
        Settings temp = settings.getValue();
        temp.setCo2Min(minco2);
        settings.setValue(repo.setSettings(temp));
    }

    public void setMAXCO2(int maxco2) {
        Settings temp = settings.getValue();
        temp.setCo2Max(maxco2);
        settings.setValue(repo.setSettings(temp));
    }

    public Settings resetCo2Levels() {
        Settings temp = settings.getValue();
        temp.setCo2Max(MAXCO2);
        temp.setCo2Min(MINCO2);
        return repo.setSettings(temp);
    }

    //
//    public int getCo2Min() {
//        return repo.getSettings().getCo2Min();
//    }
//
//    public int getCo2Max() {
//        return repo.getSettings().getCo2Max();
//    }
}
