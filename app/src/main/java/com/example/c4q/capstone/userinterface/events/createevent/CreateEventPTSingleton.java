package com.example.c4q.capstone.userinterface.events.createevent;

import android.util.Log;

import com.example.c4q.capstone.network.foursquare.foursquaremodel.Venues;
import com.example.c4q.capstone.userinterface.CurrentUser;

import java.util.List;

/**
 * Created by amirahoxendine on 3/24/18.
 */

public class CreateEventPTSingleton {
    private static CreateEventPTSingleton createEventPTSingleton;
    private static final String TAG = "CreateEventPTSingleton";
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventNote;
    private String eventVenueType;
    private String eventID;
    private List<String> invitedGuests;
    private List<Venues> venueVoteList;
    private boolean newEvent;
    private boolean inProgress;



    private CreateEventPTSingleton(){


    }



    public static CreateEventPTSingleton getNewInstance(){
        Log.d(TAG, "getNewInstance: new instance called");
        createEventPTSingleton = new CreateEventPTSingleton();
        createEventPTSingleton.setNewEvent(true);
        createEventPTSingleton.setInProgress(true);
        return createEventPTSingleton;
    }

    public static CreateEventPTSingleton getInstance(){
        Log.d(TAG, "getInstance: new instance called");
        if(createEventPTSingleton != null){
            createEventPTSingleton.setNewEvent(false);
            createEventPTSingleton.setInProgress(true);
        } else {
            getNewInstance();
        }
        return createEventPTSingleton;
    }

    public void destroyInstance(){
        createEventPTSingleton = null;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public List<Venues> getVenueVoteList() {
        return venueVoteList;
    }

    public void setVenueVoteList(List<Venues> venueVoteList) {
        this.venueVoteList = venueVoteList;
    }

    public String getEventVenueType() {
        return eventVenueType;
    }

    public void setEventVenueType(String eventVenueType) {
        this.eventVenueType = eventVenueType;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public static String getTAG() {
        return TAG;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventNote() {
        return eventNote;
    }

    public void setEventNote(String eventNote) {
        this.eventNote = eventNote;
    }

    public List<String> getInvitedGuests() {
        return invitedGuests;
    }

    public void setInvitedGuests(List<String> invitedGuests) {
        this.invitedGuests = invitedGuests;
    }

    public boolean isNewEvent() {
        return newEvent;
    }

    public void setNewEvent(boolean newEvent) {
        this.newEvent = newEvent;
    }

    public boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }
}
