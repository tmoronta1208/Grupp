package com.example.c4q.capstone.database.model.privateuserdata;


import java.util.List;

class PrivateUserPreferences {
    private List<String> bar_prefs;
    private List<String> restaurant_prefs;
    private int radius;
    private String budget;

    public List<String> getBar_prefs() {
        return bar_prefs;
    }

    public void setBar_prefs(List<String> bar_prefs) {
        this.bar_prefs = bar_prefs;
    }

    public List<String> getRestaurant_prefs() {
        return restaurant_prefs;
    }

    public void setRestaurant_prefs(List<String> restaurant_prefs) {
        this.restaurant_prefs = restaurant_prefs;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}
