package com.example.it_sep4_a20_app.ui.preferences.devicepreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.example.it_sep4_a20_app.R;
import com.example.it_sep4_a20_app.data.models.Device;

import java.util.List;

/*
 * @author Claire Zubiaurre
 */
public class DevicePreferencesFragment extends PreferenceFragmentCompat {
    private static final String DIALOG_FRAGMENT_TAG =
            "androidx.preference.PreferenceFragment.DIALOG";
    private DevicePreferencesViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = super.onCreateView(inflater, container, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(DevicePreferencesViewModel.class);

        ListPreference selectedDevice = findPreference("device list");
        setDeviceListPreferenceData(selectedDevice);

        AddDevicePreference newDevice = findPreference("new device");
        Preference removeDevice = findPreference("remove device");

        selectedDevice.setOnPreferenceChangeListener((preference, newValue) -> {
            viewModel.storeSelectedDevice((String) newValue);
            setDeviceListPreferenceData(selectedDevice);
            return true;
        });

        newDevice.setOnPreferenceChangeListener((preference, newValue) -> {
            viewModel.storeDevice((Device) newValue);
            setDeviceListPreferenceData(selectedDevice);
            return true;
        });


        removeDevice.setOnPreferenceClickListener(preference -> {
            int size_before = viewModel.getDevices().size();
            String device_id= selectedDevice.getValue();
            viewModel.removeDevice(device_id);
            setDeviceListPreferenceData(selectedDevice);
            int size_after = viewModel.getDevices().size();
            removeDevice.setSummary(device_id + " was removed (" + size_before + ", " + size_after + ")");
            return true;
        });

        return root;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_device, rootKey);
    }

    protected  void setDeviceListPreferenceData(ListPreference lp) {
        List<Device> devices = viewModel.getDevices();
        String selectedDeviceId = viewModel.getSelectedDevice();
        String selectedDeviceName = "No device selected";
        CharSequence[] entries = new CharSequence[devices.size()];
        CharSequence[] entryValues = new CharSequence[devices.size()];

        for (int i = 0; i < devices.size(); i++) {
            entries[i] = devices.get(i).getName();
            entryValues[i] = devices.get(i).getRoomId();

            if (selectedDeviceId.equals(entryValues[i]))
                selectedDeviceName = entries[i].toString();
        }

        lp.setEntries(entries);
        lp.setEntryValues(entryValues);

        lp.setSummary(selectedDeviceName);

    }


    @Override
    public void onDisplayPreferenceDialog(Preference preference) {
        // Try if the preference is one of our custom Preferences
        DialogFragment dialogFragment = null;
        if (preference instanceof AddDevicePreference) {
            // Create a new instance of TimePreferenceDialogFragment with the key of the related
            // Preference
            dialogFragment = AddDeviceDialogFragmentCompat
                    .newInstance(preference.getKey());
        }

        // If it was one of our custom Preferences, show its dialog
        if (dialogFragment != null) {
            dialogFragment.setTargetFragment(this, 0);
            dialogFragment.show(getParentFragmentManager(), DIALOG_FRAGMENT_TAG);
        }
        // Could not be handled here. Try with the super method.
        else {
            super.onDisplayPreferenceDialog(preference);
        }
    }
}