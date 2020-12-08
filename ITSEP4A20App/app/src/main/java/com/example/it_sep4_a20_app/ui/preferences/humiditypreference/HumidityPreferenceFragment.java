package com.example.it_sep4_a20_app.ui.preferences.humiditypreference;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.it_sep4_a20_app.R;

public class HumidityPreferenceFragment extends PreferenceFragmentCompat
{

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        EditTextPreference maxHumidity = findPreference(getString(R.string.key_maxTemperature));
        EditTextPreference minHumidity = findPreference(getString(R.string.key_maxTemperature));
        Preference resetHumidity = findPreference(getString(R.string.key_resetTemperature));

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
