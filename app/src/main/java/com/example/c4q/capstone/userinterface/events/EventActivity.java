package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.SupportActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.alerts.InviteNotifications;
import com.example.c4q.capstone.userinterface.events.eventfragments.EventInfoFragment;
import com.example.c4q.capstone.userinterface.events.eventfragments.EventGuestsFragment;
import com.example.c4q.capstone.userinterface.events.eventfragments.VenueFragment;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;

import java.util.ArrayList;

public class EventActivity extends AppCompatActivity {


    Intent intent;
    private String eventID;
    EventPresenter eventPresenter;
    private Events currentEvent;
    TextView eventName, eventDate;
    ArrayList<String> invitedFriendsList;
    Button deletButton;
    FragmentPagerAdapter adapterViewPager;
    ViewPager eventVPager;
    Toolbar toolbar;
    LinearLayout titleLayout;
    SupportActivity activity;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        eventPresenter = new EventPresenter();
        context = this;
        activity = this;
        intent = getIntent();
        eventID = intent.getStringExtra("eventID");

        defineViews();
        getEventData();

    }

    public void defineViews() {
        eventName = (TextView) findViewById(R.id.event_title_text_view);
        eventDate = (TextView) findViewById(R.id.event_date_text_view);
        toolbar = (Toolbar) findViewById(R.id.event_toolbar);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Implemented by activity
                Intent intent = new Intent(EventActivity.this, UserProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
                    invitedFriendsList = new ArrayList<>();
                    eventVPager = (ViewPager) findViewById(R.id.eventViewPager);
                    TabLayout tabLayout = (TabLayout) findViewById(R.id.view_pager_tab);
                    tabLayout.setupWithViewPager(eventVPager);
                    adapterViewPager = new EventPagerAdapter(getSupportFragmentManager(), eventID, invitedFriendsList);
                    eventVPager.setAdapter(adapterViewPager);
                    if (event.isVote_complete()) {
                        InviteNotifications inviteNotifications = new InviteNotifications(getApplicationContext());
                        inviteNotifications.showNotificationVoteComplete(event.getEvent_id());
                    }

                } else {
                    Log.d("Event Fragment", "event is null");
                }
            }
        });

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
            switch (position) {
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
