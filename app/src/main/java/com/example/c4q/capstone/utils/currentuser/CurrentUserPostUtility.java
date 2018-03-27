package com.example.c4q.capstone.utils.currentuser;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.c4q.capstone.utils.Constants.EVENTS;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_EVENTS;

/**
 * Created by amirahoxendine on 3/26/18.
 */

public class CurrentUserPostUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference userEventsReference;
    private DatabaseReference preferencesReference;
    private DatabaseReference eventsReference;
    CurrentUser currentUser = CurrentUser.getInstance();
    private static final String TAG = "PostUtility";

    public CurrentUserPostUtility(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        userEventsReference = firebaseDatabase.child(USER_EVENTS);
        eventsReference = firebaseDatabase.child(EVENTS);
        preferencesReference = firebaseDatabase.child(PUBLIC_USER).child(CurrentUser.getInstance().getUserID());
    }

    public String getNewEventKey(){
       String key = eventsReference.push().getKey();
       return key;
    }

    public void addEventToDb(String key, Events newEvent){
        eventsReference.child(key).setValue(newEvent);
    }

    public void addEventToUserEvents(String key){
        List<String> userEventKeys = currentUser.getUserEventIDList();
        if (userEventKeys == null){
            userEventKeys = new ArrayList<>();
        }
        userEventKeys.add(key);
        Set<String> eventKeys = new HashSet<>();
        eventKeys.addAll(userEventKeys);
        userEventKeys = new ArrayList<>();
        userEventKeys.addAll(eventKeys);
        Map<String, Object> user_events = new HashMap<>();
        user_events.put(currentUser.getUserID(), userEventKeys);
        userEventsReference.updateChildren(user_events);
    }
    public void getDummyUserKeys(){

        ValueEventListener userListener = new ValueEventListener() {
            List<String> dummyUsers = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String dummyKey = ds.getKey();
                    dummyUsers.add(dummyKey);
                }

                Log.d(TAG, "dummy user list" + dummyUsers.size());
                //myRef.child("events").child(key).setValue(newEvent);
                Log.d(TAG, "final dummy user list" + dummyUsers.size());
                FBUserDataUtility fbUserDataUtility = new FBUserDataUtility();
                fbUserDataUtility.addUserFriends(dummyUsers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        firebaseDatabase.child(PUBLIC_USER).addValueEventListener(userListener);
    }

    public void updateBarPrefs(List<String> barPrefs){
        Map<String, Object> userPrefs = new HashMap<>();
        userPrefs.put("bar_preferences", barPrefs);
        preferencesReference.updateChildren(userPrefs);
    }

    public void updateResPrefs(List<String> resPrefs){
        Map<String, Object> userPrefs = new HashMap<>();
        userPrefs.put("restaurant_preferences", resPrefs);
        preferencesReference.updateChildren(userPrefs);
    }
}
