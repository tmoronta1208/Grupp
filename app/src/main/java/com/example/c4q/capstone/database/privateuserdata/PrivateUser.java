package com.example.c4q.capstone.database.privateuserdata;


import java.util.List;

public class PrivateUser {
    private String first_name;
    private String last_name;
    private boolean over_18;
    private boolean over_21;
    private int radius;
    private List<String> user_preferences;

    public PrivateUser() {
    }

    public PrivateUser(String first_name, String last_name, boolean over_18, boolean over_21, int radius) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.over_18 = over_18;
        this.over_21 = over_21;
        this.radius = radius;
    }

    public List<String> getUser_preferences() {
        return user_preferences;
    }

    public void setUser_preferences(List<String> user_preferences) {
        this.user_preferences = user_preferences;
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

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }

    public boolean isOver_21() {
        return over_21;
    }

    public void setOver_21(boolean over_21) {
        this.over_21 = over_21;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
