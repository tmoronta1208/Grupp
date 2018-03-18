package com.example.c4q.capstone;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.transition.TransitionValues;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.c4q.capstone.network.NetworkCall;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.user.SettingsActivity;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private DrawerLayout navDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Transition explode = new Explode();
        TransitionManager tm = getContentTransitionManager();
        setContentTransitionManager(tm);
       // tm.setTransition(MainActivity.this, SettingsActivity.class, explode);
        getWindow().setEnterTransition(explode);
        getWindow().setExitTransition(explode);


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
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });

        NetworkCall.start("10001");
        setNavDrawerLayout();
        setToolbar();
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
                Intent eventIntent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(eventIntent);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed, check response for error code
                // ...
            }
        }
    }

    /*method to load and display navigation drawer - AJ*/
    public void setNavDrawerLayout(){
        navDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        switch(menuItem.getItemId()){
                            case R.id.notifications_menu_item:
                                Intent notifications = new Intent (MainActivity.this, UserProfileActivity.class);
                                startActivity(notifications);
                                //TODO start userProfile with notifications fragment loaded.
                                break;
                            case R.id.upcoming_events_menu_item:
                                Intent upcomingEvents = new Intent (MainActivity.this, UserProfileActivity.class);
                                startActivity(upcomingEvents);
                                //TODO start userProfile with upcomining events fragment loaded.
                                break;
                            case R.id.my_groups_menu_item:
                                Intent userProfile = new Intent (MainActivity.this, UserProfileActivity.class);
                                startActivity(userProfile);
                                //TODO start userProfile with groups fragment loaded.
                                break;
                            case R.id.settings_menu_item:
                                Intent settings= new Intent (MainActivity.this, SettingsActivity.class);
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
    public void setToolbar(){
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
