package com.example.c4q.capstone.userinterface.user;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.MainActivity;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.VenueVoteSwipeActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.EventsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.GroupFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";
    private static final String PUBLIC_USER = "public_user";

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth authentication;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference publicUserDatabaseReference;
    private String currentUserID;

    private DrawerLayout navDrawerLayout;
    private TextView userName;
    private CircleImageView userImage;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userImage = findViewById(R.id.circle_imageview);
        userName = findViewById(R.id.user_name);
        editButton = findViewById(R.id.edit_button);

        authentication = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        publicUserDatabaseReference = firebaseDatabase.getReference().child(PUBLIC_USER);
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();

        setNavDrawerLayout();
//        setToolbar();
//        setViews();

        GroupFragment groupFragment = new GroupFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.group_frag_cont, groupFragment, "GROUP FRAG");

        EventsFragment eventsFragment = new EventsFragment();
        FragmentManager eFragmentManager = getSupportFragmentManager();
        FragmentTransaction eFragmentTransaction = eFragmentManager.beginTransaction();
        eFragmentTransaction.add(R.id.events_frag_container, eventsFragment, "Events FRAG");
        fragmentTransaction.commit();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (currentUser != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + currentUser.getUid());
                    Toast.makeText(UserProfileActivity.this, "Successfully signed in with: " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(UserProfileActivity.this, "Successfully signed out.", Toast.LENGTH_SHORT).show();
                }
                // ...
            }
        };

        publicUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            PublicUser publicUser = ds.child(currentUserID).getValue(PublicUser.class);
            userName.setText("USER ID KEY" + ds.child(PUBLIC_USER).child(currentUserID).getKey());
//            lastNameTextView.setText(publicUser.getLast_name());
//            usernameTextView.setText(publicUser.getUsername());
//            userIdTextView.setText(ds.child(PUBLIC_USER).child(currentUserID).getKey());
//            emailTextView.setText(publicUser.getEmail());
        }

    }

    public void setViews() {
        userImage = findViewById(R.id.circle_imageview);
        userName = findViewById(R.id.user_name);
        editButton = findViewById(R.id.edit_button);

//        userName.setText("Joanne Yun");
        Glide.with(getApplicationContext()).load(R.drawable.joanneyun).into(userImage);
    }

    /*method to load and display navigation drawer - AJ*/
    public void setNavDrawerLayout() {
        navDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        switch (menuItem.getItemId()) {
                            case R.id.notifications_menu_item:

                                //TODO start userProfile with notifications fragment loaded.
                                break;
                            case R.id.upcoming_events_menu_item:

                                //TODO start userProfile with upcomining events fragment loaded.
                                break;
                            case R.id.create_new_event:
                                Intent createEventIntent = new Intent(UserProfileActivity.this, EventActivity.class);
                                startActivity(createEventIntent);
                                //TODO start settings activity.
                                break;
                            case R.id.my_groups_menu_item:

                                //TODO start userProfile with groups fragment loaded.
                                break;
                            case R.id.settings_menu_item:
                                Intent settings = new Intent(UserProfileActivity.this, SettingsActivity.class);
                                startActivity(settings);
                                //TODO start settings activity.
                                break;
                            case R.id.venue_swipe_test:
                                Intent venueSwipeIntent = new Intent(UserProfileActivity.this, VenueVoteSwipeActivity.class);
                                startActivity(venueSwipeIntent);
                                //TODO start settings activity.
                                break;

                            case R.id.signout_menu_item:
                                AuthUI.getInstance()
                                        .signOut(getApplicationContext())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent landingIntent = new Intent(UserProfileActivity.this, MainActivity.class);
                                                startActivity(landingIntent);
                                            }
                                        });
                                //TODO start settings activity.
                                break;

                        }
                        navDrawerLayout.closeDrawers();


                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
        navDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );
    }

    /*method to load toolbar as action bar and display nav drawer icon - AJ*/
    public void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        //TODO set title for testing only, change when done.
        actionbar.setTitle("User Profile");
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
    }

    /*method that closes navigation drawer whenever item is selected - AJ*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        authentication.addAuthStateListener(authStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authStateListener != null) {
            authentication.removeAuthStateListener(authStateListener);
        }
    }
}
