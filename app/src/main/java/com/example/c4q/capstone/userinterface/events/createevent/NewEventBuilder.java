package com.example.c4q.capstone.userinterface.events.createevent;

import android.util.Log;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.Venues;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amirahoxendine on 3/24/18.
 */

public class NewEventBuilder {
    private static NewEventBuilder newEventBuilder;
    private static final String TAG = "NewEventBuilder";
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventNote;
    private String eventVenueType;
    private String eventID;
    private List<String> invitedGuests;
    private List<Venues> venueVoteList;
    private boolean vote_complete;
    private boolean newEvent;
    private boolean inProgress;
    private List<PublicUser> invitedFriendsUserList;
    private HashMap<String, EventGuest> eventGuestMap;

    private NewEventBuilder(){


    }

    public static NewEventBuilder getNewInstance(){
        Log.d(TAG, "getNewInstance: new instance called");
        newEventBuilder = new NewEventBuilder();
        newEventBuilder.setNewEvent(true);
        newEventBuilder.setInProgress(true);
        return newEventBuilder;
    }
    public static NewEventBuilder getInstance(){
        Log.d(TAG, "getInstance: get instance called");
        if(newEventBuilder != null){
            newEventBuilder.setNewEvent(false);
            newEventBuilder.setInProgress(true);
        } else {
            getNewInstance();
        }
        return newEventBuilder;
    }

    public boolean isVote_complete() {
        return vote_complete;
    }

    public void setVote_complete(boolean vote_complete) {
        this.vote_complete = vote_complete;
    }

    public Map<String, EventGuest> getEventGuestMap() {
        return eventGuestMap;
    }

    public void setEventGuestMap(HashMap<String, EventGuest> eventGuestMap) {
        this.eventGuestMap = eventGuestMap;
    }

    public List<PublicUser> getInvitedFriendsUserList() {
        return invitedFriendsUserList;
    }

    public void setInvitedFriendsUserList(List<PublicUser> invitedFriendsUserList) {
        this.invitedFriendsUserList = invitedFriendsUserList;
    }

    public void destroyInstance(){
        newEventBuilder = null;
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
