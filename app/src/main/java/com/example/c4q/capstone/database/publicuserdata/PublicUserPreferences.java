package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

class PublicUserPreferences {
    private List<String> bar_prefs;
    private List<String> restaurant_prefs;


    public PublicUserPreferences(){}

    public PublicUserPreferences(List<String> bar_prefs, List<String> restaurant_prefs) {
        this.bar_prefs = bar_prefs;
        this.restaurant_prefs = restaurant_prefs;
    }

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

}
