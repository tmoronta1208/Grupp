package com.example.c4q.capstone.database.model.sharedUserData;


class SharedUserPreferences {
    private String[] bar_prefs;
    private String[] restaurant_prefs;
    private String budget;
    private int radius;

    public String[] getBar_prefs() {
        return bar_prefs;
    }

    public void setBar_prefs(String[] bar_prefs) {
        this.bar_prefs = bar_prefs;
    }

    public String[] getRestaurant_prefs() {
        return restaurant_prefs;
    }

    public void setRestaurant_prefs(String[] restaurant_prefs) {
        this.restaurant_prefs = restaurant_prefs;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
