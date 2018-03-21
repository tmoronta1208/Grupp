package com.example.c4q.capstone.userinterface.user;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.model.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.model.privateuserdata.PrivateUserLocation;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.model.publicuserdata.UserSearch;
import com.example.c4q.capstone.utils.Constants;
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

public class EditProfileActivity extends AppCompatActivity {
    private static final String TAG = "EditProfileActivity";

    private String userID, firstNameString, lastNameString, zipCodeSting, budgetString;
    private boolean over18, over21, share_location;
    private int radius;
    private double lat, lng;

    private Button saveBtn;
    private EditText firstName, lastName, zipCode;
    private RadioGroup ageGroup, budgetGroup, radiusGroup;
    private RadioButton ageChoice, budgetChoice, radiusChoice;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference publicUserReference, privateUserReference, privateUserLocationReference, searchUserReference;
    private FirebaseUser user;
    private UserSearch userSearch;
    private PublicUser publicUser;
    private PrivateUser privateUser;
    private PrivateUserLocation privateUserLocation;
    private String currentUserEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveBtn = findViewById(R.id.edit_profile_save_button);

        ageGroup = findViewById(R.id.radio_group_age);
        budgetGroup = findViewById(R.id.radio_group_budget);
        radiusGroup = findViewById(R.id.radio_group_radius);

        firstName = findViewById(R.id.edit_profile_firstname);
        lastName = findViewById(R.id.edit_profile_lastname);
        zipCode = findViewById(R.id.edit_profile_zip_code);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        publicUserReference = firebaseDatabase.getReference().child(PUBLIC_USER);
        privateUserReference = firebaseDatabase.getReference().child(PRIVATE_USER);
        privateUserLocationReference = firebaseDatabase.getReference().child(PRIVATE_USER);
        searchUserReference = firebaseDatabase.getReference().child(PUBLIC_USER);

        user = mAuth.getCurrentUser();
        userID = user.getUid();
        currentUserEmail = user.getEmail();

        radioGroupSelection();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    Toast.makeText(EditProfileActivity.this, "Successfully signed in with: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(EditProfileActivity.this, "Successfully signed out.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                privateUser = dataSnapshot.child(userID).getValue(PrivateUser.class);
                privateUserLocation = dataSnapshot.child(userID).getValue(PrivateUserLocation.class);
                publicUser = dataSnapshot.child(userID).getValue(PublicUser.class);
                userSearch = dataSnapshot.child(userID).getValue(UserSearch.class);

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

        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1020);
            return;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lat = location.getLatitude();
        lng = location.getLongitude();
    }

    private void saveToDatabase() {
        firstNameString = firstName.getText().toString().trim();
        lastNameString = lastName.getText().toString().trim();
        zipCodeSting = zipCode.getText().toString();

        if (!firstNameString.equals("") && !lastNameString.equals("") && !zipCodeSting.equals("")) {

            publicUser = new PublicUser(firstNameString, lastNameString, zipCodeSting, budgetString, over18, over21, radius);
            privateUser = new PrivateUser(firstNameString, lastNameString, over18, over21, radius);
            privateUserLocation = new PrivateUserLocation(share_location, lat, lng);
            userSearch = new UserSearch(currentUserEmail);

            searchUserReference.child(userID).setValue(userSearch);
            publicUserReference.child(userID).setValue(publicUser);
            privateUserReference.child(userID).setValue(privateUser);
            privateUserLocationReference.child(userID).child(PRIVATE_LOCATION).setValue(privateUserLocation);

            startActivity(new Intent(EditProfileActivity.this, UserProfileActivity.class));
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


