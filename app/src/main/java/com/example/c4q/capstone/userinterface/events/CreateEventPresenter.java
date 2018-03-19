package com.example.c4q.capstone.userinterface.events;

import android.os.Build;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.model.events.Events;
import com.example.c4q.capstone.userinterface.events.eventfragments.CreateEventFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 3/19/18.
 */

public class CreateEventPresenter {
    private static Events newEvent;
    private static List<String> invitedGuests = new ArrayList<>();
    private boolean eventNameSet, eventDateSet, eventTimeSet, eventGuestsSet, eventOrganizerSet;
    public String dateOfEvent;
    public String timeOfEvent;
    public String dateTime = "";
    private static String TAG = "CREATE_EVENT_PRES: ";

    public CreateEventPresenter(){
        newEvent = new Events();
    }

    public void sendEventToFB(){
        //TODO logic to send newEvent to firebase

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

    public void setEventOrganizer(String eventOrganizerId){
        newEvent.setEvent_organizer(eventOrganizerId);
        eventOrganizerSet = true;
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
        return eventGuestsSet && eventOrganizerSet && eventTimeSet &&eventNameSet && eventDateSet;
    }

    public void inviteGuest(String guestId){
        invitedGuests.add(guestId);
    }

    public void eventDoneFragmentSwap(){
        //TODO swap create event frag with event frag.
    }

}
