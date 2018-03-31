package com.example.c4q.capstone.utils;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.EVENTS;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FBEventDataUtility {
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference firebaseDatabase, eventReference;


    public static FirebaseUser currentUser;


    String eventKey;
    private static String TAG = "FB EVENT UTILITY: ";
    Events events;

    public FBEventDataUtility() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        eventReference = firebaseDatabase.child(EVENTS);
    }

    /**
     * ajoxe:
     * this method get a single events object form the database
     * it takes in a key and a listener (to send the event to)
     */

    public void getEventFromDB(String key, final EventDataListener listener) {
        eventKey = key;
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d(TAG, "event listener called");
                events = dataSnapshot.child(eventKey).getValue(Events.class);
                if (events != null) {
                    Log.d(TAG, "event name" + events.getEvent_name());
                    listener.getEvent(events);
                } else {
                    Log.d(TAG, "event not found");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d(TAG, "cancelled");
                // ...
            }
        };
        eventReference.addValueEventListener(eventListener);
    }
    public void getSingleValueEventFromDB(String key, final EventDataListener listener) {
        eventKey = key;
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Log.d(TAG, "event listener called");
                events = dataSnapshot.child(eventKey).getValue(Events.class);
                if (events != null) {
                    Log.d(TAG, "event name" + events.getEvent_name());
                    listener.getEvent(events);
                } else {
                    Log.d(TAG, "event not found");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d(TAG, "cancelled");
                // ...
            }
        };
        eventReference.addListenerForSingleValueEvent(eventListener);
    }


}
