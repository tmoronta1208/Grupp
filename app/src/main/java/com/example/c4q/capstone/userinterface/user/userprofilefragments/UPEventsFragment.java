package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.EventsAdapter;
import com.example.c4q.capstone.utils.FBEventDataListener;
import com.example.c4q.capstone.utils.FBEventDataUtility;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.example.c4q.capstone.utils.currentuser.RealTimeEventsListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPEventsFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;


    /**ajoxe:
     * data member variables
     */
    FBEventDataUtility eventDataUtility = new FBEventDataUtility();
    List<Events>  listOfEvents = CurrentUser.getInstance().getUserEventsList();

    public UPEventsFragment() {
        // Required empty public constructor
    }

   /* public static UPEventsFragment newInstance(String title) {
        UPEventsFragment fragment = new UPEventsFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events, container, false);

        if (listOfEvents == null){
            Log.d("EVENTS:", "listOfEvents is null " );
            listOfEvents = new ArrayList<>();
        } else {
            Log.d("EVENTS:", "listOfEvents is not null " + listOfEvents.size() );
        }

        getAllEvents();


        recyclerView = view.findViewById(R.id.events_rec);

        eventsAdapter = new EventsAdapter(listOfEvents,getContext()); /** change events adapter to accept a List<Events> */


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(eventsAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
    /** ajoxe: populate listOfEvents*/

        return view;
    }

    /**ajoxe:
     * method to get a list of events objects from the database
     * this method calls notifyDatasetChanged() on the adapter when the list is populated
     */
    public void getAllEvents(){
        /*if(listOfEvents.size() == 0) {
            eventDataUtility.getAllEvents(new FBEventDataListener() {
                @Override
                public void getAllEvents(List<Events> eventsList) {
                    Log.d("EVENTS:", "listOfEvents size: " + listOfEvents.size());
                    listOfEvents = new ArrayList<>();
                    listOfEvents.addAll(eventsList);
                    Log.d("EVENTS:", "listOfEvents size: " + listOfEvents.size());
                    Collections.reverse(listOfEvents);
                    eventsAdapter.notifyDataSetChanged();
                }
            });
        }*/

        CurrentUser.getInstance().getRealTimeEvents(new RealTimeEventsListener() {
            @Override
            public void getRealTimeEvents(List<Events> eventList) {
                listOfEvents.clear();
                listOfEvents.addAll(eventList);
                eventsAdapter.notifyDataSetChanged();
            }
        });
    }

}
