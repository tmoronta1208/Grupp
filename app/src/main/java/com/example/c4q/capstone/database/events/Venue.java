package com.example.c4q.capstone.database.events;


import java.util.HashMap;
import java.util.List;

public class Venue {
    private String venue_id;
    private String venue_url;
    private String venue_photo_url;
    private String venue_address;
    private String venue_city;
    private String venue_postal_code;
    private String venue_name;
    private String venue_description;
    private String venue_phone;
    private String venue_type;
    private String hours;
    private String payment_options;
    private String price_range;
    private boolean cover_charge;
    private float rating_avg;
    private double venue_lat;
    private double venue_lng;
    private int  rating_count;
    private boolean reservations;
    private HashMap<String, Boolean> venue_vote;
    private int vote_count;

    public Venue() {
    }

    public Venue(String venue_name, String venue_phone, String venue_type, String hours, String payment_options, String price_range, boolean cover_charge, float rating_avg, int rating_count, boolean reservations) {
        this.venue_name = venue_name;
        this.venue_phone = venue_phone;
        this.venue_type = venue_type;
        this.hours = hours;
        this.payment_options = payment_options;
        this.price_range = price_range;
        this.cover_charge = cover_charge;
        this.rating_avg = rating_avg;
        this.rating_count = rating_count;
        this.reservations = reservations;
    }

    public double getVenue_lat() {
        return venue_lat;
    }

    public void setVenue_lat(double venue_lat) {
        this.venue_lat = venue_lat;
    }

    public double getVenue_lng() {
        return venue_lng;
    }

    public void setVenue_lng(double venue_lng) {
        this.venue_lng = venue_lng;
    }

    public String getVenue_city() {
        return venue_city;
    }

    public void setVenue_city(String venue_city) {
        this.venue_city = venue_city;
    }

    public String getVenue_postal_code() {
        return venue_postal_code;
    }

    public void setVenue_postal_code(String venue_postal_code) {
        this.venue_postal_code = venue_postal_code;
    }

    public String getVenue_description() {
        return venue_description;
    }

    public void setVenue_description(String venue_description) {
        this.venue_description = venue_description;
    }

    public HashMap<String, Boolean> getVenue_vote() {
        return venue_vote;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setVenue_vote(HashMap<String, Boolean> venue_vote) {
        this.venue_vote = venue_vote;
    }

    public String getVenue_url() {
        return venue_url;
    }

    public void setVenue_url(String venue_url) {
        this.venue_url = venue_url;
    }

    public String getVenue_address() {
        return venue_address;
    }

    public String getVenue_photo_url() {
        return venue_photo_url;
    }

    public void setVenue_photo_url(String venue_photo_url) {
        this.venue_photo_url = venue_photo_url;
    }

    public void setVenue_address(String venue_address) {
        this.venue_address = venue_address;
    }

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

    public String getPayment_options() {
        return payment_options;
    }

    public void setPayment_options(String payment_options) {
        this.payment_options = payment_options;
    }

    public String getPrice_range() {
        return price_range;
    }

    public void setPrice_range(String price_range) {
        this.price_range = price_range;
    }

    public boolean isCover_charge() {
        return cover_charge;
    }

    public void setCover_charge(boolean cover_charge) {
        this.cover_charge = cover_charge;
    }

    public float getRating_avg() {
        return rating_avg;
    }

    public void setRating_avg(float rating_avg) {
        this.rating_avg = rating_avg;
    }

    public int getRating_count() {
        return rating_count;
    }

    public void setRating_count(int rating_count) {
        this.rating_count = rating_count;
    }

    public boolean isReservations() {
        return reservations;
    }

    public void setReservations(boolean reservations) {
        this.reservations = reservations;
    }

}
