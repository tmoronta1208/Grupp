package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.network.barzz.barzzmodel.Results;
import com.example.c4q.capstone.utils.DummyDataUtility;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;

import java.util.ArrayList;
import java.util.List;

public class VenueVoteSwipeActivity extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private DummyDataUtility dummyData = new DummyDataUtility();
    EventPresenter eventPresenter = new EventPresenter();
    Events currentEvent;
    List<Venue> venueVoteList;
    Intent intent;
    String eventID;
    String eventType = "notNew";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_vote_swipe);
        intent = getIntent();
        eventID = intent.getStringExtra("eventID");
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();
        //List<Results> resultsList = dummyData.buildDummyList(getApplicationContext());
        getEventData();
    }

    public void loadSwipeView(List<Venue> venueVoteList){
        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.card_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.card_swipe_out_msg_view));
        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if(count == 0) {
                    Intent eventIntent = new Intent(VenueVoteSwipeActivity.this, EventActivity.class);
                    eventIntent.putExtra("eventID", eventID);
                    eventIntent.putExtra("eventType", eventType);
                    startActivity(eventIntent);
                }

            }
        });


        for(Venue venue : venueVoteList){
            mSwipeView.addView(new VenueCardView(mContext, venue, mSwipeView));
        }

        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

    }

    public void getEventData(){
        eventPresenter.getEventFromDB(eventID, new EventDataListener() {
            @Override
            public void getEvent(Events event) {
                if(event != null){
                    Log.d ("Event Fragment", "event: name" + event.getEvent_name());
                    currentEvent = event;
                    venueVoteList = new ArrayList<>();
                    if(currentEvent.getVenue_map() !=null){
                        venueVoteList.addAll(currentEvent.getVenue_map().values());
                        if (venueVoteList.size() != 0){
                            loadSwipeView(venueVoteList);
                        }
                    }

                } else {
                    Log.d ("Event Fragment", "event is null");
                }
            }

            @Override
            public void getUserFullName(String name) {
            }

            @Override
            public void getUser(PublicUser publicUser) {

            }
        });

    }
}
