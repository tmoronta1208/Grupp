package com.example.c4q.capstone.userinterface.user.onboarding;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.network.barzz.BarzzNetworkCall;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.c4q.capstone.utils.Constants.BAR_PREFS;
import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarPreferencesFragment extends Fragment {
    private View view;
    private CheckBox clubCheckBox, loungeCheckBox, karaokeCheckBox, hookahPrefCheckBox,
            gayPrefCheckBox, beachCheckBox, hotelCheckbox, pubCheckbox, cocktailCheckbox;
    Switch beerCheckBox;
    private FloatingActionButton saveButton;
    HashMap<CheckBox, String> prefs = new HashMap<>();
    public static ArrayList<String> selectedPrefs = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID;
    private DatabaseReference rootRef, preferencesDB;
    private FirebaseUser currentUser;

    public BarPreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_bar_preferences, container, false);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();

        rootRef = FirebaseDatabase.getInstance().getReference();
        preferencesDB = rootRef.child(PRIVATE_USER);

        clubCheckBox = view.findViewById(R.id.club_pref);
        loungeCheckBox = view.findViewById(R.id.lounge_pref);
//        beerCheckBox = view.findViewById(R.id.beer_pref);
        karaokeCheckBox = view.findViewById(R.id.karaoke_pref);
        hookahPrefCheckBox = view.findViewById(R.id.hookah_pref);
        gayPrefCheckBox = view.findViewById(R.id.gayBar_pref);
        beachCheckBox = view.findViewById(R.id.beach_pref);
        hotelCheckbox = view.findViewById(R.id.hotel_pref);
        pubCheckbox = view.findViewById(R.id.pub_pref);
        cocktailCheckbox = view.findViewById(R.id.cocktail_pref);

        saveButton = view.findViewById(R.id.bar_pref_save_button);


        //Added all Checkboxes to a map with the checkbox as the key and type keyword as the value

        prefs.put(clubCheckBox, "club");
        prefs.put(loungeCheckBox, "lounge");
        // prefs.put(beerCheckBox, "beer");
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
                        //   BarzzNetworkCall.start("10001");
                        Log.d("selctedPref size: ", selectedPrefs.toString());
                    }

                }
            });


        }

        saveToDatabase();
        return view;
    }

    public void saveToDatabase() {


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesDB.child(currentUserID).child(PREFERENCES).child(BAR_PREFS).setValue(selectedPrefs);
                OnBoardActivity.viewPager.setCurrentItem(2);

            }
        });

    }


}
