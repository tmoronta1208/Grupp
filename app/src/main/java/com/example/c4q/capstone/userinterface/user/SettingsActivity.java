package com.example.c4q.capstone.userinterface.user;

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
import android.view.MenuItem;
import android.view.View;

import com.example.c4q.capstone.MainActivity;
import com.example.c4q.capstone.R;

public class SettingsActivity extends AppCompatActivity {

    private DrawerLayout navDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setNavDrawerLayout();
        setToolbar();
        Transition explode = new Explode();
        getWindow().setEnterTransition(explode);
        getWindow().setExitTransition(explode);
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
                                Intent notifications = new Intent (SettingsActivity.this, UserProfileActivity.class);
                                startActivity(notifications);
                                //TODO start userProfile with notifications fragment loaded.
                                break;
                            case R.id.upcoming_events_menu_item:
                                Intent upcomingEvents = new Intent (SettingsActivity.this, UserProfileActivity.class);
                                startActivity(upcomingEvents);
                                //TODO start userProfile with upcomining events fragment loaded.
                                break;
                            case R.id.my_groups_menu_item:
                                Intent userProfile = new Intent (SettingsActivity.this, UserProfileActivity.class);
                                startActivity(userProfile);
                                //TODO start userProfile with groups fragment loaded.
                                break;
                            case R.id.settings_menu_item:
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
        actionbar.setTitle("Settings");
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
