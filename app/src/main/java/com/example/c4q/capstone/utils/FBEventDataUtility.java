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
    private FirebaseAuth mAuth;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference eventReference;


    public static FirebaseUser currentUser;
    private String currentUserID;


    String eventKey;
    private static String TAG = "FB EVENT UTILITY: ";
    Events events;

    public FBEventDataUtility(){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        eventReference = firebaseDatabase.child(EVENTS);
        userReference = firebaseDatabase.child(PUBLIC_USER);

        currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            currentUserID = currentUser.getUid();
        }
    }

    /**ajoxe:
     * this method get a single events object form the database
     * it takes in a key and a listener (to send the event to)
     * */

    public void getEventFromDB(String key, final EventDataListener listener){
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

                // ...
            }
        };
        eventReference.addValueEventListener(eventListener);
    }
    /**ajoxe:
     * this method gets all event keys from db
     * */
    public void getAllEventKeys(){

        ValueEventListener eventKeyListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> eventIDList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String eventKey = ds.getKey();
                    eventIDList.add(eventKey);
                    Log.d(TAG, "getAllEventKeys: eventKey: " + eventKey);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
        eventReference.addValueEventListener(eventKeyListener);

    }

    public void getAllEvents(final FBEventDataListener listener){

        ValueEventListener eventKeyListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<Events> eventsList = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Events events = ds.getValue(Events.class);
                   eventsList.add(events);
                   if (events != null){
                       Log.d(TAG, "getAllEvents: eventName: " + events.getEvent_name());
                   } else {
                       Log.d(TAG, "getAllEvents: EVENT IS NULL");
                   }
                }
                listener.getAllEvents(eventsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
        eventReference.addValueEventListener(eventKeyListener);

    }
}
