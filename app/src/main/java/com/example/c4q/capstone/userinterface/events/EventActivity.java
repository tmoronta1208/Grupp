package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
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
import com.example.c4q.capstone.userinterface.navdrawer.NavDrawerPresenter;
import com.example.c4q.capstone.userinterface.user.SettingsActivity;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class EventActivity extends AppCompatActivity {

    SingleEventFragment singleEventFragment = new SingleEventFragment();
    CreateEventFragment createEventFragment = new CreateEventFragment();
    Intent intent;
    String eventID;
    Bundle bundle;

    ViewPager vpPager;
    FragmentPagerAdapter adapterViewPager;
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

       vpPager = (ViewPager) findViewById(R.id.vpPager);
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
