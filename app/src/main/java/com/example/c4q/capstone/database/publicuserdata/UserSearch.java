package com.example.c4q.capstone.database.publicuserdata;


public class UserSearch {
    private PublicUserDetails user_details;

    public UserSearch() {
    }

    public UserSearch(PublicUserDetails user_details) {
        this.user_details = user_details;
    }

    public PublicUserDetails getUser_details() {
        return user_details;
    }

    public void setUser_details(PublicUserDetails user_details) {
        this.user_details = user_details;
    }
}
