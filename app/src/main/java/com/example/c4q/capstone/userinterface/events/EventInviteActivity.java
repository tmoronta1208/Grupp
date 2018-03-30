package com.example.c4q.capstone.userinterface.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.EventsAdapter;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;

import java.util.List;

public class EventInviteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;
    private List<UserEvent> eventInvites;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_invite);
        recyclerView = findViewById(R.id.rec_view);
        //eventsAdapter = new EventsAdapter(listOfEvents,getApplicationContext());
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(eventsAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
    }
}
