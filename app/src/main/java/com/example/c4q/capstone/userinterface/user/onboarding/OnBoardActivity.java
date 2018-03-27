package com.example.c4q.capstone.userinterface.user.onboarding;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
>>>>>>> Stashed changes
=======
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
=======
>>>>>>> parent of aba0275... prefs update
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< Updated upstream
=======
import android.util.Log;
import android.widget.Toast;
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
=======
>>>>>>> parent of aba0275... prefs update

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.privateuserdata.PrivateUserLocation;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserPreferences;
import com.example.c4q.capstone.database.publicuserdata.UserSearch;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

public class OnBoardActivity extends AppCompatActivity {
<<<<<<< HEAD
    CreateProfileFragment createProfileFragment;
<<<<<<< HEAD
=======
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.privateuserdata.PrivateUserLocation;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserPreferences;
import com.example.c4q.capstone.database.publicuserdata.UserSearch;
import com.example.c4q.capstone.network.barzz.BarzzNetworkCall;
import com.example.c4q.capstone.userinterface.user.EditProfileActivity;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_LOCATION;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

public class OnBoardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID;
    private DatabaseReference rootRef, preferencesDB;
    private FirebaseUser currentUser;
    private PublicUserPreferences publicUserPreferences;
    private PrivateUser privateUser;
    private PrivateUserLocation privateUserLocation;
    private String currentUserEmail;
    private static final int NUM_PAGES = 3;
    private static final String TAG = "";
    private ViewPager viewPager;
    private FragmentStatePagerAdapter pagerAdapter;
    Button saveToDbButton;
>>>>>>> Stashed changes
=======

    private static final int NUM_PAGES = 3;
    private static final String TAG = "";
    private ViewPager viewPager;
    private FragmentStatePagerAdapter pagerAdapter;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID, firstNameString, lastNameString, zipCodeSting, budgetString;
    PublicUserPreferences publicUserPreferences;

    private DatabaseReference rootRef, publicUserReference, privateUserReference, privateUserLocationReference, searchUserReference;
    private FirebaseUser currentUser;
    private UserSearch userSearch;
    private PublicUser publicUser;
    private PrivateUser privateUser;
    private PrivateUserLocation privateUserLocation;
    private String currentUserEmail;

>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
=======
>>>>>>> parent of aba0275... prefs update

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< Updated upstream
        setContentView(R.layout.activity_on_board);
        loadCreatProfilFragment();
    }
=======
        setContentView(R.layout.activity_view_pager2);

        //Firebase
//        mAuth = FirebaseAuth.getInstance();
//        currentUser = mAuth.getCurrentUser();
//        currentUserID = currentUser.getUid();
//        rootRef = FirebaseDatabase.getInstance().getReference();
//        preferencesDB = rootRef.child(PRIVATE_USER);
//        saveToDbButton = findViewById(R.id.pref_save_button);


        viewPager = findViewById(R.id.onboarding_pager);
        pagerAdapter = new ViewPagerActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


    }

=======
        setContentView(R.layout.activity_view_pager2);

        viewPager = findViewById(R.id.onboarding_pager);
        pagerAdapter = new ViewPagerActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        mAuth = FirebaseAuth.getInstance();
        rootRef = FirebaseDatabase.getInstance().getReference();
        publicUserReference = rootRef.child(PUBLIC_USER);
        privateUserReference = rootRef.child(PRIVATE_USER);
        privateUserLocationReference = rootRef.child(PRIVATE_USER);
        searchUserReference = rootRef.child(PUBLIC_USER);

        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        currentUserEmail = currentUser.getEmail();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (currentUser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + currentUser.getUid());
                    Toast.makeText(OnBoardActivity.this, "Successfully signed in with: " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(OnBoardActivity.this, "Successfully signed out.", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
<<<<<<< HEAD
=======

>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
    }

    private class ViewPagerActivityAdapter extends FragmentStatePagerAdapter {
        public ViewPagerActivityAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new CreateProfileFragment();
                    break;
                case 1:
                    fragment = new BarPreferencesFragment();
                    break;

                case 2:
                    fragment = new AmenitiesPreferencesFragment();
                    break;
            }
            return fragment;
<<<<<<< HEAD
>>>>>>> Stashed changes
=======
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
=======
        setContentView(R.layout.activity_on_board);
        loadCreatProfilFragment();
    }
>>>>>>> parent of aba0275... prefs update

        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< Updated upstream
=======



>>>>>>> Stashed changes
=======
//
//
//    /** @Tati this is the onboard activity, it holds all the fragments for onboarding. It starts with the Create Profile
//     * Fragment (copy and paste of the EditProfileActvity) when you click next on each fragment, you should call
//     * getActivity().getFragmentManager to replace the current fragment with the next.
//     */
//
//    public void loadCreatProfilFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.onboard_main_fragment_container,createProfileFragment);
//        fragmentTransaction.commit();
//    }
>>>>>>> e78fbaa914bf5df5bdcd537990f9686e8c782387
=======
>>>>>>> parent of aba0275... prefs update

}
