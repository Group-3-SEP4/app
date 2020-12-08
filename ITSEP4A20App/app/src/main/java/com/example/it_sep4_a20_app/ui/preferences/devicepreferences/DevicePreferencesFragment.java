package com.example.it_sep4_a20_app.ui.preferences.devicepreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.it_sep4_a20_app.R;

public class DevicePreferencesFragment extends PreferenceFragmentCompat {

    private DevicePreferencesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        EditTextPreference roomID = findPreference(getString(R.string.key_deviceID));

        viewModel = new ViewModelProvider(this).get(DevicePreferencesViewModel.class);

        //roomID.setDefaultValue(viewModel.getRoomId());
        roomID.setSummary("Current device ID is: " + viewModel.getDeviceId());

        roomID.setOnPreferenceChangeListener((preference, newValue) -> {
            viewModel.setDeviceId(newValue.toString());
            //roomID.setDefaultValue(viewModel.getRoomId());
            roomID.setSummary("Current device ID is: " + viewModel.getDeviceId());
            return true;
        });

        return root;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_device, rootKey);
    }
}