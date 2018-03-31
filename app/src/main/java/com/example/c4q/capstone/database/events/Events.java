package com.example.c4q.capstone.database.events;


import java.util.HashMap;
import java.util.List;

public class Events {
    private boolean cancelled;
    private boolean vote_complete;
    private String event_date;
    private String event_name;
    private String event_note;
    private String event_organizer;
    private String event_id;
    private String venue_type;
    private String event_time;
    private String final_venue;
    private HashMap<String, EventGuest> event_guest_map;
    private List<String> confirmed_guests;
    private List<String> invited_guests;
    private List<String> potential_venues;
    private List<String> top_three_venues;
    private HashMap<String, Venue> venue_map;
    private List<Venue> venue_list;
    private String top_venue_photo;


    public Events(){}

    public Events(boolean cancelled, String event_date, String event_name, String event_note, String event_organizer, String event_time, List<String> invited_guests) {
        this.cancelled = cancelled;
        this.event_date = event_date;
        this.event_name = event_name;
        this.event_note = event_note;
        this.event_organizer = event_organizer;
        this.event_time = event_time;
        this.invited_guests = invited_guests;
    }

    public Events(boolean cancelled, String event_date, String event_name, String event_note, String event_organizer, String event_time, String final_venue, List<String> confirmed_guests, List<String> invited_guests, List<String> potential_venues, List<String> top_three_venues) {
        this.cancelled = cancelled;
        this.event_date = event_date;
        this.event_name = event_name;
        this.event_note = event_note;
        this.event_organizer = event_organizer;
        this.event_time = event_time;
        this.final_venue = final_venue;
        this.confirmed_guests = confirmed_guests;
        this.invited_guests = invited_guests;
        this.potential_venues = potential_venues;
        this.top_three_venues = top_three_venues;
    }

    public String getTop_venue_photo() {
        return top_venue_photo;
    }

    public void setTop_venue_photo(String top_venue_photo) {
        this.top_venue_photo = top_venue_photo;
    }

    public boolean isVote_complete() {
        return vote_complete;
    }

    public HashMap<String, EventGuest> getEvent_guest_map() {
        return event_guest_map;
    }

    public void setEvent_guest_map(HashMap<String, EventGuest> event_guest_map) {
        this.event_guest_map = event_guest_map;
    }

    public boolean vote_complete() {
        return vote_complete;
    }

    public void setVote_complete(boolean vote_complete) {
        this.vote_complete = vote_complete;
    }

    public HashMap<String, Venue> getVenue_map() {
        return venue_map;
    }

    public void setVenue_map(HashMap<String, Venue> venue_map) {
        this.venue_map = venue_map;
    }

    public List<Venue> getVenue_list() {
        return venue_list;
    }

    public void setVenue_list(List<Venue> venue_list) {
        this.venue_list = venue_list;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
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

    public List<String> getConfirmed_guests() {
        return confirmed_guests;
    }

    public void setConfirmed_guests(List<String> confirmed_guests) {
        this.confirmed_guests = confirmed_guests;
    }

    public List<String> getInvited_guests() {
        return invited_guests;
    }

    public void setInvited_guests(List<String> invited_guests) {
        this.invited_guests = invited_guests;
    }

    public List<String> getPotential_venues() {
        return potential_venues;
    }

    public void setPotential_venues(List<String> potential_venues) {
        this.potential_venues = potential_venues;
    }

    public List<String> getTop_three_venues() {
        return top_three_venues;
    }

    public void setTop_three_venues(List<String> top_three_venues) {
        this.top_three_venues = top_three_venues;
    }

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getVenue_type() {
        return venue_type;
    }

    public void setVenue_type(String venue_type) {
        this.venue_type = venue_type;
    }
}
