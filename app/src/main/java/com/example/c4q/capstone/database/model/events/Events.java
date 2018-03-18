package com.example.c4q.capstone.database.model.events;


import java.util.List;

public class Events {
    private String event_id;
    private String event_name;
    private String event_organizer;
    private String event_note;
    private List<String> potential_venues;
    private List<String> top_three_venues;
    private String final_venue;
    private String event_date;
    private String event_time;
    private List<String> confirmed_guests;
    private List<String> invited_guests;
    private List<Venues> venues;

    public String getEvent_id() {
        return event_id;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_organizer() {
        return event_organizer;
    }

    public String getEvent_note() {
        return event_note;
    }

    public List<String> getPotential_venues() {
        return potential_venues;
    }

    public List<String> getTop_three_venues() {
        return top_three_venues;
    }

    public String getFinal_venue() {
        return final_venue;
    }

    public String getEvent_date() {
        return event_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public List<String> getConfirmed_guests() {
        return confirmed_guests;
    }

    public List<String> getInvited_guests() {
        return invited_guests;
    }

    public List<Venues> getVenues() {
        return venues;
    }
}
