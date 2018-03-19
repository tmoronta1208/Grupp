package com.example.c4q.capstone.database.model.privateuserdata;


public class PrivateUser {
    private String first_name;
    private String last_name;
    private String username;
    private String email;
    private String password;
    private int age;
    private Address address;
    private PrivateUserAgeRange age_range;
    private PrivateUserLocation current_location;
    private PrivateUserNotifications notifications;
    private PrivateUserPreferences preferences;

    public PrivateUser() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public PrivateUserAgeRange getAge_range() {
        return age_range;
    }

    public void setAge_range(PrivateUserAgeRange age_range) {
        this.age_range = age_range;
    }

    public PrivateUserLocation getCurrent_location() {
        return current_location;
    }

    public void setCurrent_location(PrivateUserLocation current_location) {
        this.current_location = current_location;
    }

    public PrivateUserNotifications getNotifications() {
        return notifications;
    }

    public void setNotifications(PrivateUserNotifications notifications) {
        this.notifications = notifications;
    }

    public PrivateUserPreferences getPreferences() {
        return preferences;
    }

    public void setPreferences(PrivateUserPreferences preferences) {
        this.preferences = preferences;
    }
}
