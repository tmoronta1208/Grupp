package com.example.c4q.capstone.database.model.privateUserData;


import java.util.List;

public class User {
    private String user_id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private UserAgeRange age_range;
    private UserLocation location;
    private UserPreferences preferences;
    private List<String> group_invites;
    private List<String> event_invites;
    private List<String> user_events;
    private List<String> user_groups;
    private UserNotifications notifications;
    private UserCurrentLocation current_location;


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAgeRange getAge_range() {
        return age_range;
    }

    public void setAge_range(UserAgeRange age_range) {
        this.age_range = age_range;
    }

    public UserLocation getLocation() {
        return location;
    }

    public void setLocation(UserLocation location) {
        this.location = location;
    }

    public UserPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(UserPreferences preferences) {
        this.preferences = preferences;
    }

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

    public List<String> getUser_events() {
        return user_events;
    }

    public void setUser_events(List<String> user_events) {
        this.user_events = user_events;
    }

    public List<String> getUser_groups() {
        return user_groups;
    }

    public void setUser_groups(List<String> user_groups) {
        this.user_groups = user_groups;
    }

    public UserNotifications getNotifications() {
        return notifications;
    }

    public void setNotifications(UserNotifications notifications) {
        this.notifications = notifications;
    }

    public UserCurrentLocation getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(UserCurrentLocation current_location) {
        this.current_location = current_location;
    }
}
