package com.example.c4q.capstone.database.events;

import com.example.c4q.capstone.database.publicuserdata.UserIcon;

import java.util.List;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public class EventGuest {
    private boolean voted;
    private boolean confirmed_guest;
    private String user_id;
    private String user_firstname;
    private String user_lastname;
    private String user_status;
    private UserIcon user_icon;
    private int radius;
    private List<String> user_preferences;
    private String zip_code;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public UserIcon getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(UserIcon user_icon) {
        this.user_icon = user_icon;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    public boolean isConfirmed_guest() {
        return confirmed_guest;
    }

    public void setConfirmed_guest(boolean confirmed_guest) {
        this.confirmed_guest = confirmed_guest;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public List<String> getUser_preferences() {
        return user_preferences;
    }

    public void setUser_preferences(List<String> user_preferences) {
        this.user_preferences = user_preferences;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }
}
