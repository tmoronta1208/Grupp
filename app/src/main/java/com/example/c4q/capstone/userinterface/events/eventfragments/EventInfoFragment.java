package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.VenueVoteSwipeActivity;
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
    private static String eventID;
    Events currentEvent;
    private static Bundle args;
    EventPresenter eventPresenter = new EventPresenter();
    String eventName, eventDateAndTime, eventOrganizer;

    View rootView;
    TextView eventOrganizerTV, topVenueName, topVenueAddress, voteCountTV;
    ImageView topVenueImage;
    CircleImageView organizerIcon;
    RatingBar voteCount;
    Button voteButton;
    View itemView;


    public EventInfoFragment() {
        // Required empty public constructor
    }

    public static EventInfoFragment newInstance(String eventID) {
        args = new Bundle();
        args.putString("eventID", eventID);
        EventInfoFragment fragment = new EventInfoFragment();
        fragment.setArguments(args);
        if (eventID != null) {
            fragment.onCreate(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        args = getArguments();
        if (args != null) {
            eventID = args.getString("eventID");

        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_event_info, container, false);
        loadViews();
        getEventData();
        return rootView;
    }

    public void loadViews() {
        eventOrganizerTV = (TextView) rootView.findViewById(R.id.user_full_name_text_view);
        topVenueName = (TextView) rootView.findViewById(R.id.venue_name_textview);
        topVenueAddress = (TextView) rootView.findViewById(R.id.venue_address_textview);
        voteCountTV = (TextView) rootView.findViewById(R.id.venue_vote_textview);
        voteButton = (Button) rootView.findViewById(R.id.vote_button);
        topVenueImage = (ImageView) rootView.findViewById(R.id.venue_photo_image_view);
        organizerIcon = (CircleImageView) rootView.findViewById(R.id.user_icon);
        itemView = (View) rootView.findViewById(R.id.top_venue_item_view);
    }

    public void setVoteClick() {
        voteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voteIntent = new Intent(EventInfoFragment.this.getContext(), VenueVoteSwipeActivity.class);
                voteIntent.putExtra("eventID", eventID);
                startActivity(voteIntent);
                if (getActivity() != null) {
                    getActivity().finish();
                }


            }
        });
    }

    public void showHideVote(Events event) {
        if (event != null) {
            boolean voted = currentEvent.getEvent_guest_map().get(CurrentUser.userID).isVoted();
            if (voted) {
                voteButton.setVisibility(View.GONE);

                Log.d("show hide vote", "user voted" + voted);
            } else {
                voteButton.setVisibility(View.VISIBLE);
                if (currentEvent.getVenue_map() != null) {
                }
                Log.d("show hide vote", "user did not vote" + voted);
            }
        }

    }

    public void getEventData() {
        if (eventID != null) {
            eventPresenter.getEventFromDB(eventID, new EventDataListener() {
                @Override
                public void getEvent(Events event) {
                    currentEvent = event;
                    if (currentEvent != null) {
                        Log.d("Event Info Fragment", "event: name" + event.getEvent_name());

                        setVoteClick();
                        showHideVote(currentEvent);
                        eventName = currentEvent.getEvent_name();
                        EventGuest eventGuest = currentEvent.getEvent_guest_map().get(currentEvent.getEvent_organizer());
                        eventOrganizer = eventGuest.getUser_firstname() + " " + eventGuest.getUser_lastname();
                        eventOrganizerTV.setText(eventOrganizer);
                        if (eventGuest.getUser_icon() != null) {
                            if (getContext() != null) {
                                Glide.with(getContext()).load(eventGuest.getUser_icon().getIcon_url()).into(organizerIcon);
                            }

                        }
                        if (currentEvent.getVenue_map() != null) {
                            if (currentEvent.getVenue_map().get(currentEvent.getTop_venue()) != null) {
                                Venue topVenue = currentEvent.getVenue_map().get(currentEvent.getTop_venue());
                                if (topVenue != null) {

                                    if (topVenue.getVenue_vote() != null) {
                                        itemView.setVisibility(View.VISIBLE);
                                        topVenueName.setText(topVenue.getVenue_name());
                                        topVenueAddress.setText(topVenue.getVenue_address());
                                        if (topVenue.getVenue_photo_url() != null) {
                                            Picasso.with(getContext())
                                                    .load(topVenue.getVenue_photo_url())
                                                    .into(topVenueImage);
                                        }
                                        String votes = "votes";
                                        if (topVenue.getVote_count() == 1) {
                                            votes = "vote";
                                        }
                                        String numVotes = String.valueOf(topVenue.getVote_count()) + " " + votes;

                                        voteCountTV.setText(numVotes);
                                    } else {
                                        voteCountTV.setText("no votes yet");
                                    }
                                }
                            }

                        }

                    } else {
                        Log.d("Event Info Fragment", "event is null");
                    }
                }

            });
        }

    }

}
