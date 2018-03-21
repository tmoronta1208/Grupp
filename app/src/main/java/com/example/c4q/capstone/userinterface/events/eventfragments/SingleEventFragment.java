package com.example.c4q.capstone.userinterface.events.eventfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.model.events.Events;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.example.c4q.capstone.utils.FBUserFriendsListener;

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
    EventPresenter eventPresenter;
    private static Events currentEvent;
    TextView eventName, eventOrganizer, eventDate;
    String organizerFullName;
    UserFriendsFragment userFriendsFragment;

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
        }
        eventPresenter = new EventPresenter();
        eventName = (TextView) rootView.findViewById(R.id.event_title_text_view);
        eventOrganizer = (TextView) rootView.findViewById(R.id.event_organier_text_view);
        eventDate = (TextView) rootView.findViewById(R.id.event_date_text_view);

        getEventData();
        loadUserFriendsFragment();

        return rootView;
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

                    eventDate.setText(currentEvent.getEvent_date());
                } else {
                    Log.d ("Event Fragment", "event is null");
                }
            }

            @Override
            public void getUserFullName(String name) {
                organizerFullName = name;
                eventOrganizer.setText(organizerFullName);
            }

            @Override
            public void getUser(PublicUser publicUser) {

            }
        });

    }
    /**method to load UserFreindsFragment below event data*/
    public void loadUserFriendsFragment(){

        userFriendsFragment = new UserFriendsFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.event_fragment_container, userFriendsFragment).commit();
    }

}
