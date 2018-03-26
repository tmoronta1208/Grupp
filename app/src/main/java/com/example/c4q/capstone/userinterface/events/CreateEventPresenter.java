package com.example.c4q.capstone.userinterface.events;

import android.os.Build;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPTSingleton;
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
 * This is a class to handle data input for CreateEventAddNameFragment
 */

public class CreateEventPresenter {
    private Events newEvent;
    CurrentUser currentUser = CurrentUser.getInstance();
    CurrentUserPost currentUserPost = CurrentUserPost.getInstance();
    private static List<String> invitedGuests = new ArrayList<>();
    private boolean eventNameSet, eventDateSet, eventTimeSet, eventGuestsSet;
    public String dateOfEvent;
    public String timeOfEvent;
    public String dateTime = "";
    private static String TAG = "CREATE_EVENT_PRES: ";
    List<String> dummyUsers;
    CreateEventPTSingleton createEventPTSingleton;
    String key;

    public CreateEventPresenter(CreateEventPTSingleton eventPTSingleton){
        createEventPTSingleton = eventPTSingleton;
        newEvent = new Events();
    }

    public void sendEventToFB(EventFragmentListener listener){
        setFinalizedEvent();
        key = currentUserPost.postNewEvent(newEvent);
        Log.d(TAG, "event key : " + key);
        listener.getEventIdKEy(key);
    }

    public void getDummyUserKeys(){

        /*ValueEventListener userListener = new ValueEventListener() {
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
                //myRef.child("events").child(key).setValue(newEvent);
                Log.d(TAG, "final dummy user list" + dummyUsers.size());
                //fbUserDataUtility.addUserFriends(dummyUsers);//<-- add dummy friends
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        //myRef.child(PUBLIC_USER).addValueEventListener(userListener);*/
    }

    public void setEventName(String eventName){
        createEventPTSingleton.setEventName(eventName);
        Log.d(TAG, "event name : " + eventName);
        eventNameSet = true;
    }

    public void setEventDate(DatePicker datePicker){
        dateOfEvent = String.valueOf(datePicker.getMonth()) + "/" + String.valueOf(datePicker.getDayOfMonth());
        createEventPTSingleton.setEventDate(dateOfEvent);
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
        createEventPTSingleton.setEventTime(timeOfEvent);
        setDateAndTime();
        Log.d(TAG, "event time : " + timeOfEvent);
        eventTimeSet = true;
    }

    public void setDateAndTime(){
        dateTime = "";
        StringBuilder sb = new StringBuilder(dateTime);
        if (createEventPTSingleton.getEventDate() != null) {
            sb.append("Date: ").append(createEventPTSingleton.getEventDate()).append(" ");
        }
        if (createEventPTSingleton.getEventTime() != null){
            sb.append("Time: ").append(createEventPTSingleton.getEventTime());
        }
        dateTime = sb.toString();
    }

    public void setEventGuests(List<String> invitedGuests){
        createEventPTSingleton.setInvitedGuests(invitedGuests);
       /* newEvent.setInvited_guests(invitedGuests);
        if(invitedGuests.size() == 0){
            eventGuestsSet = false;
        }*/
        eventGuestsSet = true;
    }

    public void setEventNote(String note){
        createEventPTSingleton.setEventNote(note);
    }

    public boolean validateEvent(){
        boolean validEvent = false;
        if (eventTimeSet && eventNameSet && eventDateSet){
            Log.d(TAG, "create event: event valid");
            validEvent = true;
            setFinalizedEvent();
        } else {
            Log.d(TAG, "create event: event not valid");
        }
        return validEvent;
    }

    public void setFinalizedEvent(){
        newEvent.setEvent_id(createEventPTSingleton.getEventID());
        newEvent.setEvent_name(createEventPTSingleton.getEventName());
        newEvent.setEvent_note(createEventPTSingleton.getEventNote());
        newEvent.setEvent_time(createEventPTSingleton.getEventTime());
        newEvent.setEvent_date(createEventPTSingleton.getEventDate());

        newEvent.setEvent_organizer(currentUser.getUserID());
        Log.d(TAG, "create event: userId" + currentUser.getUserID());
    }
}
