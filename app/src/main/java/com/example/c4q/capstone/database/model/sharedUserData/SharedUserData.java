package com.example.c4q.capstone.database.model.sharedUserData;



public class SharedUserData {
    private String user_id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private SharedUserAgeRange age_range;
    private SharedUserLocation location;
    private SharedUserPreferences preferences;

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

    public SharedUserAgeRange getAge_range() {
        return age_range;
    }

    public void setAge_range(SharedUserAgeRange age_range) {
        this.age_range = age_range;
    }

    public SharedUserLocation getLocation() {
        return location;
    }

    public void setLocation(SharedUserLocation location) {
        this.location = location;
    }

    public SharedUserPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(SharedUserPreferences preferences) {
        this.preferences = preferences;
    }
}