package com.example.c4q.capstone.database.model.users;


public class SharedUserData {
    private String user_id;
    private String username;
    private String first_name;
    private String last_name;
    private AgeRange age_range;
    private Location location;
    private Preferences preferences;

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

    public AgeRange getAge_range() {
        return age_range;
    }

    public Location getLocation() {
        return location;
    }

    public Preferences getPreferences() {
        return preferences;
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
        private boolean share_current_location;
        private String primary_zip;
        private double lat;
        private double lng;

        public boolean isShare_current_location() {
            return share_current_location;
        }

        public String getPrimary_zip() {
            return primary_zip;
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

    class Preferences {
        private String[] bar_prefs;
        private String[] restuarant_prefs;
        private String budget;
        private int radius;

        public String[] getBar_prefs() {
            return bar_prefs;
        }

        public String[] getRestuarant_prefs() {
            return restuarant_prefs;
        }

        public String getBudget() {
            return budget;
        }

        public int getRadius() {
            return radius;
        }
    }
}