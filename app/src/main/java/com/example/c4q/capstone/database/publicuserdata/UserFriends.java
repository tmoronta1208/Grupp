package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

public class UserFriends {
    private List<String> requested_user_id;

    public UserFriends() {
    }

    public UserFriends(List<String> requested_user_id) {
        this.requested_user_id = requested_user_id;
    }

    public List<String> getRequested_user_id() {
        return requested_user_id;
    }

    public void setRequested_user_id(List<String> requested_user_id) {
        this.requested_user_id = requested_user_id;
    }
}
