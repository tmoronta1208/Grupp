package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.network.barzz.BarzzNetworkCall;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferencesFragment extends Fragment {
    private View view;
    private CheckBox clubCheckBox;
    private CheckBox loungeCheckBox;
    private CheckBox beerCheckBox;
    private CheckBox karaokeCheckBox;
    private CheckBox hookahPrefCheckBox;
    private CheckBox gayPrefCheckBox;
    private CheckBox beachCheckBox;
    private CheckBox hotelCheckbox;
    private CheckBox pubCheckbox;
    private CheckBox cocktailCheckbox;
    HashMap<CheckBox, String> prefs = new HashMap<>();
    public static ArrayList<String> selectedPrefs = new ArrayList<>();

    public PreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_preferences, container, false);


        clubCheckBox = view.findViewById(R.id.club_pref);
        loungeCheckBox = view.findViewById(R.id.lounge_pref);
        beerCheckBox = view.findViewById(R.id.beer_pref);
        karaokeCheckBox = view.findViewById(R.id.karaoke_pref);
        hookahPrefCheckBox = view.findViewById(R.id.hookah_pref);
        gayPrefCheckBox = view.findViewById(R.id.gayBar_pref);
        beachCheckBox = view.findViewById(R.id.beach_pref);
        hotelCheckbox = view.findViewById(R.id.hotel_pref);
        pubCheckbox = view.findViewById(R.id.pub_pref);
        cocktailCheckbox = view.findViewById(R.id.cocktail_pref);

        //Added all Checkboxes to a map with the checkbox as the key and type keyword as the value

        prefs.put(clubCheckBox, "club");
        prefs.put(loungeCheckBox, "lounge");
        prefs.put(beerCheckBox, "beer");
        prefs.put(karaokeCheckBox, "karaoke");
        prefs.put(hookahPrefCheckBox, "hookah");
        prefs.put(gayPrefCheckBox, "gay");
        prefs.put(beachCheckBox, "beach");
        prefs.put(hotelCheckbox, "hotel");
        prefs.put(pubCheckbox, "pub");
        prefs.put(cocktailCheckbox, "cocktail");

        //Foreach - added an onClick listener to each checkbox in the map and t

        for (final CheckBox a : prefs.keySet()) {

            a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (a.isChecked()) {
                        Log.d("Item Checked", prefs.get(a));
                        selectedPrefs.add(prefs.get(a));
                       // BarzzNetworkCall.start("10001");
                        Log.d("selctedPref size: ", selectedPrefs.toString());
                    }

                }
            });
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
}
