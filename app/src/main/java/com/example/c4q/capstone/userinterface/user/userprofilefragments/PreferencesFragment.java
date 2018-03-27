package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.privateuserdata.PrivateUserLocation;
import com.example.c4q.capstone.database.publicuserdata.PublicUserPreferences;
import com.example.c4q.capstone.network.barzz.BarzzNetworkCall;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;

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
<<<<<<< Updated upstream:app/src/main/java/com/example/c4q/capstone/userinterface/user/userprofilefragments/PreferencesFragment.java
    HashMap<CheckBox, String> prefs = new HashMap<>();
    public static ArrayList<String> selectedPrefs = new ArrayList<>();
=======
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID;
    private DatabaseReference rootRef, preferencesDB;
    private FirebaseUser currentUser;
    private PublicUserPreferences publicUserPreferences;
    private PrivateUser privateUser;
    private PrivateUserLocation privateUserLocation;
    private String currentUserEmail;
    Button saveToDbButton;
    public static HashMap<CheckBox, String> prefs = new HashMap<>();
    public static List<String> selectedPrefs = new ArrayList<>();
>>>>>>> Stashed changes:app/src/main/java/com/example/c4q/capstone/userinterface/user/onboarding/BarPreferencesFragment.java

    public PreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_preferences, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();
        preferencesDB = rootRef.child(PRIVATE_USER);
        saveToDbButton = view.findViewById(R.id.pref_save_button);


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

        //Foreach - added an onClick listener to each checkbox in the map

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
                        BarzzNetworkCall.start("10001");
                        Log.d("selctedPref size: ", selectedPrefs.toString());
                    }

                }
            });

//            a.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    if (a.isChecked()) {
//                        Log.d("Item Checked", prefs.get(a));
//                        selectedPrefs.add(prefs.get(a));
//                        // BarzzNetworkCall.start("10001");
//                        Log.d("selctedPref size: ", selectedPrefs.toString());
//                    }
//                    if (!a.isChecked()) {
//                        selectedPrefs.remove(a);
//                    }
////                        Log.d("selctedPref size: ", selectedPrefs.toString());
//
//                }
//
//            });
        }

        saveToDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePrefToDatabse();
            }
        });


        return view;
    }


    public void savePrefToDatabse() {


        preferencesDB.child(currentUserID).child(PREFERENCES).setValue(selectedPrefs);


    }
}


