package com.example.c4q.capstone.userinterface.user.settingsfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacySettingsFragment extends Fragment {


    public PrivacySettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_privacy_settings, container, false);
    }

}
