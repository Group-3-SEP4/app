package com.example.it_sep4_a20_app.ui.preferences.humiditypreference;

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
/**
 * @author Tobias Sønderbo
 */
public class HumidityPreferenceFragment extends PreferenceFragmentCompat
{
    private HumidityPreferenceViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View root = super.onCreateView(inflater, container, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HumidityPreferenceViewModel.class);
        EditTextPreference maxHumidity = findPreference(getString(R.string.key_maxHumidity));
        EditTextPreference minHumidity = findPreference(getString(R.string.key_minHumidity));
        Preference resetHumidity = findPreference(getString(R.string.key_resetHumidity));
        maxHumidity.setSummary(getString(R.string.current_max_humidity, mViewModel.getStoredMaxHumiditySetting()));
        minHumidity.setSummary(getString(R.string.current_min_humidity, mViewModel.getStoredMinHumiditySetting()));

        maxHumidity.setOnPreferenceChangeListener((preference, newValue) ->
        {
            boolean valid = numberCheck(newValue);
            if(valid)
            {
                mViewModel.storeMaxHumiditySetting(Integer.parseInt(newValue.toString()));
                maxHumidity.setSummary(getString(R.string.current_max_humidity, Integer.parseInt(newValue.toString())));
            }
            return valid;
        });

        minHumidity.setOnPreferenceChangeListener(((preference, newValue) ->
        {
            boolean valid = numberCheck(newValue);
            if(valid)
            {
                mViewModel.storeMinHumiditySetting(Integer.parseInt(newValue.toString()));
                minHumidity.setSummary(getString(R.string.current_min_humidity, Integer.parseInt(newValue.toString())));
            }
            return valid;
        }));

        resetHumidity.setOnPreferenceClickListener((preference ->
        {
            mViewModel.resetHumiditySettings();
            maxHumidity.setSummary(getString(R.string.current_max_humidity, mViewModel.getStoredMaxHumiditySetting()));
            minHumidity.setSummary(getString(R.string.current_min_humidity,mViewModel.getStoredMinHumiditySetting()));
            return true;
        }));

        return root;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        setPreferencesFromResource(R.xml.preferences_humidity, rootKey);
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
