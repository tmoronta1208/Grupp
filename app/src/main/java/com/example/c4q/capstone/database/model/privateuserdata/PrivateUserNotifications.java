package com.example.c4q.capstone.database.model.privateuserdata;


import java.util.List;

class PrivateUserNotifications {
    private List<Boolean> event_invites;
    private List<Boolean> group_invites;
    private List<String> vote;

    public List<Boolean> getEvent_invites() {
        return event_invites;
    }

    public void setEvent_invites(List<Boolean> event_invites) {
        this.event_invites = event_invites;
    }

    public List<Boolean> getGroup_invites() {
        return group_invites;
    }

    public void setGroup_invites(List<Boolean> group_invites) {
        this.group_invites = group_invites;
    }

    public List<String> getVote() {
        return vote;
    }

    public void setVote(List<String> vote) {
        this.vote = vote;
    }
}
