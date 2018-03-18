package com.example.c4q.capstone.database.model;

import com.example.c4q.capstone.database.model.events.Events;
import com.example.c4q.capstone.database.model.groups.Groups;
import com.example.c4q.capstone.database.model.sharedUserData.SharedUserData;
import com.example.c4q.capstone.database.model.privateUserData.User;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class GruppData {
    private List<User> users;
    private List<SharedUserData> shared_user_data;
    private List<Events> events;
    private List<Groups> groups;

    public GruppData() {
        // Default constructor required for calls to DataSnapshot.getValue(GruppData.class)
    }

    public GruppData(List<User> users, List<SharedUserData> shared_user_data, List<Events> events, List<Groups> groups) {
        this.users = users;
        this.shared_user_data = shared_user_data;
        this.events = events;
        this.groups = groups;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<SharedUserData> getShared_user_data() {
        return shared_user_data;
    }

    public void setShared_user_data(List<SharedUserData> shared_user_data) {
        this.shared_user_data = shared_user_data;
    }

    public List<Events> getEvents() {
        return events;
    }

    public void setEvents(List<Events> events) {
        this.events = events;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }
}
