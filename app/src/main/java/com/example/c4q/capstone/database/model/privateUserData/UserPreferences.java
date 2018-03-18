package com.example.c4q.capstone.database.model.privateUserData;


class UserPreferences {
    private String[] bar_prefs;
    private String[] restuarant_prefs;
    private String budget;
    private String radius;

    public String[] getBar_prefs() {
        return bar_prefs;
    }

    public void setBar_prefs(String[] bar_prefs) {
        this.bar_prefs = bar_prefs;
    }

    public String[] getRestuarant_prefs() {
        return restuarant_prefs;
    }

    public void setRestuarant_prefs(String[] restuarant_prefs) {
        this.restuarant_prefs = restuarant_prefs;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }
}
