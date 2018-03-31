package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

public class UserFriends {
    private String user_email;
    private List<PublicUserDetails> friends;

    public UserFriends() {
    }

    public UserFriends(String user_email, List<PublicUserDetails> friends) {
        this.user_email = user_email;
        this.friends = friends;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public List<PublicUserDetails> getUser_friend_details() {
        return friends;
    }

    public void setFriends(List<PublicUserDetails> friends) {
        this.friends = friends;
    }
}
