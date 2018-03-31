package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

public class UserFriends {
    private String user_email;
    private List<PublicUserDetails> user_friend_details;

    public UserFriends() {
    }

    public UserFriends(String user_email, List<PublicUserDetails> user_friend_details) {
        this.user_email = user_email;
        this.user_friend_details = user_friend_details;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public List<PublicUserDetails> getUser_friend_details() {
        return user_friend_details;
    }

    public void setUser_friend_details(List<PublicUserDetails> user_friend_details) {
        this.user_friend_details = user_friend_details;
    }
}
