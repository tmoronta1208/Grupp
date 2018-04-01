package com.example.c4q.capstone.userinterface;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.utils.currentuser.CurrentUserListener;
import com.example.c4q.capstone.utils.currentuser.CurrentUserUtility;
import com.example.c4q.capstone.utils.currentuser.PublicUserListener;
import com.example.c4q.capstone.utils.currentuser.RealTimeEventsListener;

import java.util.List;
import java.util.Map;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class CurrentUser {
    private static CurrentUser userInstance;
    private  static final String TAG = "CURRENT USER INSTANCE";
    private CurrentUserListener userListener;
    private CurrentUserUtility userUtility   = new CurrentUserUtility();

    public static final String userID = CurrentUserUtility.setCurrentUserID();
    private  String userFullName;
    private  PrivateUser currentPrivateUser;
    private  PublicUser currentPublicUser;
    private  List<PublicUser> userFriendsList;
    private  List<Events> userEventsList;
    private  List<String> userFriendIDList;
    private Map<String, UserEvent> currentUserEventMap;
    private Map<String, UserEvent> currentUserEventInviteMap;
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
            //setCurrentPrivateUser();
           // setCurrentPublicUser();
        }
    }

    public Map<String, UserEvent> getCurrentUserEventMap() {
        return currentUserEventMap;
    }

    public Map<String, UserEvent> getCurrentUserEventInviteMap() {
        return currentUserEventInviteMap;
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

    public  PublicUser getCurrentPublicUser(final PublicUserListener publicUserListener) {
        userUtility.getCurrentPublicUser(new PublicUserListener() {
            @Override
            public void publicUserExists(Boolean userExists) {
                publicUserListener.publicUserExists(userExists);
            }
        });
        return currentPublicUser;
    }

    public  List<PublicUser> getUserFriendsList() {
        return userFriendsList;
    }

    public List<Events> getUserEventsList() {
        return userEventsList;
    }

    public void getRealTimeEvents(RealTimeEventsListener listener){
        Log.d(TAG, "get real time events called: ");
        userUtility.getRealTimeCurrentUserEvents(listener);
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
                    userFullName = currentPublicUser.getFirst_name() + " " + currentPublicUser.getLast_name();
                    Log.d(TAG, "user full name: " + userFullName);
                }
            }

            @Override
            public void getUserFriends(List<PublicUser> publicUserList) {
                userFriendsList = publicUserList;
                if (userFriendsList != null){
                    Log.d(TAG, "get user friends: user friends list size: " + userFriendsList.size());
                }

            }
            @Override
            public void getUserEvents(List<Events> eventsList) {
                userEventsList = eventsList;
                if (userEventsList != null){
                    Log.d(TAG, "get user Events : user events list size: " + userEventsList.size());
                }
            }

            @Override
            public void userHasFriends(Boolean hasFriends) {
                userHasFriends = hasFriends;
                Log.d(TAG, "user has friends: " + userHasFriends);
                if (userHasFriends){
                    //setUserFriendsList();
                }
            }

            @Override
            public void userHasEvents(Boolean hasEvents) {
                userHasEvents = hasEvents;
                Log.d(TAG, "user has events: " + userHasEvents);
                if(userHasEvents){
                    //setUserEventsList();
                }
            }

            @Override
            public void setUser(Boolean userInDB, String id) {
                currentUserExists = userInDB;
                createUser(userInDB);
                Log.d(TAG, "user in database: " + currentUserExists);

                Log.d(TAG, "user id: " + userID);
            }

            @Override
            public void getUserFriendIDs(List<String> friendIds) {
                userFriendIDList = friendIds;
                if (userFriendIDList != null){
                    Log.d(TAG, "user friend id list size : " + userFriendIDList.size());
                }
            }

            @Override
            public void getUserEventIDs(List<String> eventIds) {
                userEventIDList = eventIds;
                if (userEventIDList != null){
                    Log.d(TAG, "user events id list size : " + userEventIDList.size());
                }
            }

            @Override
            public void getUserEventList(Map<String, UserEvent> userEventMap) {
                currentUserEventMap = userEventMap;
                if (currentUserEventMap != null){
                    Log.d(TAG, "user events size : " + currentUserEventMap.size());
                }
            }

            @Override
            public void eventInviteList(Map<String, UserEvent> userEventMap) {
                currentUserEventInviteMap = userEventMap;
                if (currentUserEventInviteMap != null){
                    Log.d(TAG, "user invites : " + currentUserEventInviteMap.size());
                }
            }

        };
    }
}
