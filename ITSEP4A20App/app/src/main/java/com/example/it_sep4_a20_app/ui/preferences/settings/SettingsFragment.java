package com.example.it_sep4_a20_app.ui.preferences.settings;

import android.os.Bundle;
import androidx.navigation.Navigation;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.it_sep4_a20_app.R;
/**
 * @author Tobias Sønderbo, David Nguyen
 */
public class SettingsFragment extends PreferenceFragmentCompat
{
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_settings, rootKey);

        Preference co2Preference = findPreference(getString(R.string.key_preference_co2));
        co2Preference.setOnPreferenceClickListener(preference ->
        {
            Navigation.findNavController(getView()).navigate(R.id.nav_co2_preferences);
            return true;
        });

        Preference tempPreference = findPreference(getString(R.string.key_preference_temp));
        tempPreference.setOnPreferenceClickListener(preference ->
        {
            Navigation.findNavController(getView()).navigate(R.id.nav_temp_preferences);
            return true;
        });

        Preference humidityPreference = findPreference(getString(R.string.key_preference_humidity));
        humidityPreference.setOnPreferenceClickListener(preference ->
        {
            Navigation.findNavController(getView()).navigate(R.id.nav_humidity_preferences);
            return true;
        });

        Preference devicePreference = findPreference(getString(R.string.key_preference_device));
        devicePreference.setOnPreferenceClickListener(preference ->
        {
            Navigation.findNavController(getView()).navigate(R.id.nav_device_preferences);
            return true;
        });
    }
}
