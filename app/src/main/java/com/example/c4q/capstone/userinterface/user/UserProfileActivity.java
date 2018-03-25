package com.example.c4q.capstone.userinterface.user;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventActivity;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPEventsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPGroupFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.c4q.capstone.utils.Constants.USER_ICON;


public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";
    private static final int RC_PHOTO_PICKER = 2;

    private TextView userName;
    private CircleImageView userImage;
    private Button editButton, contactButton, searchNewFriends;
    private View fragContainer;
    private String userFullName;

    private Toolbar toolbar;
    private SupportActivity activity;
    private Context context;
    private BottomNavigationView navigation;

    private ContactListFragment contactListFragment;
    private UPEventsFragment eventsFragment;
    private UPGroupFragment groupFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DatabaseReference userIconDB;

    private CurrentUser currentUserInstance = CurrentUser.getInstance();
    private FirebaseStorage firebaseStorage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setFragmentReference();
        userIconDB = FirebaseDatabase.getInstance().getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        context = this;
        activity = this;

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUserInfo();
        loadFirstFragment();

        newIconImg();

        navigation = findViewById(R.id.bottom_navigation_container);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void setFragmentReference() {
        contactListFragment = new ContactListFragment();
        eventsFragment = new UPEventsFragment();
        groupFragment = new UPGroupFragment();

    }

    private void loadFirstFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.up_bottom_frag_cont, eventsFragment)
                .addToBackStack("next")
                .commit();
    }

    private void swapFragments(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.up_bottom_frag_cont, fragment)
                .addToBackStack("next")
                .commit();
    }

    private void setUserInfo() {
        userFullName = currentUserInstance.getUserFullName();
        userName = findViewById(R.id.user_name);
        userName.setText(userFullName);
        userImage = findViewById(R.id.circle_imageview);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_profile_pop_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_profile_menu_item:
                startActivity(new Intent(UserProfileActivity.this, EditProfileActivity.class));
                break;
            case R.id.edit_preferences_menu_item:
                //TODO
                break;
            case R.id.add_friends_menu_item:
                startActivity(new Intent(UserProfileActivity.this, UserSearchActivity.class));
                break;
            case R.id.add_group_menu_item:

                startActivity(new Intent(UserProfileActivity.this, CreateEventActivity.class));

                //TODO
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_events:
                    //mTextMessage.setText("Events");
                    swapFragments(eventsFragment);
                    return true;
                case R.id.navigation_groups:
                    swapFragments(groupFragment);
                    return true;
                case R.id.navigation_friends:
                    swapFragments(contactListFragment);
                    return true;
//                case R.id.navigation_profile:
//                    mTextMessage.setText("Profile");
//                    return true;
            }
            return false;
        }
    };




}
