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

    public List<String> getNo() {
        return no;
    }

    public List<String> getMaybe() {
        return maybe;
    }

    public boolean isComplete() {
        return complete;
    }
}
