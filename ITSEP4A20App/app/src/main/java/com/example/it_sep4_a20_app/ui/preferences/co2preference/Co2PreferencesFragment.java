package com.example.it_sep4_a20_app.ui.preferences.co2preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.it_sep4_a20_app.R;
import com.example.it_sep4_a20_app.data.models.Settings;

public class Co2PreferencesFragment extends PreferenceFragmentCompat {

    private Co2PreferencesViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        EditTextPreference minCo2 = findPreference(getString(R.string.key_minCo2));
        EditTextPreference maxCo2 = findPreference(getString(R.string.key_maxCo2));
        Preference resetCo2 = findPreference(getString(R.string.key_resetCo2));

        mViewModel = new ViewModelProvider(this).get(Co2PreferencesViewModel.class);

        mViewModel.getSettings().observe(getViewLifecycleOwner(), settings ->
        {
            minCo2.setDefaultValue(settings.getPpmMin());
            maxCo2.setDefaultValue(settings.getPpmMax());
            minCo2.setSummary(getString(R.string.current_min_co2, settings.getPpmMin()));
            maxCo2.setSummary(getString(R.string.current_max_co2, settings.getPpmMax()));
            mViewModel.storeMinCo2Setting(settings.getPpmMin());
            mViewModel.storeMaxCo2Setting(settings.getPpmMax());
        });

        //sets Co2 preferences to factory values
        resetCo2.setOnPreferenceClickListener(preference -> {
            mViewModel.resetCo2Levels();
            return true;
        });

        minCo2.setOnPreferenceChangeListener((preference, newValue) -> {
            boolean valid = numberCheck(newValue);
            if (valid)
            {
                mViewModel.setMINCO2((Integer.parseInt(newValue.toString())));
            }
            return true;
        });

        maxCo2.setOnPreferenceChangeListener((preference, newValue) -> {
            boolean valid = numberCheck(newValue);
            if (valid)
            {
                mViewModel.setMAXCO2((Integer.parseInt(newValue.toString())));
            }
            return true;
        });

        return root;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_co2, rootKey);
    }

    private boolean numberCheck(Object newValue) {
        if( !newValue.toString().equals("")  &&  newValue.toString().matches("\\d*") ) {
            return true;
        }
        else {
            Toast.makeText(getContext(), getResources().getString(R.string.is_an_invalid_number), Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}