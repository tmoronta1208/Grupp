package com.example.c4q.capstone.database.model.publicuserdata;


public class PublicUser {
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private PublicUserLocation location;
    private PublicUserAgeRange age_range;
    private PublicUserPreferences preferences;

    public PublicUser() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PublicUserLocation getLocation() {
        return location;
    }

    public void setLocation(PublicUserLocation location) {
        this.location = location;
    }

    public PublicUserAgeRange getAge_range() {
        return age_range;
    }

    public void setAge_range(PublicUserAgeRange age_range) {
        this.age_range = age_range;
    }

    public PublicUserPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(PublicUserPreferences preferences) {
        this.preferences = preferences;
    }
}
