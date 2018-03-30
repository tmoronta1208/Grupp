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
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.createevent.VenueVoteUtility;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.VenueAdapter;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class VenueFragment extends Fragment {
    View rootView;
    RecyclerView venueRecyclerView;
    VenueAdapter venueAdapter;
    List<Venue> venueList = new ArrayList<>();
    String eventID;
    Events currentEvent;
    Bundle args;
    EventPresenter eventPresenter = new EventPresenter();
    LinearLayoutManager linearLayoutManager;
    VenueVoteUtility venueVoteUtility;
    Context context;
    TextView venueName, venueAddress, venueVoteCount;
    ImageView venuePhoto;

    String voteCount;
    private HashMap<String, Venue> venueIdMap;
    private HashMap<String, Integer> venueVoteCountMap;
    private List<String> orderedVenueIdList;
    private Venue topVenue;
    boolean dataLoaded = false;

    public VenueFragment() {
        // Required empty public constructor
    }

    public static VenueFragment newInstance(String eventID) {
        Bundle args = new Bundle();
        args.putString("eventID", eventID);
        VenueFragment fragment = new VenueFragment();
        fragment.setArguments(args);
        fragment.eventID = eventID;
        if (eventID !=null){
            fragment.getEventData();
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
        rootView = inflater.inflate(R.layout.fragment_venue, container, false);
        venueRecyclerView = rootView.findViewById(R.id.venue_recycler_view);
        venueAdapter = new VenueAdapter(venueList, context);
        linearLayoutManager = new LinearLayoutManager(context);
        venueRecyclerView.setAdapter(venueAdapter);
        venueRecyclerView.setLayoutManager(linearLayoutManager);
        venueRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        venueName = (TextView) rootView.findViewById(R.id.venue_name_textview);
        venueAddress = (TextView) rootView.findViewById(R.id.venue_address_textview);
        venueVoteCount = (TextView) rootView.findViewById(R.id.venue_vote_textview);
        venuePhoto = (ImageView) rootView.findViewById(R.id.venue_photo_image_view);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        getEventData();
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

                                venueVoteUtility = new VenueVoteUtility(currentEvent);
                                venueIdMap = venueVoteUtility.venueIdMap;
                                venueVoteCountMap = venueVoteUtility.venueVoteCountMap;
                                orderedVenueIdList = venueVoteUtility.orderedVenueIdList;
                                venueList = new ArrayList<>();
                                venueList = venueVoteUtility.orderedVenueList;
                                topVenue = venueVoteUtility.topVenue;
                                setTopVenueView(topVenue);
                                setVenueVoteCount();
                                Log.d ("Venue Fragment", "get venue map called: list size " + venueList.size());
                                venueAdapter.notifyDataSetChanged();
                                venueAdapter = new VenueAdapter(venueList, context);
                                venueRecyclerView.setAdapter(venueAdapter);
                                venueAdapter.notifyDataSetChanged();
                                dataLoaded = true;
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
        if(getActivity() != null){
            venueName.setTextColor(getActivity().getResources().getColor(R.color.colorPrimary));
        }

        venueAddress.setText(venue.getVenue_address());
        if (venue.getVenue_vote() != null){
            venueVoteCount.setText(String.valueOf(venue.getVote_count())+ "  **Top Venue!** ");
        } else {
            venueVoteCount.setText("not voted yet");
        }
        if (venue.getVenue_photo_url() != null){
            Picasso.with(context)
                    .load(venue.getVenue_photo_url())
                    .into(venuePhoto);
        }
    }
    public void setVenueVoteCount(){
        for (Venue venue : venueList){
            int vote = venueVoteCountMap.get(venue.getVenue_id());
            venue.setVote_count(vote);
            CurrentUserPost.getInstance().postVenueVoteCount(eventID, venue.getVenue_id(), vote);
        }
    }

    public void reOrderList(){

    }
}
