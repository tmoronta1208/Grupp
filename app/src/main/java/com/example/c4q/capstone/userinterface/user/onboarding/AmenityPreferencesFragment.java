package com.example.c4q.capstone.userinterface.user.onboarding;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmenityPreferencesFragment extends Fragment {


    public AmenityPreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_amenity_preferences, container, false);
    }

}
