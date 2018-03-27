package com.example.c4q.capstone.userinterface.user.onboarding;

<<<<<<< Updated upstream
=======
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
>>>>>>> Stashed changes
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< Updated upstream

import com.example.c4q.capstone.R;

public class OnBoardActivity extends AppCompatActivity {
    CreateProfileFragment createProfileFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
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
>>>>>>> Stashed changes

    /** @Tati this is the onboard activity, it holds all the fragments for onboarding. It starts with the Create Profile
     * Fragment (copy and paste of the EditProfileActvity) when you click next on each fragment, you should call
     * getActivity().getFragmentManager to replace the current fragment with the next.
     */

    public void loadCreatProfilFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.onboard_main_fragment_container,createProfileFragment);
        fragmentTransaction.commit();
    }
<<<<<<< Updated upstream
=======



>>>>>>> Stashed changes

}
