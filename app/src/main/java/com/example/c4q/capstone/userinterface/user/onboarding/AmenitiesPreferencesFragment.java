package com.example.c4q.capstone.userinterface.user.onboarding;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.CheckBox;
=======
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387

import com.example.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmenitiesPreferencesFragment extends Fragment {

<<<<<<< HEAD
    CheckBox brunchPref, outdoorPref, rooftopPref, danceFloorPref, fullMenuPref, videoGamePref, dartPref, poolTablePref;

=======
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
    View rootView;


    public AmenitiesPreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_amenities_preferences, container, false);

<<<<<<< HEAD

//        brunchPref = rootView.findViewById(R.id.brunch_pref);
//        outdoorPref = rootView.findViewById(R.id.outdoor_pref);
//        rooftopPref = rootView.findViewById(R.id.rooftop_pref);
//        danceFloorPref = rootView.findViewById(R.id.dance_floor_pref);
//        fullMenuPref = rootView.findViewById(R.id.fullmenu_pref);
//        videoGamePref = rootView.findViewById(R.id.video_games_pref);
//        dartPref = rootView.findViewById(R.id.darts_pref);
//        poolTablePref = rootView.findViewById(R.id.pooltable_pref);
//
//
//        BarPreferencesFragment.prefs.put(brunchPref, "brunch");
//        BarPreferencesFragment.prefs.put(outdoorPref, "outdoor");
//        BarPreferencesFragment.prefs.put(rooftopPref, "rooftop");
//        BarPreferencesFragment.prefs.put(danceFloorPref, "dance+floor");
//        BarPreferencesFragment.prefs.put(videoGamePref, "video+games");
//        BarPreferencesFragment.prefs.put(fullMenuPref, "full+menu");
//        BarPreferencesFragment.prefs.put(dartPref, "darts");
//        BarPreferencesFragment.prefs.put(poolTablePref, "pool+table");

=======
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
        return rootView;
    }

}
