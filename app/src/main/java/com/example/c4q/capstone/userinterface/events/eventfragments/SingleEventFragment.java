package com.example.c4q.capstone.userinterface.events.eventfragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.VenueVoteSwipeActivity;
import com.example.c4q.capstone.userinterface.user.ContactListFragment;
import com.example.c4q.capstone.utils.FBUserDataUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * AJ - Single event fragment. Holds Event info and a fragment container for friends list or showing info
 * subject to change
 */
public class SingleEventFragment extends Fragment {
    Bundle bundle;
    View rootView;
    String eventID;
    String eventType;
    EventPresenter eventPresenter;
    private static Events currentEvent;
    TextView eventName, eventOrganizer, eventDate, countVenues;
    Button voteButton;
    ImageButton moreButton;
    String organizerFullName;
    UserFriendsFragment userFriendsFragment;
    FrameLayout frameLayout;
    List<String> invitedFriendsList;

    public SingleEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_single_event, container, false);
        bundle = getArguments();
        if (bundle != null) {
           eventID = bundle.getString("eventID", null);
            eventType = bundle.getString("eventType", null);
        }
        eventPresenter = new EventPresenter();

        defineViews();
        getEventData();
        showHideVote();
        setVoteClick();
        showHideMore();

        return rootView;
    }

    public void defineViews(){
        eventName = (TextView) rootView.findViewById(R.id.event_title_text_view);
        eventOrganizer = (TextView) rootView.findViewById(R.id.event_organier_text_view);
        eventDate = (TextView) rootView.findViewById(R.id.event_date_text_view);
        countVenues = (TextView)  rootView.findViewById(R.id.venues_result_number_tv);
        voteButton = (Button) rootView.findViewById(R.id.vote_button);
        moreButton = (ImageButton) rootView.findViewById(R.id.more_button);
        frameLayout = (FrameLayout) rootView.findViewById(R.id.event_fragment_container);
    }

    public void showHideVote(){
        if(eventType.equals("new")){
            voteButton.setVisibility(View.VISIBLE);
            countVenues.setVisibility(View.VISIBLE);
        }
    }

    public void setVoteClick(){
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voteIntent = new Intent(SingleEventFragment.this.getActivity(), VenueVoteSwipeActivity.class);
                voteIntent.putExtra("eventID", eventID);
                startActivity(voteIntent);
            }
        });
    }

    public void showHideMore(){
        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d ("Event Fragment", "more button clicked");
                if (frameLayout.getVisibility() == View.VISIBLE){
                    Log.d ("Event Fragment", "frame is visible");
                    frameLayout.setVisibility(View.GONE);
                    if(voteButton.getVisibility() == View.GONE && eventType.equals("new")){
                        voteButton.setVisibility(View.VISIBLE);
                        countVenues.setVisibility(View.VISIBLE);
                    }
                    eventName.setTextSize(40);
                    eventOrganizer.setTextSize(18);

                } else if(frameLayout.getVisibility() == View.GONE){
                    Log.d ("Event Fragment", "frame is invisible");
                    frameLayout.setVisibility(View.VISIBLE);
                    if(voteButton.getVisibility() == View.VISIBLE){
                        voteButton.setVisibility(View.GONE);
                        countVenues.setVisibility(View.GONE);
                    }
                    eventName.setTextSize(32);
                    eventOrganizer.setTextSize(16);
                }
            }
        });
    }

    /**method to get event data ( based on event id from bundle ) from the database*/

    public void getEventData(){
        eventPresenter.getEventFromDB(eventID, new EventDataListener() {
            @Override
            public void getEvent(Events event) {
                currentEvent = event;
                if(currentEvent != null){
                    Log.d ("Event Fragment", "event: name" + event.getEvent_name());
                    eventName.setText(currentEvent.getEvent_name());

                    eventDate.setText("Date: " + currentEvent.getEvent_date());
                    invitedFriendsList = event.getInvited_guests();
                    loadUserFriendsFragment();
                } else {
                    Log.d ("Event Fragment", "event is null");
                }
            }

            @Override
            public void getUserFullName(String name) {
                organizerFullName = name;
                eventOrganizer.setText("Creator: " + organizerFullName);
            }

            @Override
            public void getUser(PublicUser publicUser) {

            }
        });

    }
    /**method to load UserFreindsFragment below event data*/
    public void loadUserFriendsFragment(){
        ArrayList<String> invitedFriends = new ArrayList<>();
        if(invitedFriendsList != null && invitedFriendsList.size() != 0){
            invitedFriends.addAll(invitedFriendsList);
            Log.d ("Event Fragment", "invited list array size:" + invitedFriendsList.size());
        }
        userFriendsFragment = UserFriendsFragment.newInstance(invitedFriends);
        if (isAdded()) {
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.event_fragment_container, userFriendsFragment).commit();
        }
    }

}
