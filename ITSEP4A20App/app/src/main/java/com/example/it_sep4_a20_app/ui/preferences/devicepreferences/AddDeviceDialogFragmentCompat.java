package com.example.it_sep4_a20_app.ui.preferences.devicepreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.it_sep4_a20_app.R;
import com.example.it_sep4_a20_app.data.models.Device;

/*
 * @author Claire Zubiaurre


Based on library implementation of EditTextPreferenceDialogFragmentCompat
Source found here:
https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev/preference/preference/src/main/java/androidx/preference/EditTextPreferenceDialogFragmentCompat.java
 */

public class AddDeviceDialogFragmentCompat extends androidx.preference.PreferenceDialogFragmentCompat {

    private static final String SAVE_STATE_TEXT = "AddDevicePreferenceDialogFragment.text";


    private EditText mEditText1;
    private EditText mEditText2;

    private CharSequence mText1;
    private CharSequence mText2;


    public static AddDeviceDialogFragmentCompat newInstance(String key) {
        final AddDeviceDialogFragmentCompat
                fragment = new AddDeviceDialogFragmentCompat();
        final Bundle b = new Bundle(1);
        b.putString(ARG_KEY, key);
        fragment.setArguments(b);
        return fragment;
    }

    private AddDevicePreference getAddDevicePreference() {
        return (AddDevicePreference) getPreference();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onBindDialogView(View view) {
        super.onBindDialogView(view);
        mEditText1 = view.findViewById(R.id.edit1);
        mEditText1.requestFocus();
        mEditText1.setText(mText1);

        mEditText2 = view.findViewById(R.id.edit2);
        mEditText2.requestFocus();
        mEditText2.setText(mText2);

        // Place cursor at the end
        mEditText1.setSelection(mEditText1.getText().length());
        if (getAddDevicePreference().getOnBindEditTextListener() != null) {
            getAddDevicePreference().getOnBindEditTextListener().onBindEditText(mEditText1);
        }
    }



    @Override
    public void onDialogClosed(boolean positiveResult) {
        if (positiveResult) {
            String id = mEditText1.getText().toString();
            String name = mEditText2.getText().toString();
            Device device = new Device();
            device.setRoomId(id);
            device.setName(name);
            final AddDevicePreference preference = getAddDevicePreference();
            preference.callChangeListener(device);
        }
    }


}
