package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.EventsAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.EventsViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.c4q.capstone.utils.Constants.USER_EVENTS_LIST;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPEventsFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private DatabaseReference rootRef, eventsRef;


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

        String currentUserID = CurrentUser.getInstance().getUserID();
        rootRef = FirebaseDatabase.getInstance().getReference();
        eventsRef = rootRef.child(USER_EVENTS_LIST).child(currentUserID);

        recyclerView = view.findViewById(R.id.events_rec);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        linearLayout.setStackFromEnd(true);
        linearLayout.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayout);

        eventsAdapter = new EventsAdapter(Events.class, R.layout.events_item_view, EventsViewHolder.class, eventsRef);

        recyclerView.setAdapter(eventsAdapter);
        return view;
    }
}
