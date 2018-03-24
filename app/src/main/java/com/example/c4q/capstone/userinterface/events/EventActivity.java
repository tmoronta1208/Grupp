package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.eventfragments.SingleEventFragment;
import com.example.c4q.capstone.userinterface.navdrawer.NavDrawerPresenter;

public class EventActivity extends AppCompatActivity {

    SingleEventFragment singleEventFragment = new SingleEventFragment();
    Intent intent;
    String eventID;


    /**
     * ajoxe: Nav Drawer
     */
    NavigationView navigationView;
    private DrawerLayout navDrawerLayout;
    NavDrawerPresenter navDrawerPresenter;
    SupportActivity activity;
    Context context;
    Toolbar toolbar;
    ActionBar actionbar;
    Bundle eventBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        context = this;
        activity = this;
        setNavDrawerLayout();
        setToolbar();
        intent = getIntent();
        eventID = intent.getStringExtra("eventID");
        eventBundle = new Bundle();
        eventBundle.putString("eventID", eventID);

        setEventFragment();
    }

    /*method to setup events fragment - AJ*/
    public void setEventFragment(){
        singleEventFragment = new SingleEventFragment();
        singleEventFragment.setArguments(eventBundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.event_fragment_container, singleEventFragment);
        fragmentTransaction.commit();
    }
/** Navigation Drawer Set up **/
    public void setNavDrawerLayout() {
        navDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navDrawerPresenter = new NavDrawerPresenter(activity, context);
        navDrawerPresenter.setNavDrawerViews(navDrawerLayout, navigationView);
        navDrawerPresenter.setNavigationViewMethods();
        setToolbar();
    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        navDrawerPresenter.setToolbarViews(toolbar, actionbar);
        navDrawerPresenter.setActionbar("Create Event");
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
