package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

public class UserFriends {
    private List<PublicUserDetails> friends;

    public UserFriends() {
    }

    public UserFriends(List<PublicUserDetails> friends) {
        this.friends = friends;
    }

    public List<PublicUserDetails> getFriends() {
        return friends;
    }

    public void setFriends(List<PublicUserDetails> friends) {
        this.friends = friends;
    }
}
