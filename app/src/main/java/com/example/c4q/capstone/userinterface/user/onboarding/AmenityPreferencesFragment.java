package com.example.c4q.capstone.userinterface.user.onboarding;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmenityPreferencesFragment extends Fragment {
    Button brunch, outdoorSeating, rooftop, danceFloor, fullMenu, videoGames, darts, poolTables;
   com.getbase.floatingactionbutton.FloatingActionButton saveButton;
    View rootView;
    HashMap<Button, String> prefs = new HashMap<>();
    List<String> selectedPrefs = new ArrayList<>();
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID;
    private DatabaseReference rootRef, preferencesDB;
    private FirebaseUser currentUser;


    public AmenityPreferencesFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ClickableViewAccessibility")
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

            for (final Button a : prefs.keySet()) {

                a.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(selectedPrefs.contains(prefs.get(a))){
                            selectedPrefs.remove(prefs.get(a));
                        }
                        Log.d("selctedPref size: ", selectedPrefs.toString());

                        a.setBackground(getActivity().getResources().getDrawable(R.drawable.pefrencebuttonpressed));

                        selectedPrefs.add(prefs.get(a));

                        Log.d("selctedPref size: ", selectedPrefs.toString());

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
                preferencesDB.child(PUBLIC_USER).child(currentUserID).child(PREFERENCES).child(AMENITY_PREFS).setValue(selectedPrefs);
                preferencesDB.child(PRIVATE_USER).child(currentUserID).child(PREFERENCES).child(AMENITY_PREFS).setValue(selectedPrefs);
                CurrentUserPost.getInstance().postNewAmenityPreferences(selectedPrefs);
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }

}
