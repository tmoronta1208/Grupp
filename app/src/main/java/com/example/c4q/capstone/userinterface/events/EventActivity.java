package com.example.c4q.capstone.userinterface.events;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.example.c4q.capstone.LoginActivity;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPagerAdapter;
import com.example.c4q.capstone.userinterface.events.eventfragments.CreateEventFragment;
import com.example.c4q.capstone.userinterface.events.eventfragments.SingleEventFragment;
import com.example.c4q.capstone.userinterface.user.SettingsActivity;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class EventActivity extends AppCompatActivity {
    private DrawerLayout navDrawerLayout;
    SingleEventFragment singleEventFragment = new SingleEventFragment();
    CreateEventFragment createEventFragment = new CreateEventFragment();
    Intent intent;
    String eventID;
    Bundle bundle;

    ViewPager vpPager;
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        setNavDrawerLayout();
        setToolbar();
        intent = getIntent();
        eventID = intent.getStringExtra("eventID");

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CreateEventPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setPageTransformer(true, new FlipVerticalTransformer());
        TabLayout tabLayout = (TabLayout) findViewById(R.id.create_event_tab_layout);
        tabLayout.setupWithViewPager(vpPager);
        //setEventFragment();
    }

    /*method to setup events fragment - AJ*/
    public void setEventFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.event_fragment_container, singleEventFragment);
        bundle.putString("eventID", eventID);
        fragmentTransaction.commit();
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
                        switch(menuItem.getItemId()){
                            case R.id.notifications_menu_item:
                                Intent notifications = new Intent (EventActivity.this, UserProfileActivity.class);
                                startActivity(notifications);
                                //TODO start userProfile with notifications fragment loaded.
                                break;
                            case R.id.upcoming_events_menu_item:
                                Intent upcomingEvents = new Intent (EventActivity.this,UserProfileActivity.class);
                                startActivity(upcomingEvents);
                                //TODO start userProfile with upcomining events fragment loaded.
                                break;

                            case R.id.create_new_event:
                                Intent createEventIntent = new Intent (EventActivity.this, EventActivity.class);
                                startActivity(createEventIntent);
                                //TODO start settings activity.
                                break;
                            case R.id.my_groups_menu_item:
                                Intent userProfile = new Intent (EventActivity.this, UserProfileActivity.class);
                                startActivity(userProfile);
                                //TODO start userProfile with groups fragment loaded.
                                break;
                            case R.id.settings_menu_item:
                                Intent settings= new Intent (EventActivity.this, SettingsActivity.class);
                                startActivity(settings);
                                //TODO start settings activity.
                                break;
                            case R.id.signout_menu_item:
                                AuthUI.getInstance()
                                        .signOut(getApplicationContext())
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Intent landingIntent = new Intent (EventActivity.this, LoginActivity.class);
                                                startActivity(landingIntent);
                                            }
                                        });
                                //TODO start settings activity.
                                break;
                            case R.id.venue_swipe_test:
                                Intent venueSwipeIntent = new Intent (EventActivity.this, VenueVoteSwipeActivity.class);
                                startActivity(venueSwipeIntent);
                                //TODO start settings activity.
                                break;
                        }
                        // close drawer when item is tapped
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
        actionbar.setTitle("Events");
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
