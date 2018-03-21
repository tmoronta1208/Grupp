package com.example.c4q.capstone.utils;

import android.util.Log;

import com.example.c4q.capstone.database.model.events.Events;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FBEventDataUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference eventReference;
    private DatabaseReference userReference;

    private FirebaseUser user;

    private String userID;
    String userFirstName;
    String userLastName;
    String eventKey;
    private static String TAG = "FB EVENT UTILITY: ";
    Events events;

    public FBEventDataUtility(){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        eventReference = firebaseDatabase.child("events");
        userReference = firebaseDatabase.child("public_user");

        user = mAuth.getCurrentUser();
        userID = user.getUid();
    }

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
}
