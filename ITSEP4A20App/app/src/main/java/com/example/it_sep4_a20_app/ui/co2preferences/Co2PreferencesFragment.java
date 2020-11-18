package com.example.it_sep4_a20_app.ui.co2preferences;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.it_sep4_a20_app.R;

public class Co2PreferencesFragment extends PreferenceFragmentCompat
{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        setPreferencesFromResource(R.xml.preferences_co2, rootKey);
    }
}
