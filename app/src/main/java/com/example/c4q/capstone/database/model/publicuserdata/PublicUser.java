package com.example.c4q.capstone.database.model.publicuserdata;


public class PublicUser {
    private String first_name;
    private String last_name;
    private String zip_code;

    public PublicUser() {
    }

    public PublicUser(String first_name, String last_name, String zip_code) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.zip_code = zip_code;
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
}
