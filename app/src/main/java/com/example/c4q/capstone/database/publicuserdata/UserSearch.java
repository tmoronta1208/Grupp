package com.example.c4q.capstone.database.publicuserdata;


public class UserSearch {
    private String first_name;
    private String last_name;
    private String email;
    private String icon_url;
    private String uid;
    private String zip_code;
    private int radius;

    public UserSearch() {
    }

    public UserSearch(String first_name, String last_name, String email, String icon_url, String uid, String zip_code, int radius) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.icon_url = icon_url;
        this.uid = uid;
        this.zip_code = zip_code;
        this.radius = radius;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
