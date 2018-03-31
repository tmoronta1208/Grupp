package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.eventfragments.InvitedFriendsFragment;
import com.example.c4q.capstone.userinterface.events.eventfragments.VenueFragment;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {


    Intent intent;

    String eventID;
    String eventType;
    EventPresenter eventPresenter;
    private static Events currentEvent;
    TextView eventName, eventOrganizer, eventDate, countVenues;
    ProgressBar progressBar;
    Button voteButton;
    ImageButton moreButton;
    String organizerFullName;

    FrameLayout frameLayout;
    List<String> invitedFriendsList;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    InvitedFriendsFragment invitedFriendsFragment;
    VenueFragment venueFragment;
    Boolean newInstanceCalled = false;

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
        eventPresenter = new EventPresenter();
        context = this;
        activity = this;
        intent = getIntent();
        eventID = intent.getStringExtra("eventID");
        eventType = intent.getStringExtra("eventType");

        invitedFriendsFragment = new InvitedFriendsFragment();
        //venueFragment = new VenueFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.event_fragment_container, invitedFriendsFragment);
        fragmentTransaction.commit();

        defineViews();
        getEventData();
        showHideVote();
        setVoteClick();
        showHideMore();
    }

    public void defineViews() {
        eventName = (TextView) findViewById(R.id.event_title_text_view);
        eventOrganizer = (TextView) findViewById(R.id.event_organier_text_view);
        eventDate = (TextView) findViewById(R.id.event_date_text_view);
        countVenues = (TextView) findViewById(R.id.venues_result_number_tv);
        voteButton = (Button) findViewById(R.id.vote_button);
        voteButton.setVisibility(View.GONE);
        moreButton = (ImageButton) findViewById(R.id.more_button);
        progressBar = (ProgressBar) findViewById(R.id.get_vote_progress);
        frameLayout = (FrameLayout) findViewById(R.id.event_fragment_container);
    }

    public void showHideVote() {
        if (currentEvent == null) {
            voteButton.setVisibility(View.GONE);
            countVenues.setVisibility(View.GONE);
        }
    }

    public void showHideVote(Events event) {
        if(event != null){
            boolean voted = currentEvent.getEvent_guest_map().get(CurrentUser.userID).isVoted();
            if (currentEvent.getEvent_guest_map().get(CurrentUser.userID).isVoted()) {
                voteButton.setVisibility(View.GONE);
                countVenues.setVisibility(View.GONE);
                Log.d("show hide vote", "user voted" + voted);
            } else {
                voteButton.setVisibility(View.VISIBLE);
                countVenues.setVisibility(View.VISIBLE);
                if (currentEvent.getVenue_map() != null){
                    countVenues.setText("You have " + currentEvent.getVenue_map().size() + " venues to vote on!");
                }
                Log.d("show hide vote", "user did not vote" + voted);
            }
        }

    }

    public void setVoteClick() {
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voteIntent = new Intent(EventActivity.this, VenueVoteSwipeActivity.class);
                voteIntent.putExtra("eventID", eventID);
                startActivity(voteIntent);
                finish();
            }
        });
    }

    public void showHideMore() {
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Event Fragment", "more button clicked");
                if (frameLayout.getVisibility() == View.VISIBLE) {
                    Log.d("Event Fragment", "frame is visible");
                    frameLayout.setVisibility(View.GONE);
                    showHideVote(currentEvent);
                    eventName.setTextSize(50);
                    eventOrganizer.setVisibility(View.VISIBLE);
                    eventOrganizer.setTextSize(18);

                } else if (frameLayout.getVisibility() == View.GONE) {
                    Log.d("Event Fragment", "frame is invisible");
                    frameLayout.setVisibility(View.VISIBLE);
                    if (voteButton.getVisibility() == View.VISIBLE) {
                        voteButton.setVisibility(View.GONE);
                        countVenues.setVisibility(View.GONE);
                    }
                    eventName.setTextSize(28);
                    eventOrganizer.setVisibility(View.GONE);
                }
            }
        });
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

                    eventDate.setText(currentEvent.getEvent_date());
                    invitedFriendsList = event.getInvited_guests();
                    organizerFullName = currentEvent.getEvent_guest_map().get(currentEvent.getEvent_organizer()).getUser_firstname();
                    eventOrganizer.setText("Created By " + organizerFullName);
                    //loadUserFriendsFragment();


                    if (currentEvent.getVenue_map() != null) {
                        progressBar.setVisibility(View.GONE);
                        boolean voted = currentEvent.getEvent_guest_map().get(CurrentUser.userID).isVoted();
                        Log.d("get event data", "user voted" + voted);
                        showHideVote(currentEvent);

                        if (!newInstanceCalled) {
                            loadVenueFragment(eventID);
                            newInstanceCalled = true;
                        }
                    } else {
                        progressBar.setVisibility(View.GONE);
                        countVenues.setText("Sorry, no matching venues");
                    }
                } else {
                    Log.d("Event Fragment", "event is null");
                }
            }
        });

    }

    /**
     * method to load UserFreindsFragment below event data
     */
    public void loadUserFriendsFragment() {

        ArrayList<String> invitedFriends = new ArrayList<>();
        if (invitedFriendsList != null && invitedFriendsList.size() != 0) {
            invitedFriends.addAll(invitedFriendsList);
            Log.d("Event Fragment", "invited list array size:" + invitedFriendsList.size());
            invitedFriendsFragment.getFriendUsers(invitedFriends);
        }


    }

    public void loadVenueFragment(String eventID) {
        venueFragment = VenueFragment.newInstance(eventID);
        fragmentManager.beginTransaction().replace(R.id.event_fragment_container, venueFragment).commit();

    }
}
