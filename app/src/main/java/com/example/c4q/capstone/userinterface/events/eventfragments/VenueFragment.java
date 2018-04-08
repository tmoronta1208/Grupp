package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.userinterface.events.EventInviteViewHolder;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.createevent.VenueVoteUtility;

import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.VenueViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.EVENTS;
import static com.example.c4q.capstone.utils.Constants.EVENT_INVITATIONS;
import static com.example.c4q.capstone.utils.Constants.VENUE_MAP;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueFragment extends Fragment {
    View rootView;
    RecyclerView venueRecyclerView;
    List<Venue> venueList = new ArrayList<>();
    String eventID;
    Events currentEvent;
    private static Bundle args;
    EventPresenter eventPresenter = new EventPresenter();
    LinearLayoutManager linearLayoutManager;
    VenueVoteUtility venueVoteUtility;
    Context context;
    TextView venueName, venueAddress, venueVoteCount;
    ImageView venuePhoto;
    private DatabaseReference rootRef, eventsRef, venue_map;
    private String currentUserID = CurrentUser.userID;

    String voteCount;
    private HashMap<String, Venue> venueIdMap;
    private HashMap<String, Integer> venueVoteCountMap;
    private List<String> orderedVenueIdList;
    private Venue topVenue;
    boolean dataLoaded = false;
    FirebaseRecyclerAdapter<Venue, VenueViewHolder> firebaseRecyclerAdapter;

    public VenueFragment() {
        // Required empty public constructor
    }

    public static VenueFragment newInstance(String eventID) {
        args = new Bundle();
        args.putString("eventID", eventID);
        VenueFragment fragment = new VenueFragment();
        fragment.setArguments(args);
        fragment.eventID = eventID;
        if (eventID !=null){
            fragment.onCreate(args);
            //fragment.loadFireBaseAdapter(eventID);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootRef = FirebaseDatabase.getInstance().getReference();
        eventsRef = rootRef.child(EVENTS);
        args = getArguments();
        if (args != null){
            eventID = args.getString("eventID");
            //getEventData();
            loadFireBaseAdapter(eventID);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_venue, container, false);
        venueRecyclerView = rootView.findViewById(R.id.venue_recycler_view);

        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        venueRecyclerView.setAdapter(firebaseRecyclerAdapter);
        venueRecyclerView.setLayoutManager(linearLayoutManager);


        venueName = (TextView) rootView.findViewById(R.id.venue_name_textview);
        venueAddress = (TextView) rootView.findViewById(R.id.venue_address_textview);
        venueVoteCount = (TextView) rootView.findViewById(R.id.venue_vote_textview);
        venuePhoto = (ImageView) rootView.findViewById(R.id.venue_photo_image_view);

        getEventData();

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        //getEventData();
    }

    public void loadFireBaseAdapter(final String eventID){
        if (eventID != null){
            venue_map = eventsRef.child(eventID).child(VENUE_MAP);
            Log.d("venue frag", "event id" + eventID);
            firebaseRecyclerAdapter =
                    new FirebaseRecyclerAdapter<Venue, VenueViewHolder>(Venue.class, R.layout.venue_item_view, VenueViewHolder.class, venue_map.orderByChild("vote_count")) {

                        @Override
                        protected void populateViewHolder(VenueViewHolder viewHolder, Venue model, int position) {
                            if(position == 0){

                            }
                            if (getActivity() != null){
                                viewHolder.onBind(model, getActivity().getApplicationContext());
                                //getEventData();
                            }
                        }
                    };
        }

    }

    public void getEventData(){
        if (eventID != null){
            eventPresenter.getSingleValueEventFromDB(eventID, new EventDataListener() {
                @Override
                public void getEvent(Events event) {
                    currentEvent = event;
                    if(currentEvent != null){
                        Log.d ("Venue Fragment", "event: name" + event.getEvent_name());

                        if(currentEvent.getVenue_map() != null){
                            if(!dataLoaded){
                                Log.d ("Venue Fragment", "get venue map called");

                                venueVoteUtility = new VenueVoteUtility(currentEvent, context);
                                venueIdMap = venueVoteUtility.venueIdMap;
                                venueVoteCountMap = venueVoteUtility.venueVoteCountMap;
                                orderedVenueIdList = venueVoteUtility.orderedVenueIdList;
                                venueList = new ArrayList<>();
                                venueList = venueVoteUtility.orderedVenueList;
                                setVenueVoteCount();

                                Log.d ("Venue Fragment", "get venue map called: list size " + venueList.size());
                            }

                        } else{

                        }
                    } else {
                        Log.d ("Event Fragment", "event is null");
                    }
                }

            });
        }

    }


    public void setTopVenueView(Venue venue){
        venueName.setText(venue.getVenue_name());
        if (getActivity() != null) {
            venueName.setTextColor(getActivity().getResources().getColor(R.color.colorAccent));
        }
    }

    public void setVenueVoteCount(){
        CurrentUserPost currentUserPost = CurrentUserPost.getInstance();
        for (Venue venue : venueList){
            int vote = venueVoteCountMap.get(venue.getVenue_id());
            venue.setVote_count(vote);
            currentUserPost.postVenueVoteCount(eventID, venue.getVenue_id(), vote);
        }
    }

}
