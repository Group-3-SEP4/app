package com.example.it_sep4_a20_app.ui.co2preferences;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;

import com.example.it_sep4_a20_app.R;

public class Co2PreferencesFragment extends PreferenceFragmentCompat {

    private Co2PreferencesViewModel viewModel;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_co2, rootKey);
        EditTextPreference minCo2 = findPreference(getString(R.string.key_minCo2));
        EditTextPreference maxCo2 = findPreference(getString(R.string.key_maxCo2));

        Preference resetCo2 = findPreference(getString(R.string.key_resetCo2));

    }
}