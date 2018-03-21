package com.example.c4q.capstone.userinterface.events;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public class EventPresenter {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference eventReference;
    private DatabaseReference userReference;

    private FirebaseUser user;
    private static final String PUBLIC_USER = "public_user";
    private String userID;
    String userFirstName;
    String userLastName;
    String eventKey;
    private static String TAG = "EVENT_PRES: ";
    Events events;

    public EventPresenter(){
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
                Log.d(" Event Presenter", "event listener called");
                events = dataSnapshot.child(eventKey).getValue(Events.class);
                if (events != null) {
                    Log.d(" EVENT PRESENTER", "event name" + events.getEvent_name());
                    listener.getEvent(events);
                    getUserData(events.getEvent_organizer(), listener);

                } else {
                    Log.d(" EVENT PRESENTER", "event not found");
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

    public void getUserData(final String organizer, final EventDataListener listener){
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                PublicUser user = dataSnapshot.child(organizer).getValue(PublicUser.class);
               userFirstName = user.getFirst_name();
                userLastName = user.getLast_name();
                String userFullName = userFirstName + " " + userLastName;
                Log.w(TAG, "user full name" + userFullName);
                listener.getUserFullName(userFullName);
                //listener.getUser(user);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
       userReference.addValueEventListener(userListener);
    }


}
