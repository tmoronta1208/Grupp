package com.example.c4q.capstone.userinterface.events.createevent;

import java.util.List;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class CreateEventModel {
    String eventVenueType;
    String eventName;
    String eventDate;
    String eventTime;
    String eventNote;
    List<String> invitedGuests;

    public String getEventVenueType() {
        return eventVenueType;
    }

    public void setEventVenueType(String eventVenueType) {
        this.eventVenueType = eventVenueType;
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

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
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
}
