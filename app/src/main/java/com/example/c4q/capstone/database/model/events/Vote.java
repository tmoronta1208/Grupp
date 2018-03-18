package com.example.c4q.capstone.database.model.events;


import java.util.List;

public class Vote {
    private List<String> yes;
    private List<String> no;
    private List<String> maybe;
    private boolean complete;

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

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
