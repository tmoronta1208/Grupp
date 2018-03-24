package com.example.c4q.capstone.userinterface.events;

import android.os.Build;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

/**
 * Created by amirahoxendine on 3/19/18.
 * This is a class to handle data input for CreateEventFragment
 */

public class CreateEventPresenter {
    private Events newEvent;
    private static List<String> invitedGuests = new ArrayList<>();
    private boolean eventNameSet, eventDateSet, eventTimeSet, eventGuestsSet;
    public String dateOfEvent;
    public String timeOfEvent;
    public String dateTime = "";
    private static String TAG = "CREATE_EVENT_PRES: ";

    public String eventTime;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private FirebaseUser user;
    private String userID;
    String firstName;
    String lastName;
    List<String> dummyUsers;
    FBUserDataUtility fbUserDataUtility = new FBUserDataUtility();

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    DatabaseReference ref = database.child(PUBLIC_USER);
    String key;

    public CreateEventPresenter(){
        newEvent = new Events();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        user = mAuth.getCurrentUser();
        userID = user.getUid();
        setEventOrganizer();
    }

    public void sendEventToFB(EventFragmentListener listener){
        getDummyUserKeys();
        Log.d(TAG, "create event: eventSent to firebase" + newEvent.getEvent_name());
        key = myRef.child("events").push().getKey();
        Log.d(TAG, "create event: push key " + key);

        myRef.child("events").child(key).setValue(newEvent);

        Map<String, Object> user_events = new HashMap<>();
        user_events.put(userID, key);
        myRef.child("user_events").updateChildren(user_events);
        Log.d(TAG, "create event: set value: " + newEvent.getEvent_name());
        listener.getEventIdKEy(key);
    }

    public void getDummyUserKeys(){

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dummyUsers = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String dummyKey = ds.getKey();
                    dummyUsers.add(dummyKey);
                }
                newEvent.setInvited_guests(dummyUsers);
                Log.d(TAG, "create event: set value: " + newEvent.getEvent_name());
                Log.d(TAG, "dummy user list" + dummyUsers.size());
                myRef.child("events").child(key).setValue(newEvent);
                Log.d(TAG, "final dummy user list" + dummyUsers.size());
                fbUserDataUtility.addUserFriends(dummyUsers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        myRef.child(PUBLIC_USER).addValueEventListener(userListener);
    }


    public void getUserData(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                PublicUser user = dataSnapshot.getValue(PublicUser.class);
                firstName = user.getFirst_name();
                lastName = user.getLast_name();
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        myRef.addValueEventListener(postListener);
    }

    public void setEventOrganizer(){
        newEvent.setEvent_organizer(userID);
    }

    public void setEventName(String eventName){
        newEvent.setEvent_name(eventName);
        Log.d(TAG, "event name : " + eventName);
        eventNameSet = true;
    }

    public void setEventDate(DatePicker datePicker){
        dateOfEvent = String.valueOf(datePicker.getMonth()) + "/" + String.valueOf(datePicker.getDayOfMonth());
        newEvent.setEvent_date(dateOfEvent);
        setDateAndTime();
        Log.d(TAG, "event date : " + dateOfEvent);
        eventDateSet = true;
    }

    public void setEventTime(TimePicker timePicker){

        if (Build.VERSION.SDK_INT < 23) {
            timeOfEvent = String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute());
        } else {
            timeOfEvent = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
        }
        newEvent.setEvent_time(timeOfEvent);
        setDateAndTime();
        Log.d(TAG, "event time : " + timeOfEvent);
        eventTimeSet = true;
    }

    public void setDateAndTime(){
        dateTime = "";
        StringBuilder sb = new StringBuilder(dateTime);
        if (dateOfEvent != null) {
            sb.append("Date: ").append(dateOfEvent).append(" ");
        }
        if (timeOfEvent != null){
            sb.append("Time: ").append(timeOfEvent);
        }
        dateTime = sb.toString();
    }

    public void setEventGuests(){
        newEvent.setInvited_guests(invitedGuests);
        if(invitedGuests.size() == 0){
            eventGuestsSet = false;
        }
        eventGuestsSet = true;
    }

    public void setEventNote(String note){
        newEvent.setEvent_note(note);
    }

    public boolean validateEvent(){
        boolean validEvent = false;
        if (eventTimeSet &&eventNameSet && eventDateSet){
            Log.d(TAG, "create event: event valid");
            validEvent = true;
        } else {
            Log.d(TAG, "create event: event not valid");
        }
        return validEvent;
    }

    public void inviteGuest(String guestId){
        invitedGuests.add(guestId);
    }

    public void eventDoneFragmentSwap(){
        //TODO swap create event frag with event frag.
    }

}
