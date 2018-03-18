package com.example.c4q.capstone.database.model;

import com.example.c4q.capstone.database.model.events.Events;
import com.example.c4q.capstone.database.model.groups.Groups;
import com.example.c4q.capstone.database.model.users.SharedUserData;
import com.example.c4q.capstone.database.model.users.User;

import java.util.List;

public class GruppData {
    private List<User> users;
    private List<SharedUserData> shared_user_data;
    private List<Events> events;
    private List<Groups> groups;

    public List<User> getUsers() {
        return users;
    }

    public List<SharedUserData> getShared_user_data() {
        return shared_user_data;
    }

    public List<Events> getEvents() {
        return events;
    }

    public List<Groups> getGroups() {
        return groups;
    }
}
