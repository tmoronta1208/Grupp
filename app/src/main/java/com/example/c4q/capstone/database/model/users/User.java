package com.example.c4q.capstone.database.model.users;


import java.util.List;

public class User {
    private String user_id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private AgeRange age_range;
    private Location location;
    private Preferences preferences;
    private List<String> group_invites;
    private List<String> event_invites;
    private List<String> user_events;
    private List<String> user_groups;
    private Notifications notifications;
    private CurrentLocation current_location;

    public String getUser_id() {
        return user_id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AgeRange getAge_range() {
        return age_range;
    }

    public Location getLocation() {
        return location;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public List<String> getGroup_invites() {
        return group_invites;
    }

    public List<String> getEvent_invites() {
        return event_invites;
    }

    public List<String> getUser_events() {
        return user_events;
    }

    public List<String> getUser_groups() {
        return user_groups;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public CurrentLocation getCurrent_location() {
        return current_location;
    }

    class AgeRange {
        private boolean over_18;
        private boolean over_21;

        public boolean isOver_18() {
            return over_18;
        }

        public boolean isOver_21() {
            return over_21;
        }
    }

    class Location {
        private String street;
        private String city;
        private String state;
        private String zip;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getZip() {
            return zip;
        }
    }

    class Preferences {
        private String[] bar_prefs;
        private String[] restuarant_prefs;
        private String budget;
        private String radius;

        public String[] getBar_prefs() {
            return bar_prefs;
        }

        public String[] getRestuarant_prefs() {
            return restuarant_prefs;
        }

        public String getBudget() {
            return budget;
        }

        public String getRadius() {
            return radius;
        }
    }

    class CurrentLocation {
        private double lat;
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }
}
