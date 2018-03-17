package com.example.c4q.capstone.database.model.events;


public class Venues {
    private String venue_id;
    private String venue_name;
    private Address venue_address;
    private String venue_phone;
    private String venue_type;
    private String hours;
    private String rating;
    private String ratings_count;
    private String age_restrictions;
    private String cover_charge;
    private String price_range;
    private String dress_code;
    private String music;
    private String amenities;
    private boolean reservations;
    private String payment_options;
    private Vote vote;

    public String getVenue_id() {
        return venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public Address getVenue_address() {
        return venue_address;
    }

    public String getVenue_phone() {
        return venue_phone;
    }

    public String getVenue_type() {
        return venue_type;
    }

    public String getHours() {
        return hours;
    }

    public String getRating() {
        return rating;
    }

    public String getRatings_count() {
        return ratings_count;
    }

    public String getAge_restrictions() {
        return age_restrictions;
    }

    public String getCover_charge() {
        return cover_charge;
    }

    public String getPrice_range() {
        return price_range;
    }

    public String getDress_code() {
        return dress_code;
    }

    public String getMusic() {
        return music;
    }

    public String getAmenities() {
        return amenities;
    }

    public boolean isReservations() {
        return reservations;
    }

    public String getPayment_options() {
        return payment_options;
    }

    public Vote getVote() {
        return vote;
    }
}
