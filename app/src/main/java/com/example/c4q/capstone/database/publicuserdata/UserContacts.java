package com.example.c4q.capstone.database.publicuserdata;


import java.util.List;

public class UserContacts {
    private List<PublicUserDetails> contacts;

    public UserContacts() {
    }

    public UserContacts(List<PublicUserDetails> contacts) {
        this.contacts = contacts;
    }

    public List<PublicUserDetails> getContacts() {
        return contacts;
    }

    public void setContacts(List<PublicUserDetails> contacts) {
        this.contacts = contacts;
    }
}
