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
    String eventType;


    /**
     * ajoxe: Nav Drawer
     */

    SupportActivity activity;
    Context context;

    Bundle eventBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        context = this;
        activity = this;
        intent = getIntent();
        eventID = intent.getStringExtra("eventID");
        eventType = intent.getStringExtra("eventType");
        eventBundle = new Bundle();
        eventBundle.putString("eventID", eventID);
        eventBundle.putString("eventType", eventType);

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
}
