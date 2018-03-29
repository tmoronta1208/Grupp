package com.example.c4q.capstone.userinterface.user.onboarding;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.privateuserdata.PrivateUserLocation;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.UserSearch;
import com.example.c4q.capstone.userinterface.user.EditProfileActivity;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.PRIVATE_LOCATION;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_SEARCH;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateProfileFragment extends Fragment {
    View rootView;

    private static final String TAG = "CreateProfileActivity";

    private String currentUserID, firstNameString, lastNameString, zipCodeSting, budgetString;
    private boolean over18, over21, share_location;
    private int radius;
    private double lat, lng;

    private FloatingActionButton saveBtn;
    private EditText firstName, lastName, zipCode;
    private RadioGroup ageGroup, budgetGroup, radiusGroup;
    private RadioButton ageChoice, budgetChoice, radiusChoice;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference rootRef, publicUserReference, privateUserReference, privateUserLocationReference, searchUserReference;
    private FirebaseUser currentUser;
    private UserSearch userSearch;
    private PublicUser publicUser;
    private PrivateUser privateUser;
    private PrivateUserLocation privateUserLocation;
    private String currentUserEmail;


    public CreateProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(com.example.c4q.capstone.R.layout.fragment_create_profile, container, false);

        saveBtn = rootView.findViewById(R.id.edit_profile_save_button);

        ageGroup = rootView.findViewById(R.id.radio_group_age);
        budgetGroup = rootView.findViewById(R.id.radio_group_budget);
        radiusGroup = rootView.findViewById(R.id.radio_group_radius);

        firstName = rootView.findViewById(R.id.edit_profile_firstname);
        lastName = rootView.findViewById(R.id.edit_profile_lastname);
        zipCode = rootView.findViewById(R.id.edit_profile_zip_code);

        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();

        publicUserReference = rootRef.child(PUBLIC_USER);
        privateUserReference = rootRef.child(PRIVATE_USER);
        privateUserLocationReference = rootRef.child(PRIVATE_USER);
        searchUserReference = rootRef.child(USER_SEARCH);
        saveBtn = rootView.findViewById(R.id.create_profile_save_button);

        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        currentUserEmail = currentUser.getEmail();

        radioGroupSelection();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (currentUser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + currentUser.getUid());
//                    Toast.makeText(EditProfileActivity.this, "Successfully signed in with: " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                    Toast.makeText(EditProfileActivity.this, "Successfully signed out.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                privateUser = dataSnapshot.child(currentUserID).getValue(PrivateUser.class);
                privateUserLocation = dataSnapshot.child(currentUserID).getValue(PrivateUserLocation.class);
                publicUser = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
                userSearch = dataSnapshot.child(currentUserID).getValue(UserSearch.class);

                Log.d(TAG, "onDataChange: Added information to database: \n" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };

        searchUserReference.addValueEventListener(valueEventListener);
        publicUserReference.addValueEventListener(valueEventListener);
        privateUserReference.addValueEventListener(valueEventListener);
        privateUserLocationReference.addValueEventListener(valueEventListener);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase();
            }
        });




        return rootView;
    }
    private void location(){
        ///////////////////////////////////////////
        /**
         * code below needs to be place in the launch of the app so the user can give location
         * permission beforehand.
         */
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(getActivity().LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
            share_location = true;
            return;
        }

       /* Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lat = location.getLatitude();
        lng = location.getLongitude();*/

    }

    private void saveToDatabase() {
        firstNameString = firstName.getText().toString().trim();
        lastNameString = lastName.getText().toString().trim();
        zipCodeSting = zipCode.getText().toString();

        if (!firstNameString.equals("") && !lastNameString.equals("") && !zipCodeSting.equals("")) {

            publicUser = new PublicUser(currentUserID,firstNameString, lastNameString, zipCodeSting, budgetString, currentUserEmail, over18, over21, radius);
            privateUser = new PrivateUser(firstNameString, lastNameString, over18, over21, radius);
            privateUserLocation = new PrivateUserLocation(share_location, lat, lng);
            userSearch = new UserSearch(currentUserEmail);

            /**
             * searchUserReference needs to be added at time of account creation
             */
            searchUserReference.child(currentUserID).setValue(userSearch);
            publicUserReference.child(currentUserID).setValue(publicUser);
            privateUserReference.child(currentUserID).setValue(privateUser);
            privateUserLocationReference.child(currentUserID).child(PRIVATE_LOCATION).setValue(privateUserLocation);

            //startActivity(new Intent(CreateProfileFragment.this.getActivity(), UserProfileActivity.class));
        } else {
            firstName.setError("Required");
            lastName.setError("Required");
            zipCode.setError("Required");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void radioGroupSelection() {
        ageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ageChoice = group.findViewById(checkedId);
                switch (checkedId) {
                    case R.id.age_choice_one:
                        over18 = true;
                        over21 = true;
                        break;
                    case R.id.age_choice_two:
                        over18 = true;
                        over21 = false;
                        break;
                    case R.id.age_choice_three:
                        over18 = false;
                        over21 = false;
                        break;
                }
            }
        });

        budgetGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                budgetChoice = group.findViewById(checkedId);
                switch (checkedId) {
                    case R.id.budget_choice_one:
                        budgetString = "$";
                        break;
                    case R.id.budget_choice_two:
                        budgetString = "$$";
                        break;
                    case R.id.budget_choice_three:
                        budgetString = "$$$";
                        break;
                    case R.id.budget_choice_four:
                        budgetString = "$$$$";
                        break;

                }
            }
        });

        radiusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radiusChoice = group.findViewById(checkedId);
                switch (checkedId) {
                    case R.id.radius_choice_one:
                        radius = 5;
                        break;
                    case R.id.radius_choice_two:
                        radius = 10;
                        break;
                    case R.id.radius_choice_three:
                        radius = 15;
                        break;
                    case R.id.radius_choice_four:
                        radius = 20;
                        break;
                    case R.id.radius_choice_five:
                        radius = 25;
                        break;
                }
            }
        });
    }

}
