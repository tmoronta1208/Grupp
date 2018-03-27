package com.example.c4q.capstone.userinterface;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.utils.currentuser.CurrentUserListener;
import com.example.c4q.capstone.utils.currentuser.CurrentUserUtility;
import com.example.c4q.capstone.utils.currentuser.RealTimeEventsListener;

import java.util.List;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class CurrentUser {
    private static CurrentUser userInstance;
    private  static final String TAG = "CURRENT USER INSTANCE";
    private CurrentUserListener userListener;
    private CurrentUserUtility userUtility   = new CurrentUserUtility();

    private  String userID;
    private  String userFullName;
    private  PrivateUser currentPrivateUser;
    private  PublicUser currentPublicUser;
    private  List<PublicUser> userFriendsList;
    private  List<Events> userEventsList;
    private  List<String> userFriendIDList;
    private  List<String> userEventIDList;
    private boolean currentUserExists;
    private boolean userHasFriends;
    private boolean userHasEvents;
    private boolean userHasPublicProfile;
    private boolean userHasPrivateProfile;

    private CurrentUser(){
        Log.d(TAG, "constructor called");
        setUserListener();
        setUtility();
    }

    private void setUtility(){
        userUtility.setListener(userListener);
    }

    private void createUser(Boolean userExists){
        Log.d(TAG, "create user called");
        if(userExists){
            setCurrentPrivateUser();
            setCurrentPublicUser();
        }
    }

    public static CurrentUser getInstance(){
        Log.d(TAG, "getInstance: instance called");
        if (userInstance == null) userInstance = new CurrentUser();
            return userInstance;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public boolean isCurrentUserExists() {
        return currentUserExists;
    }

    public boolean isUserHasFriends() {
        return userHasFriends;
    }

    public boolean isUserHasEvents() {
        return userHasEvents;
    }

    public boolean isUserHasPublicProfile() {
        return userHasPublicProfile;
    }

    public boolean isUserHasPrivateProfile() {
        return userHasPrivateProfile;
    }

    public List<String> getUserFriendIDList() {
        return userFriendIDList;
    }

    public List<String> getUserEventIDList() {
        return userEventIDList;
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

    public void getRealTimeEvents(RealTimeEventsListener listener){
        userUtility.getRealTimeCurrentUserEvents(listener);
    }

    private void setCurrentPublicUser(){
        userUtility.getCurrentPublicUser(userListener);
    }

    private void setCurrentPrivateUser() {
        userUtility.getCurrentPrivateUser(userListener);

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
                userHasPrivateProfile = userUtility.userHasPrivateProfile;
                Log.d(TAG, "user has private profile: " + userHasPrivateProfile);
            }

            @Override
            public void getPublicUser(PublicUser publicUser) {
                currentPublicUser = publicUser;
                userHasPublicProfile =userUtility.userHasPublicProfile;
                Log.d(TAG, "user has public profile: " + userHasPublicProfile);
                if (currentPublicUser != null){
                    userFullName = currentPrivateUser.getFirst_name() + " " + currentPrivateUser.getLast_name();
                    Log.d(TAG, "user full name: " + userFullName);
                }
            }

            @Override
            public void getUserFriends(List<PublicUser> publicUserList) {
                userFriendsList = publicUserList;
                if (userFriendsList != null){
                    Log.d(TAG, "user friends list size: " + userFriendsList.size());
                }

            }
            @Override
            public void getUserEvents(List<Events> eventsList) {
                userEventsList = eventsList;
                if (userEventsList != null){
                    Log.d(TAG, "user events list size: " + userEventsList.size());
                }
            }

            @Override
            public void userHasFriends(Boolean hasFriends) {
                userHasFriends = hasFriends;
                Log.d(TAG, "user has friends: " + userHasFriends);
                if (userHasFriends){
                    setUserFriendsList();
                }
            }

            @Override
            public void userHasEvents(Boolean hasEvents) {
                userHasEvents = hasEvents;
                Log.d(TAG, "user has events: " + userHasEvents);
                if(userHasEvents){
                    setUserEventsList();
                }
            }

            @Override
            public void setUser(Boolean userInDB, String id) {
                createUser(userInDB);
                currentUserExists = userInDB;
                Log.d(TAG, "user in database: " + currentUserExists);
                userID = id;
                Log.d(TAG, "user id: " + userID);
            }

            @Override
            public void getUserFriendIDs(List<String> friendIds) {
                userFriendIDList = friendIds;
            }

            @Override
            public void getUserEventIDs(List<String> eventIds) {
                userEventIDList = eventIds;
            }

        };
    }
}
