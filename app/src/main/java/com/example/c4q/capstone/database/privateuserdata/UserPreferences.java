package com.example.c4q.capstone.database.privateuserdata;


import java.util.List;

public class UserPreferences {
    private List<String> amenity_preferences;
    private List<String> bar_prefs;

    public UserPreferences() {
    }

    public UserPreferences(List<String> amenity_preferences, List<String> bar_prefs) {
        this.amenity_preferences = amenity_preferences;
        this.bar_prefs = bar_prefs;
    }

    public List<String> getAmenity_preferences() {
        return amenity_preferences;
    }

    public void setAmenity_preferences(List<String> amenity_preferences) {
        this.amenity_preferences = amenity_preferences;
    }

    public List<String> getBar_prefs() {
        return bar_prefs;
    }

    public void setBar_prefs(List<String> bar_prefs) {
        this.bar_prefs = bar_prefs;
    }
}
