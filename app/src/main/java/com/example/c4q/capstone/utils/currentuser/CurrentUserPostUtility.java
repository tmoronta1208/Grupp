package com.example.c4q.capstone.utils.currentuser;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.c4q.capstone.utils.Constants.EVENTS;

/**
 * Created by amirahoxendine on 3/26/18.
 */

public class CurrentUserPostUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference userEventsReference;
    private DatabaseReference eventsReference;
    CurrentUser currentUser = CurrentUser.getInstance();

    public CurrentUserPostUtility(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        userEventsReference = firebaseDatabase.child("user_events");
        eventsReference = firebaseDatabase.child(EVENTS);
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
        Map<String, Object> user_events = new HashMap<>();
        user_events.put(currentUser.getUserID(), userEventKeys);
        userEventsReference.updateChildren(user_events);
    }


}
