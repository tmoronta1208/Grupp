package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.events.eventfragments.EventInfoFragment;
import com.example.c4q.capstone.userinterface.events.eventfragments.EventGuestsFragment;
import com.example.c4q.capstone.userinterface.events.eventfragments.VenueFragment;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {


    Intent intent;

    private String eventID;
    EventPresenter eventPresenter;
    private Events currentEvent;
    TextView eventName, eventDate;
    Button voteButton;
    ArrayList<String> invitedFriendsList;
    Button deletButton;
    FragmentPagerAdapter adapterViewPager;
    ViewPager eventVPager;
    Toolbar toolbar;

    /**
     * ajoxe: Nav Drawer
     */

    SupportActivity activity;
    Context context;

    Bundle eventBundle;
    EventInfoFragment eventInfoFragment;
    VenueFragment venueFragment;
    EventGuestsFragment eventGuestsFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.event_info:

                    return true;
                case R.id.event_guests:

                    return true;
                case R.id.event_venues:

                    return true;
            }
            return false;
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        eventPresenter = new EventPresenter();
        context = this;
        activity = this;
        intent = getIntent();
        eventID = intent.getStringExtra("eventID");
        fragmentManager = getSupportFragmentManager();
        //fragmentTransaction = fragmentManager.beginTransaction();


        /*BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);*/
        defineViews();
        getEventData();

    }

    public void defineViews() {
        eventName = (TextView) findViewById(R.id.event_title_text_view);
        eventDate = (TextView) findViewById(R.id.event_date_text_view);
        toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //CurrentUserPost.getInstance().deleteEvent(currentEvent);

    }



    /**
     * method to get event data ( based on event id from bundle ) from the database
     */

    public void getEventData() {
        eventPresenter.getEventFromDB(eventID, new EventDataListener() {
            @Override
            public void getEvent(Events event) {
                currentEvent = event;
                if (currentEvent != null) {
                    Log.d("Event Fragment", "event: name" + event.getEvent_name());

                    eventName.setText(currentEvent.getEvent_name());
                    eventDate.setText(currentEvent.getEvent_date() + " @ " + currentEvent.getEvent_time());
                    //setFragments(eventID);
                    invitedFriendsList = new ArrayList<>();
                    invitedFriendsList.addAll(event.getInvited_guests());
                    eventVPager = (ViewPager) findViewById(R.id.eventViewPager);
                    TabLayout tabLayout = (TabLayout) findViewById(R.id.view_pager_tab);
                    tabLayout.setupWithViewPager(eventVPager);
                    adapterViewPager = new EventPagerAdapter(getSupportFragmentManager(),eventID, invitedFriendsList );
                    eventVPager.setAdapter(adapterViewPager);

                    if (currentEvent.getVenue_map() != null) {

                        //showHideVote(currentEvent);


                    }
                } else {
                    Log.d("Event Fragment", "event is null");
                }
            }
        });

    }

    public void setFragments(String eventID){
        /*eventGuestsFragment = EventGuestsFragment.newInstance(eventID);
        venueFragment = VenueFragment.newInstance(eventID);
        eventInfoFragment = EventInfoFragment.newInstance(eventID);*/
    }

    /**
     * method to load UserFreindsFragment below event data
     */
    public void loadUserFriendsFragment() {
/*
        ArrayList<String> invitedFriends = new ArrayList<>();
        if (invitedFriendsList != null && invitedFriendsList.size() != 0) {
            invitedFriends.addAll(invitedFriendsList);
            Log.d("Event Fragment", "invited list array size:" + invitedFriendsList.size());
            eventGuestsFragment.getFriendUsers(invitedFriends);
        }*/


    }

    public void loadVenueFragment(String eventID) {
        /*venueFragment = VenueFragment.newInstance(eventID);
        fragmentManager.beginTransaction().replace(R.id.event_fragment_container, venueFragment).commit();*/

    }
    public static class EventPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;
        String eventId;
        ArrayList<String> friendsList;


        public EventPagerAdapter(FragmentManager fragmentManager, String eventId, ArrayList<String> friendsList) {
            super(fragmentManager);
            this.eventId = eventId;
            this.friendsList = friendsList;
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }


        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return EventInfoFragment.newInstance(eventId);
                case 1:
                    return VenueFragment.newInstance(eventId);
                case 2:
                    return EventGuestsFragment.newInstance(eventId);
                default:
                    return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Info";
                case 1:
                    return "Venues";
                case 2:
                    return "Guest List";
                default:
                    return null;

            }
        }

    }
}
