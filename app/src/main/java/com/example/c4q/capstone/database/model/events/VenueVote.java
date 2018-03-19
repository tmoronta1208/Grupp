package com.example.c4q.capstone.database.model.events;


import java.util.List;

class VenueVote {
    private boolean vote_complete;
    private List<String> yes;
    private List<String> no;
    private List<String> maybe;

    public boolean isVote_complete() {
        return vote_complete;
    }

    public void setVote_complete(boolean vote_complete) {
        this.vote_complete = vote_complete;
    }

    public List<String> getYes() {
        return yes;
    }

    public void setYes(List<String> yes) {
        this.yes = yes;
    }

    public List<String> getNo() {
        return no;
    }

    public void setNo(List<String> no) {
        this.no = no;
    }

    public List<String> getMaybe() {
        return maybe;
    }

    public void setMaybe(List<String> maybe) {
        this.maybe = maybe;
    }
}
