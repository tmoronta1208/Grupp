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

import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.RESTAURANT_PREFS;
import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantPreferencesFragment extends Fragment {
    CheckBox brunch_food, vegan_pref, pescetarian_pref, asian_pref, latin_pref, american_pref, breakfast_pref, vegetarian_pref;
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
        rootView = inflater.inflate(R.layout.fragment_restaurant_preferences, container, false);

        //Firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();
        preferencesDB = rootRef.child(PRIVATE_USER);

        brunch_food = rootView.findViewById(R.id.brunch_food_pref);
        vegan_pref = rootView.findViewById(R.id.vegan_pref);
        american_pref = rootView.findViewById(R.id.american_pref);
        latin_pref = rootView.findViewById(R.id.latin_food_pref);
        asian_pref = rootView.findViewById(R.id.asian_food);
        breakfast_pref = rootView.findViewById(R.id.breakfast_pref);
        pescetarian_pref = rootView.findViewById(R.id.pescetarian_pref);
        vegetarian_pref = rootView.findViewById(R.id.vegetarian_pref);

        saveButton = rootView.findViewById(R.id.amenity_pref_save_button);


        prefs.put(brunch_food, "brunch");
        prefs.put(vegan_pref, "vegan");
        prefs.put(american_pref, "american");
        prefs.put(asian_pref, "asian");
        prefs.put(latin_pref, "latin");
        prefs.put(breakfast_pref, "breakfast");
        prefs.put(pescetarian_pref, "pescetarian");
        prefs.put(vegetarian_pref, "vegetarian");

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
                preferencesDB.child(PUBLIC_USER).child(currentUserID).child(PREFERENCES).child(RESTAURANT_PREFS).setValue(selectedPrefs);
                preferencesDB.child(PRIVATE_USER).child(currentUserID).child(PREFERENCES).child(RESTAURANT_PREFS).setValue(selectedPrefs);
                CurrentUserPost.getInstance().postNewAmenityPreferences(selectedPrefs);
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

    }

}
