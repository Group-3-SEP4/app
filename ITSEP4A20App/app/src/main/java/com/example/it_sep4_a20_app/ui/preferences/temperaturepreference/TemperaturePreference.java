package com.example.it_sep4_a20_app.ui.preferences.temperaturepreference;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.it_sep4_a20_app.R;

public class TemperaturePreference extends PreferenceFragmentCompat
{
    private TemperaturePreferenceViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        EditTextPreference maxTemp = findPreference(getString(R.string.key_maxTemperature));
        Preference resetMaxTemp = findPreference(getString(R.string.key_resetTemperature));

        mViewModel = new ViewModelProvider(this).get(TemperaturePreferenceViewModel.class);

        mViewModel.getSettings().observe(getViewLifecycleOwner(), settings ->
        {
            maxTemp.setDefaultValue(settings.getTemperatureSetPoint());
            maxTemp.setSummary(getString(R.string.current_max_temperature, settings.getTemperatureSetPoint()));
        });

        resetMaxTemp.setOnPreferenceClickListener(preference -> {
            mViewModel.resetMaxTemp();
            return true;
        });

        maxTemp.setOnPreferenceChangeListener((preference, newValue) -> {
            boolean valid = numberCheck(newValue);
            if(valid)
            mViewModel.setMaxTemp((Integer.parseInt(newValue.toString())));
            return valid;
        });
        return root;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_temperature, rootKey);
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
