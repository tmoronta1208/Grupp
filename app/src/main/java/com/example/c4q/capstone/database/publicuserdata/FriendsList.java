package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

public class FriendsList {
    private String user_id;
    private List<String> requested_user_id;

    public FriendsList() {
    }

    public FriendsList( String user_id,List<String> requested_user_id) {
        this.requested_user_id = requested_user_id;
        this.user_id = user_id;
    }

    public List<String> getRequested_user_id() {
        return requested_user_id;
    }

    public void setRequested_user_id(List<String> requested_user_id) {
        this.requested_user_id = requested_user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
