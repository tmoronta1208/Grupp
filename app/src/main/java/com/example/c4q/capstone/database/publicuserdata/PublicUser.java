package com.example.c4q.capstone.database.publicuserdata;


public class PublicUser {
    private String user_id;
    private String first_name;
    private String last_name;
    private String zip_code;
    private String budget;
    private String email;
    private boolean over_18;
    private boolean over_21;
    private int radius;

    public PublicUser() {
    }

    public PublicUser(String user_id, String first_name, String last_name, String zip_code, String budget, String email, boolean over_18, boolean over_21, int radius) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.zip_code = zip_code;
        this.user_id = user_id;
        this.budget = budget;
        this.email = email;
        this.over_18 = over_18;
        this.over_21 = over_21;
        this.radius = radius;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }

    public boolean isOver_21() {
        return over_21;
    }

    public void setOver_21(boolean over_21) {
        this.over_21 = over_21;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
