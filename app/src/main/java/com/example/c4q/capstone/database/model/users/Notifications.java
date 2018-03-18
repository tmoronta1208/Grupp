package com.example.c4q.capstone.database.model.users;


import java.util.List;

public class Notifications {
    private List<String> group_invites;
    private List<String> event_invites;
    private List<String> vote;
    private String organizer_vote;

    public List<String> getGroup_invites() {
        return group_invites;
    }

    public List<String> getEvent_invites() {
        return event_invites;
    }

    public List<String> getVote() {
        return vote;
    }

    public String getOrganizer_vote() {
        return organizer_vote;
    }
}