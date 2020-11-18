package com.example.it_sep4_a20_app.ui.settings;

import android.os.Bundle;

import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.it_sep4_a20_app.R;



public class SettingsFragment extends PreferenceFragmentCompat
{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey);

        Preference co2Preference = findPreference(getString(R.string.key_preference_co2));
        co2Preference.setOnPreferenceClickListener(preference ->
        {
            Navigation.findNavController(getView()).navigate(R.id.nav_co2_preferences);
            return true;
        });
    }
}
