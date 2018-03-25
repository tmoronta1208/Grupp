package com.example.c4q.capstone.userinterface;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.utils.currentuser.CurrentUserListener;
import com.example.c4q.capstone.utils.currentuser.CurrentUserUtility;

import java.util.List;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class CurrentUser {
    private static CurrentUser userInstance;
    private CurrentUserListener userListener;
    private CurrentUserUtility userUtility = new CurrentUserUtility();
    private  static final String TAG = "CURRENT USER INSTANCE";

    private  String userID;
    private  String userFullName;
    private  PrivateUser currentPrivateUser;
    private  PublicUser currentPublicUser;
    private  List<PublicUser> userFriendsList;
    private  List<Events> userEventsList;



    private CurrentUser(){
        Log.d(TAG, "constructor called");
        setUserID();
        setUserListener();
        setCurrentPrivateUser();
        setCurrentPublicUser();
        //setUserFriendsList();
        //setUserEventsList();
    }

    public static CurrentUser getInstance(){
        Log.d(TAG, "getInstance: instance called");
        if (userInstance == null) userInstance = new CurrentUser();
            return userInstance;

    }

    public String getUserFullName() {
        return userFullName;
    }



    public String getUserID() {
        return userID;
    }

    public PrivateUser getCurrentPrivateUser() {
        return currentPrivateUser;
    }

    public  PublicUser getCurrentPublicUser() {
        return currentPublicUser;
    }

    public  List<PublicUser> getUserFriendsList() {
        return userFriendsList;
    }

    public List<Events> getUserEventsList() {
        return userEventsList;
    }

    private void setUserID() {
        userID =userUtility.currentUserID;
    }
    private void setCurrentPublicUser(){
        userUtility.getCurrentPublicUser(userListener);
    }

    private void setCurrentPrivateUser() {
        //userUtility.getCurrentPrivateUser(userListener);

    }

    private void setUserFriendsList(){
        userUtility.getCurrentUserFriends(userListener);
    }
    private void setUserEventsList(){
        userUtility.getCurrentUserEvents(userListener);
    }
    private void setUserListener(){
        userListener = new CurrentUserListener() {
            @Override
            public void getPrivateUser(PrivateUser privateUser) {
                currentPrivateUser = privateUser;
                userFullName = currentPrivateUser.getFirst_name() + " " + currentPrivateUser.getLast_name();
                Log.d(TAG, "user full name: " + userFullName);
            }

            @Override
            public void getPublicUser(PublicUser publicUser) {
                currentPublicUser = publicUser;
                Log.d(TAG, "current public user: " + currentPublicUser.getFirst_name());
            }

            @Override
            public void getUserFriends(List<PublicUser> publicUserList) {
                userFriendsList = publicUserList;
                Log.d(TAG, "user friends list size: " + userFriendsList.size());
            }
            @Override
            public void getUserEvents(List<Events> eventsList) {
                userEventsList = eventsList;
                Log.d(TAG, "user events list size: " + userEventsList.size());
            }
        };
    }
}
