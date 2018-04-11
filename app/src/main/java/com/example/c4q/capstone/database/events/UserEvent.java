package com.example.c4q.capstone.database.events;

import com.example.c4q.capstone.database.publicuserdata.UserIcon;

/**
 * Created by amirahoxendine on 3/29/18.
 * short event model for user events list
 */

public class UserEvent {
    private String event_id;
    private String event_date;
    private String event_name;
    private String event_note;
    private String event_organizer;
    private String event_organizer_full_name;
    private UserIcon event_organizer_icon;
    private String event_time;
    private String final_venue;
    private String event_photo;
    private boolean vote_complete;

    public UserIcon getEvent_organizer_icon() {
        return event_organizer_icon;
    }

    public void setEvent_organizer_icon(UserIcon event_organizer_icon) {
        this.event_organizer_icon = event_organizer_icon;
    }

    public boolean isVote_complete() {
        return vote_complete;
    }

    public void setVote_complete(boolean vote_complete) {
        this.vote_complete = vote_complete;
    }

    public String getEvent_photo() {
        return event_photo;
    }

    public void setEvent_photo(String event_photo) {
        this.event_photo = event_photo;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_note() {
        return event_note;
    }

    public void setEvent_note(String event_note) {
        this.event_note = event_note;
    }

    public String getEvent_organizer() {
        return event_organizer;
    }

    public void setEvent_organizer(String event_organizer) {
        this.event_organizer = event_organizer;
    }

    public String getEvent_organizer_full_name() {
        return event_organizer_full_name;
    }

    public void setEvent_organizer_full_name(String event_organizer_full_name) {
        this.event_organizer_full_name = event_organizer_full_name;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getFinal_venue() {
        return final_venue;
    }

    public void setFinal_venue(String final_venue) {
        this.final_venue = final_venue;
    }
}
