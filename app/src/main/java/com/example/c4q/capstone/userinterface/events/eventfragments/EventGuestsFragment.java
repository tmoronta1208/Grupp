package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.EventGuestAdapter;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.EventGuestsViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.c4q.capstone.utils.Constants.EVENTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventGuestsFragment extends Fragment {
    View rootView;
    RecyclerView eventGuestecyclerView;

    LinearLayoutManager linearLayoutManager;

    TextView noInvitedFriends;
    DatabaseReference eventGuestsRef;
    EventGuestAdapter eventGuestAdapter;
    String eventId;
    private static Bundle args;


    public EventGuestsFragment() {
        // Required empty public constructor
    }

    public static EventGuestsFragment newInstance(String eventID) {

        EventGuestsFragment fragment = new EventGuestsFragment();
        args = new Bundle();
        args.putString("eventID", eventID);
        fragment.eventId = eventID;
        //userFragBundle.putStringArrayList("invitedFriends", invitedFriends);
        fragment.setArguments(args);
        fragment.onCreate(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (args != null){
            if ( args.getString("eventID") != null){
                eventId = args.getString("eventID");
            }
        }
        setEventGuestAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_event_guests, container, false);
        setEventGuestAdapter();

        eventGuestecyclerView = (RecyclerView) rootView.findViewById(R.id.friends_recycler_view);
        eventGuestecyclerView.setAdapter(eventGuestAdapter);
        eventGuestecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return rootView;
    }

    public void setEventGuestAdapter(){
        eventGuestsRef = FirebaseDatabase.getInstance().getReference().child(EVENTS).child(eventId).child("event_guest_map");
        eventGuestAdapter = new EventGuestAdapter(EventGuest.class,R.layout.event_guest_item_view,
                EventGuestsViewHolder.class, eventGuestsRef);
    }


}
