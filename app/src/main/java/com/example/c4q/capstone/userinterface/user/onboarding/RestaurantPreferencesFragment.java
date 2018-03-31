package com.example.c4q.capstone.userinterface.user.onboarding;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.AMENITY_PREFS;
import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantPreferencesFragment extends Fragment {
    CheckBox brunch, outdoorSeating, rooftop, danceFloor, fullMenu, videoGames, darts, poolTables;
    Button saveButton;
    View rootView;
    HashMap<CheckBox, String> prefs = new HashMap<>();
    List<String> selectedPrefs = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID;
    private DatabaseReference rootRef, preferencesDB;
    private FirebaseUser currentUser;


    public RestaurantPreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_amenity_preferences, container, false);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();
        preferencesDB = rootRef.child(PRIVATE_USER);

        brunch = rootView.findViewById(R.id.brunch_pref);
        outdoorSeating = rootView.findViewById(R.id.outdoor_pref);
        rooftop = rootView.findViewById(R.id.rooftop_pref);
        danceFloor = rootView.findViewById(R.id.dance_floor_pref);
        fullMenu = rootView.findViewById(R.id.full_menu_pref);
        videoGames = rootView.findViewById(R.id.video_games_pref);
        darts = rootView.findViewById(R.id.darts_pref);
        poolTables = rootView.findViewById(R.id.pool_table_pref);

        saveButton = rootView.findViewById(R.id.amenity_pref_save_button);


        prefs.put(brunch, "brunch");
        prefs.put(outdoorSeating, "outdoor+seating");
        prefs.put(rooftop, "rooftop");
        prefs.put(danceFloor, "dance+floor");
        prefs.put(fullMenu, "full+menu");
        prefs.put(videoGames, "video+games");
        prefs.put(darts, "darts");
        prefs.put(poolTables, "pool+tables");

        for (final CheckBox a : prefs.keySet()) {

            a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (!a.isChecked()) {
                        selectedPrefs.remove(prefs.get(a));
                        //selectedPrefs.add(null);
                        Log.d("selctedPref size: ", selectedPrefs.toString());

                    }
                    if (a.isChecked()) {
                        Log.d("Item Checked", prefs.get(a));
                        selectedPrefs.add(prefs.get(a));
                      //  BarzzNetworkCall.start("10001");
                        Log.d("selctedPref size: ", selectedPrefs.toString());
                    }

                }
            });


        }
        saveToDatabase();
        return rootView;
    }

    public void saveToDatabase() {


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesDB.child(currentUserID).child(PREFERENCES).child(AMENITY_PREFS).setValue(selectedPrefs);
                CurrentUserPost.getInstance().postNewAmenityPreferences(selectedPrefs);
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }

}
