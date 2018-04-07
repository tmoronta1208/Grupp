package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.createevent.VenueVoteUtility;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.c4q.capstone.utils.Constants.EVENTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventInfoFragment extends Fragment {
    String eventID;
    Events currentEvent;
    Bundle args;
    EventPresenter eventPresenter = new EventPresenter();
    String eventName, eventDateAndTime, eventOrganizer;

    View rootView;
    TextView eventDateTimeTV, eventOrganizerTV, topVenueName, topVenueAddress;
    ImageView topVenueImage;
    CircleImageView organizerIcon;
    RatingBar voteCount;



    public EventInfoFragment() {
        // Required empty public constructor
    }

    public static EventInfoFragment newInstance(String eventID){
        Bundle args = new Bundle();
        args.putString("eventID", eventID);
        EventInfoFragment fragment = new EventInfoFragment();
        fragment.setArguments(args);
        fragment.eventID = eventID;
        if (eventID !=null){
            fragment.onCreate(args);
        }
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        args = getArguments();
        if (args != null){
            eventID = args.getString("eventID");
            getEventData();
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_event_info, container, false);

        return rootView;
    }

    public void getEventData(){
        if (eventID != null){
            eventPresenter.getSingleValueEventFromDB(eventID, new EventDataListener() {
                @Override
                public void getEvent(Events event) {
                    currentEvent = event;
                    if(currentEvent != null){
                        Log.d ("Event Info Fragment", "event: name" + event.getEvent_name());
                        eventDateTimeTV = (TextView) rootView.findViewById(R.id.event_date_text_view);
                        eventOrganizerTV = (TextView) rootView.findViewById(R.id.user_full_name_text_view);
                        topVenueName = (TextView) rootView.findViewById(R.id.venue_name_textview);
                        topVenueAddress = (TextView) rootView.findViewById(R.id.venue_address_textview);
                        topVenueImage = (ImageView) rootView.findViewById(R.id.venue_photo_image_view);
                        organizerIcon = (CircleImageView)  rootView.findViewById(R.id.user_icon);

                            eventName = currentEvent.getEvent_name();
                            eventDateAndTime = currentEvent.getEvent_date() + " @ " + currentEvent.getEvent_time();
                            EventGuest eventGuest = currentEvent.getEvent_guest_map().get(currentEvent.getEvent_organizer());
                            eventOrganizer = eventGuest.getUser_firstname() + " " +eventGuest.getUser_lastname();
                            eventDateTimeTV.setText(currentEvent.getEvent_date());
                            eventOrganizerTV.setText(eventOrganizer);
                        Venue topVenue = currentEvent.getVenue_map().get(currentEvent.getTop_venue());
                        if (topVenue != null){
                            topVenueName.setText(topVenue.getVenue_name());
                            topVenueAddress.setText(topVenue.getVenue_address());
                            if (topVenue.getVenue_photo_url() != null){
                                Picasso.with(getContext())
                                        .load(topVenue.getVenue_photo_url())
                                        .into(topVenueImage);
                            }
                        }


                    } else {
                        Log.d ("Event Info Fragment", "event is null");
                    }
                }

            });
        }

    }

}
