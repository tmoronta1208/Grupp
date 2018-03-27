package com.example.c4q.capstone.userinterface.user.onboarding;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateProfileFragment extends Fragment {
    View rootView;

    private static final String TAG = "CreateProfileActivity";

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference publicUserReference, privateUserReference, privateUserLocationReference;
    private FirebaseUser currentUser;

    public CreateProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(com.example.c4q.capstone.R.layout.fragment_create_profile, container, false);


        return rootView;
    }


    private void saveToDatabase() {


    }


//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }

//    public void radioGroupSelection() {
//        ageGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                ageChoice = group.findViewById(checkedId);
//                switch (checkedId) {
//                    case R.id.age_choice_one:
//                        over18 = true;
//                        over21 = true;
//                        break;
//                    case R.id.age_choice_two:
//                        over18 = true;
//                        over21 = false;
//                        break;
//                    case R.id.age_choice_three:
//                        over18 = false;
//                        over21 = false;
//                        break;
//                }
//            }
//        });
//
//        budgetGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                budgetChoice = group.findViewById(checkedId);
//                switch (checkedId) {
//                    case R.id.budget_choice_one:
//                        budgetString = "$";
//                        break;
//                    case R.id.budget_choice_two:
//                        budgetString = "$$";
//                        break;
//                    case R.id.budget_choice_three:
//                        budgetString = "$$$";
//                        break;
//                    case R.id.budget_choice_four:
//                        budgetString = "$$$$";
//                        break;
//
//                }
//            }
//        });
//
//        radiusGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                radiusChoice = group.findViewById(checkedId);
//                switch (checkedId) {
//                    case R.id.radius_choice_one:
//                        radius = 5;
//                        break;
//                    case R.id.radius_choice_two:
//                        radius = 10;
//                        break;
//                    case R.id.radius_choice_three:
//                        radius = 15;
//                        break;
//                    case R.id.radius_choice_four:
//                        radius = 20;
//                        break;
//                    case R.id.radius_choice_five:
//                        radius = 25;
//                        break;
//                }
//            }
//        });
//    }

}
