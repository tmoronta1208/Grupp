package com.example.c4q.capstone.database.events;


public class Venue {
    private String venue_name;
    private String venue_phone;
    private String venue_type;
    private String hours;
    private String payment_options;
    private String price_range;
    private boolean cover_charge;
    private float rating_avg;
    private int  rating_count;
    private boolean reservations;

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
