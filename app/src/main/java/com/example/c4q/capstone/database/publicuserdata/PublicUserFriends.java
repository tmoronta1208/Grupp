package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

public class PublicUserFriends {
    private List<String> user_id;

    public PublicUserFriends() {
    }

    public PublicUserFriends(List<String> user_id) {
        this.user_id = user_id;
    }

    public List<String> getUser_id() {
        return user_id;
    }

    public void setUser_id(List<String> user_id) {
        this.user_id = user_id;
    }
}
