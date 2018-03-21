package com.example.c4q.capstone.database.model.publicuserdata;


public class UserSearch {
    private String email;
    private String username;

    public UserSearch(){}

    public UserSearch(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
