package com.example.c4q.capstone.database.model.groups;


import java.util.List;

public class Groups {
    private String group_name;
    private String group_id;
    private List<String> group_users;
    private List<String> group_events;

    public String getGroup_name() {
        return group_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public List<String> getGroup_users() {
        return group_users;
    }

    public List<String> getGroup_events() {
        return group_events;
    }
}
