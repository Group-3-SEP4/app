package com.example.it_sep4_a20_app.ui.co2preferences;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

public class Co2PreferencesFragment extends PreferenceFragmentCompat {

    private Co2PreferencesViewModel viewModel;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Context context = getPreferenceManager().getContext();
        PreferenceScreen screen = getPreferenceManager().createPreferenceScreen(context);

        EditTextPreference minCo2Preference = new EditTextPreference(context);
        minCo2Preference.setKey("minCo2Level");
        minCo2Preference.setTitle("Edit minimum Co2 levels, current: " + viewModel.getCo2Min());

        EditTextPreference maxCo2Preference = new EditTextPreference(context);
        maxCo2Preference.setKey("maxCo2Level");
        maxCo2Preference.setTitle("Edit maximum Co2 levels, current: " + viewModel.getCo2Max());

        Preference resetCo2Preference = new Preference(context);
        resetCo2Preference.setKey("resetCo2Levels");
        resetCo2Preference.setTitle("Reset");
        resetCo2Preference.setSummary("Reset MIN and MAX Co2 levels to recommended");

        setPreferenceScreen(screen);
    }

}