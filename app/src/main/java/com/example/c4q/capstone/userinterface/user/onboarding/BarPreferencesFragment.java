package com.example.c4q.capstone.userinterface.user.onboarding;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import java.util.List;
import java.util.Set;

import static com.example.c4q.capstone.utils.Constants.BAR_PREFS;
import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class BarPreferencesFragment extends Fragment {
    private View view;
    private Button clubCheckBox, loungeCheckBox, karaokeCheckBox, hookahPrefCheckBox,
            gayPrefCheckBox,beerCheckBox, beachCheckBox, hotelCheckbox, pubCheckbox, cocktailCheckbox;

    private FloatingActionButton saveButton;
    HashMap<Button, String> prefs = new HashMap<>();
    public static List<String> selectedPrefs = new ArrayList<>();

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
        beerCheckBox = view.findViewById(R.id.beer_pref);
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
        prefs.put(beerCheckBox, "beer");
        prefs.put(karaokeCheckBox, "karaoke");
        prefs.put(hookahPrefCheckBox, "hookah");
        prefs.put(gayPrefCheckBox, "gay");
        prefs.put(beachCheckBox, "beach");
        prefs.put(hotelCheckbox, "hotel");
        prefs.put(pubCheckbox, "pub");
        prefs.put(cocktailCheckbox, "cocktail");

        //Foreach - added an onClick listener to each checkbox in the map
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
        return view;
    }

    public void saveToDatabase() {


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferencesDB.child(currentUserID).child(PREFERENCES).child(BAR_PREFS).setValue(selectedPrefs);
                preferencesDB.child(currentUserID).child(PREFERENCES).child(BAR_PREFS).setValue(selectedPrefs);
                OnBoardActivity.viewPager.setCurrentItem(2);

            }
        });

    }


}
