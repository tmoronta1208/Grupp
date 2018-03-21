package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment {
    private View view;
    private View clubRadio;
    private View loungeRadio;
    private View beerRadio;
    private View karaokeRadio;

    public PreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_preferences, container, false);

        clubRadio = view.findViewById(R.id.club_pref);
        loungeRadio = view.findViewById(R.id.lounge_pref);
        beerRadio = view.findViewById(R.id.beer_pref);
        karaokeRadio = view.findViewById(R.id.karaoke_pref);











        return view;
    }

}
