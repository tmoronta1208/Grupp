package com.example.c4q.capstone;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.model.publicuserdata.UserSearch;
import com.example.c4q.capstone.network.NetworkCall;
import com.example.c4q.capstone.userinterface.user.EditProfileActivity;
import com.example.c4q.capstone.userinterface.user.SettingsActivity;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.example.c4q.capstone.utils.Constants;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private DrawerLayout navDrawerLayout;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth authentication;
    private FirebaseUser currentUser;
    private DatabaseReference publicUserDatabaseReference, searchUserReference;
    private String currentUserID, currentUserEmail;
    private PublicUser publicUser;
    private UserSearch userSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Transition explode = new Explode();
        TransitionManager tm = getContentTransitionManager();
        setContentTransitionManager(tm);
        // tm.setTransition(LoginActivity.this, SettingsActivity.class, explode);
        getWindow().setEnterTransition(explode);
        getWindow().setExitTransition(explode);

        authentication = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        publicUserDatabaseReference = firebaseDatabase.getReference().child(Constants.PUBLIC_USER);
        currentUser = authentication.getCurrentUser();
        if (currentUser != null) {
            currentUserID = currentUser.getUid();
        }

        /**
         * gets user data to check if user is in database
         * */

        getUserData();


        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build());

        /* displays log in UI and grabs the users credentials (MG) */
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        //   .setLogo(R.drawable.my_great_logo) <-- Set logo drawable
                        //  .setTheme(R.style.MySuperAppTheme) <-- Set theme
                        .build(),
                RC_SIGN_IN);
        /*Deletes Firebase Authentication as well as all social identity providers (MG)*/
//        AuthUI.getInstance()
//                .signOut(this)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    public void onComplete(@NonNull Task<Void> task) {
//                        // ...
//                    }
//                });

        NetworkCall.start("10001");
        setNavDrawerLayout();
//        setToolbar();
    }

    /* takes user's credentials and controls what to do with it.
     i.e database stuff (MG)*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userID = user.getUid();


                /**
                 * if user is not in db, launches edit profile intent
                 * */

                if (userNotInDB()) {
                    Intent editProfileIntent = new Intent(LoginActivity.this, EditProfileActivity.class);
                    startActivity(editProfileIntent);
                    Log.d(" LOGIN", "set up profile");

                } else {
                    Log.d(" LOGIN", "go to profile");
                    Intent userProfileIntent = new Intent(LoginActivity.this, UserProfileActivity.class);
                    startActivity(userProfileIntent);
                }
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    private boolean userNotInDB() {
        return publicUser == null;
    }

    /**
     * gets user data to check if user is in database
     */

    public void getUserData() {
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d(" LOGIN", "USER LISTENER CALLED");
                publicUser = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
                if (publicUser != null) {
                    Log.d(" LOGIN", "user first name" + publicUser.getFirst_name());
                } else {
                    Log.d(" LOGIN", "user is null");

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
        publicUserDatabaseReference.addValueEventListener(userListener);
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
                                Intent notifications = new Intent(LoginActivity.this, UserProfileActivity.class);
                                startActivity(notifications);
                                //TODO start userProfile with notifications fragment loaded.
                                break;
                            case R.id.upcoming_events_menu_item:
                                Intent upcomingEvents = new Intent(LoginActivity.this, UserProfileActivity.class);
                                startActivity(upcomingEvents);
                                //TODO start userProfile with upcomining events fragment loaded.
                                break;
                            case R.id.my_groups_menu_item:
                                Intent userProfile = new Intent(LoginActivity.this, UserProfileActivity.class);
                                startActivity(userProfile);
                                //TODO start userProfile with groups fragment loaded.
                                break;
                            case R.id.settings_menu_item:
                                Intent settings = new Intent(LoginActivity.this, SettingsActivity.class);
                                startActivity(settings);
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
        actionbar.setTitle("LoginScreen");
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
}
