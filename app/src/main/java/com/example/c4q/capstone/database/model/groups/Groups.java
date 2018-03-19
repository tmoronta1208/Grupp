package com.example.c4q.capstone.database.model.groups;


import java.util.List;

public class Groups {
    private List<String> group_events;
    private List<String> group_members;
    private String group_name;

    public Groups() {
    }

    public List<String> getGroup_events() {
        return group_events;
    }

    public void setGroup_events(List<String> group_events) {
        this.group_events = group_events;
    }

    public List<String> getGroup_members() {
        return group_members;
    }

    public void setGroup_members(List<String> group_members) {
        this.group_members = group_members;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }
}
