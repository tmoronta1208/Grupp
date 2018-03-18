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

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public Address getVenue_address() {
        return venue_address;
    }

    public void setVenue_address(Address venue_address) {
        this.venue_address = venue_address;
    }

    public String getVenue_phone() {
        return venue_phone;
    }

    public void setVenue_phone(String venue_phone) {
        this.venue_phone = venue_phone;
    }

    public String getVenue_type() {
        return venue_type;
    }

    public void setVenue_type(String venue_type) {
        this.venue_type = venue_type;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(String ratings_count) {
        this.ratings_count = ratings_count;
    }

    public String getAge_restrictions() {
        return age_restrictions;
    }

    public void setAge_restrictions(String age_restrictions) {
        this.age_restrictions = age_restrictions;
    }

    public String getCover_charge() {
        return cover_charge;
    }

    public void setCover_charge(String cover_charge) {
        this.cover_charge = cover_charge;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public String getDress_code() {
        return dress_code;
    }

    public void setDress_code(String dress_code) {
        this.dress_code = dress_code;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getAmenities() {
        return amenities;
    }

    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }

    public boolean isReservations() {
        return reservations;
    }

    public void setReservations(boolean reservations) {
        this.reservations = reservations;
    }

    public String getPayment_options() {
        return payment_options;
    }

    public void setPayment_options(String payment_options) {
        this.payment_options = payment_options;
    }

    public Vote getVote() {
        return vote;
    }

    public void setVote(Vote vote) {
        this.vote = vote;
    }
}
