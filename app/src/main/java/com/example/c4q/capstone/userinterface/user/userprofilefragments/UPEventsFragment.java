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
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.EventsAdapter;
import com.example.c4q.capstone.utils.FBEventDataListener;
import com.example.c4q.capstone.utils.FBEventDataUtility;

import java.util.ArrayList;
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
    List<Events> listOfEvents = new ArrayList<>();

    public UPEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = view.findViewById(R.id.events_rec);

        eventsAdapter = new EventsAdapter(); /** change events adapter to accept a List<Events> */

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayout);
    /** ajoxe: populate listOfEvents*/
        getAllEvents();
        return view;
    }

    /**ajoxe:
     * method to get a list of events objects from the database
     * this method calls notifyDatasetChanged() on the adapter when the list is populated
     */
    public void getAllEvents(){
        eventDataUtility.getAllEvents(new FBEventDataListener() {
            @Override
            public void getAllEvents(List<Events> eventsList) {
                listOfEvents.addAll(eventsList);
                Log.d("EVENTS:", "listOfEvents size: " + listOfEvents.size());
                eventsAdapter.notifyDataSetChanged();
            }
        });
    }

}
