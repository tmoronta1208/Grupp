package com.example.c4q.capstone.userinterface.user;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUserAgeRange;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUserBudget;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUserRadius;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileActivity extends AppCompatActivity {
    private static final String PUBLIC_USER = "public_user";
    private static final String TAG = "EditProfileActivity";
    private static final String AGE_RANGE = "age_range";
    private static final String BUDGET = "budget";
    private static final String RADIUS = "radius";

    private String userID;
    private Button saveBtn;
    private EditText firstName, lastName, zipCode;
    private Spinner ageSpinner, budgetSpinner, radiusSpinner;

    private PublicUserAgeRange publicUserAgeRange;
    private PublicUserBudget publicUserBudget;
    private PublicUserRadius publicUserRadius;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        saveBtn = findViewById(R.id.edit_profile_save_button);

        ageSpinner = findViewById(R.id.edit_profile_age_spinner);
        budgetSpinner = findViewById(R.id.edit_profile_budget_spinner);
        radiusSpinner = findViewById(R.id.edit_profile_radius_spinner);

        firstName = findViewById(R.id.edit_profile_firstname);
        lastName = findViewById(R.id.edit_profile_lastname);
        zipCode = findViewById(R.id.edit_profile_zip_code);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        user = mAuth.getCurrentUser();
        userID = user.getUid();

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

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: Added information to database: \n" +
                        dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDatabase();
            }
        });
    }

    private void saveToDatabase() {
        String firstNameString = firstName.getText().toString().trim();
        String lastNameString = lastName.getText().toString().trim();
        String zipCodeString = zipCode.getText().toString().trim();

        if (!firstNameString.equals("") && !lastNameString.equals("") && !zipCodeString.equals("")) {
            PublicUser publicUser = new PublicUser(firstNameString, lastNameString, zipCodeString);
            myRef.child(PUBLIC_USER).child(userID).setValue(publicUser);
            spinnerLogic();
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

    public void spinnerLogic() {
        ageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        publicUserAgeRange = new PublicUserAgeRange(true, true);
                        break;
                    case 1:
                        publicUserAgeRange = new PublicUserAgeRange(true, false);
                        break;
                    case 2:
                        publicUserAgeRange = new PublicUserAgeRange(false, false);
                        break;
                }
                myRef.child(PUBLIC_USER).child(userID).child(AGE_RANGE).setValue(publicUserAgeRange);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        budgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        publicUserBudget = new PublicUserBudget("$");
                        break;
                    case 1:
                        publicUserBudget = new PublicUserBudget("$$");
                        break;
                    case 2:
                        publicUserBudget = new PublicUserBudget("$$$");
                        break;
                    case 3:
                        publicUserBudget = new PublicUserBudget("$$$$");
                        break;
                }
                myRef.child(PUBLIC_USER).child(userID).child(BUDGET).setValue(publicUserAgeRange);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radiusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        publicUserRadius = new PublicUserRadius(5);
                        break;
                    case 1:
                        publicUserRadius = new PublicUserRadius(10);
                        break;
                    case 2:
                        publicUserRadius = new PublicUserRadius(15);
                        break;
                    case 3:
                        publicUserRadius = new PublicUserRadius(20);
                        break;
                    case 4:
                        publicUserRadius = new PublicUserRadius(25);
                        break;
                }
                myRef.child(PUBLIC_USER).child(userID).child(RADIUS).setValue(publicUserAgeRange);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}


