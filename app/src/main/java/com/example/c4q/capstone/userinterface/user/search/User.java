package com.example.c4q.capstone.userinterface.user.search;


public class User {
    private String first_name;
    private String last_name;
    private String email;
    private String id;
    private String username;
    private String user_icon;

    public User() {
        //required empty constructor
    }

    public User(String first_name, String last_name, String email, String id, String username, String user_icon) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.id = id;
        this.username = username;
        this.user_icon = user_icon;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public void setUser_icon(String user_icon) {
        this.user_icon = user_icon;
    }
}
