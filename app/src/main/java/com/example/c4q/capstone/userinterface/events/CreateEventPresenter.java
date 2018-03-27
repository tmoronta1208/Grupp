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
    private boolean eventNameSet, eventDateSet, eventTimeSet, eventGuestsSet, eventNoteSet;
    private boolean nameDone, friendsDone;
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
       key = setFinalizedEvent();
       currentUserPost.postNewEvent(key, newEvent);

        Log.d(TAG, "event key : " + key);
        listener.getEventIdKEy(key);
    }

    public void setEventName(String eventName){
        createEventPTSingleton.setEventName(eventName);
        eventNameSet = true;
        Log.d(TAG, "event name : " + eventName);
        eventNameSet = true;
        validateEvent();
    }

    public void setEventDate(DatePicker datePicker){
        dateOfEvent = String.valueOf(datePicker.getMonth()) + "/" + String.valueOf(datePicker.getDayOfMonth());
        createEventPTSingleton.setEventDate(dateOfEvent);
        setDateAndTime();
        eventDateSet = true;
        Log.d(TAG, "event date : " + dateOfEvent);
        validateEvent();

    }

    public void setEventTime(TimePicker timePicker){

        if (Build.VERSION.SDK_INT < 23) {
            timeOfEvent = String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute());
        } else {
            timeOfEvent = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
        }
        createEventPTSingleton.setEventTime(timeOfEvent);
        setDateAndTime();
        eventTimeSet = true;
        validateEvent();
        Log.d(TAG, "event time : " + timeOfEvent);

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
        eventGuestsSet = true;
        friendsDone = true;
        Log.d(TAG, "invite size" + invitedGuests.size());
        validateEvent();
    }
    public boolean validateFriendsDone(){
        return friendsDone;
    }
    public boolean validateNameDone(){
        return eventTimeSet && eventDateSet && eventNameSet;
    }

    public void setEventNote(String note){
        createEventPTSingleton.setEventNote(note);
        Log.d(TAG, "event type" + note);
        eventNoteSet = true;
        validateEvent();
    }

    public boolean validateEvent(){
        boolean validEvent = false;
        if (eventTimeSet && eventNameSet && eventDateSet && eventGuestsSet &&eventNoteSet){
            Log.d(TAG, "create event: event valid");
            validEvent = true;

        } else {
            Log.d(TAG, "create event: event not valid");
        }
        return validEvent;
    }

    public String setFinalizedEvent(){
        key = currentUserPost.newEventKey();
        newEvent.setEvent_id(createEventPTSingleton.getEventID());
        newEvent.setEvent_name(createEventPTSingleton.getEventName());
        newEvent.setEvent_note(createEventPTSingleton.getEventNote());
        newEvent.setEvent_time(createEventPTSingleton.getEventTime());
        newEvent.setEvent_date(createEventPTSingleton.getEventDate());
        newEvent.setVenue_type(createEventPTSingleton.getEventVenueType());
        newEvent.setEvent_note(createEventPTSingleton.getEventNote());
        newEvent.setInvited_guests(createEventPTSingleton.getInvitedGuests());
        newEvent.setEvent_organizer(currentUser.getUserID());
        newEvent.setEvent_id(key);
        Log.d(TAG, "event type" + createEventPTSingleton.getEventVenueType());
        return key;
    }
}
