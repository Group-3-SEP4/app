package com.example.it_sep4_a20_app.ui.co2preferences;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.it_sep4_a20_app.repositories.SettingsRepository;
import com.example.it_sep4_a20_app.util.objects.Settings;

public class Co2PreferencesViewModel extends ViewModel {

    private SettingsRepository mRepo;
    private final int MAXCO2 = 1500;
    private final int MINCO2 = 200;

    public Co2PreferencesViewModel() {
        this.mRepo = new SettingsRepository();
    }

    public MutableLiveData<Settings> getSettings() {
        return mRepo.getSettings();
    }

    public void setMINCO2(int minco2) {
        Settings temp = mRepo.getSettings().getValue();
        temp.setPpmMin(minco2);
        mRepo.setSettings(temp);
    }

    public void setMAXCO2(int maxco2) {
        Settings temp = mRepo.getSettings().getValue();
        temp.setPpmMax(maxco2);
        mRepo.setSettings(temp);
    }

    public void resetCo2Levels() {
        Settings temp = mRepo.getSettings().getValue();
        if(temp == null)
            return;
        temp.setPpmMax(MAXCO2);
        temp.setPpmMin(MINCO2);
        mRepo.setSettings(temp);
    }

}
