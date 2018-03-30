package com.example.c4q.capstone.userinterface.events;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.utils.FBEventDataUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.EVENTS;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public class EventPresenter {
    FBEventDataUtility eventDataUtility;
    String eventKey;


    public EventPresenter() {
        eventDataUtility = new FBEventDataUtility();
    }

    public void getEventFromDB(String key, final EventDataListener listener) {
        eventKey = key;
        eventDataUtility.getEventFromDB(eventKey, listener);
    }

    public void getSingleValueEventFromDB(String key, final EventDataListener listener) {
        eventKey = key;
        eventDataUtility.getSingleValueEventFromDB(eventKey, listener);
    }

}
