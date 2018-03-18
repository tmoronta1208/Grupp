package com.example.c4q.capstone.database.model.privateUserData;


import java.util.List;

public class UserNotifications {
    private List<String> group_invites;
    private List<String> event_invites;
    private List<String> vote;
    private String organizer_vote;

    public List<String> getGroup_invites() {
        return group_invites;
    }

    public void setGroup_invites(List<String> group_invites) {
        this.group_invites = group_invites;
    }

    public List<String> getEvent_invites() {
        return event_invites;
    }

    public void setEvent_invites(List<String> event_invites) {
        this.event_invites = event_invites;
    }

    public List<String> getVote() {
        return vote;
    }

    public void setVote(List<String> vote) {
        this.vote = vote;
    }

    public String getOrganizer_vote() {
        return organizer_vote;
    }

    public void setOrganizer_vote(String organizer_vote) {
        this.organizer_vote = organizer_vote;
    }
}