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

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.VenueAdapter;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;
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

    public VenueFragment() {
        // Required empty public constructor
    }

    public static VenueFragment newInstance(String eventID) {
        Bundle args = new Bundle();
        args.putString("eventID", eventID);
        VenueFragment fragment = new VenueFragment();
        fragment.setArguments(args);
        fragment.eventID = eventID;
        fragment.getEventData();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        eventID = args.getString("eventID");
        getEventData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_venue, container, false);
        venueRecyclerView = rootView.findViewById(R.id.venue_recycler_view);
        venueRecyclerView.setAdapter(venueAdapter);
        venueRecyclerView.setLayoutManager(linearLayoutManager);
        venueRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        venueAdapter = new VenueAdapter(venueList, context);
        linearLayoutManager = new LinearLayoutManager(context);
    }

    public void getEventData(){
        eventPresenter.getEventFromDB(eventID, new EventDataListener() {
            @Override
            public void getEvent(Events event) {
                currentEvent = event;
                if(currentEvent != null){
                    Log.d ("Event Fragment", "event: name" + event.getEvent_name());

                    if(currentEvent.getVenue_map() != null){
                       venueList.addAll(currentEvent.getVenue_map().values());

                    } else{

                    }
                } else {
                    Log.d ("Event Fragment", "event is null");
                }
            }

            @Override
            public void getUserFullName(String name) {

            }
        });

    }
}
