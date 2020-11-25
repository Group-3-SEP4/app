package com.example.it_sep4_a20_app.ui.co2preferences;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.it_sep4_a20_app.R;
import com.example.it_sep4_a20_app.util.Settings;

public class Co2PreferencesFragment extends PreferenceFragmentCompat {

    private Co2PreferencesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        EditTextPreference minCo2 = findPreference(getString(R.string.key_minCo2));
        EditTextPreference maxCo2 = findPreference(getString(R.string.key_maxCo2));
        Preference resetCo2 = findPreference(getString(R.string.key_resetCo2));

        viewModel = new ViewModelProvider(this).get(Co2PreferencesViewModel.class);
        viewModel.getSettings().observe(getViewLifecycleOwner(), new Observer<Settings>() {
            @Override
            public void onChanged(Settings settings) {
                minCo2.setDefaultValue(settings.getCo2Min());
                maxCo2.setDefaultValue(settings.getCo2Max());
                minCo2.setSummary("Current minimum value is: " + settings.getCo2Min());
                maxCo2.setSummary("Current maximum value is: " + settings.getCo2Max());
            }
        });

        //sets Co2 preferences to factory values
        resetCo2.setOnPreferenceClickListener(preference -> {
            viewModel.resetCo2Levels();
            return true;
        });

        minCo2.setOnPreferenceChangeListener((preference, newValue) -> {
            viewModel.setMINCO2((Integer.parseInt(newValue.toString())));
            return true;
        });

        maxCo2.setOnPreferenceChangeListener((preference, newValue) -> {
            viewModel.setMAXCO2((Integer.parseInt(newValue.toString())));
            return true;
        });

        return root;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_co2, rootKey);
    }
}