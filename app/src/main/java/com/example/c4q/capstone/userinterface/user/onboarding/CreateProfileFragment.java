package com.example.c4q.capstone.userinterface.user.onboarding;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.privateuserdata.PrivateUserLocation;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.DEFAULT_ICON;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_LOCATION;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_ICON;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateProfileFragment extends Fragment {
    View rootView;

    private static final String TAG = "CreateProfileActivity";

    private String currentUserID, firstNameString, lastNameString, zipCodeString, budgetString;
    private boolean over18, over21, share_location;
    private int radius;
    private double lat, lng;

    private FloatingActionButton saveBtn;

    private EditText firstName, lastName, zipCode;
    private RadioGroup ageGroup, budgetGroup, radiusGroup;
    private RadioButton ageChoice, budgetChoice, radiusChoice;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference rootRef, publicUserReference, privateUserReference, privateUserLocationReference, userIconReference;
    private FirebaseUser currentUser;
    private PublicUser publicUser;
    private UserIcon userIcon;
    private PrivateUser privateUser;
    private PrivateUserLocation privateUserLocation;
    private PublicUserDetails publicUserDetails;
    private String currentUserEmail;


    public CreateProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(com.example.c4q.capstone.R.layout.fragment_create_profile, container, false);

//        saveBtn = rootView.findViewById(R.id.create_profile_save_button);

        ageGroup = rootView.findViewById(R.id.radio_group_age);
        budgetGroup = rootView.findViewById(R.id.radio_group_budget);
        radiusGroup = rootView.findViewById(R.id.radio_group_radius);

        firstName = rootView.findViewById(R.id.edit_profile_firstname);
        lastName = rootView.findViewById(R.id.edit_profile_lastname);
        zipCode = rootView.findViewById(R.id.edit_profile_zip_code);

        rootRef = FirebaseDatabase.getInstance().getReference();

        publicUserReference = rootRef.child(PUBLIC_USER);
        privateUserReference = rootRef.child(PRIVATE_USER);
        privateUserLocationReference = rootRef.child(PRIVATE_USER);
        userIconReference = rootRef.child(USER_ICON);

        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        currentUserEmail = currentUser.getEmail();

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                privateUser = dataSnapshot.child(currentUserID).getValue(PrivateUser.class);
                privateUserLocation = dataSnapshot.child(currentUserID).getValue(PrivateUserLocation.class);
                publicUser = dataSnapshot.child(currentUserID).getValue(PublicUser.class);

                Log.d(TAG, "onDataChange: Added information to database: \n" + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        };

        publicUserReference.addValueEventListener(valueEventListener);
        privateUserReference.addValueEventListener(valueEventListener);
        privateUserLocationReference.addValueEventListener(valueEventListener);
        userIconReference.addValueEventListener(valueEventListener);

        saveBtn = rootView.findViewById(R.id.create_profile_save_button);

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


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase();
            }
        });

        return rootView;
    }


    private void location() {
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
        zipCodeString = zipCode.getText().toString();
        userIcon = new UserIcon(DEFAULT_ICON);

        if (!firstNameString.equals("") && !lastNameString.equals("") && !zipCodeString.equals("")) {

            publicUser = new PublicUser(currentUserID, firstNameString, lastNameString, zipCodeString, budgetString, currentUserEmail, userIcon, over18, over21, radius);

            privateUser = new PrivateUser(firstNameString, lastNameString, over18, over21, radius);

            privateUserLocation = new PrivateUserLocation(share_location, lat, lng);

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
