package com.example.c4q.capstone.userinterface;

import android.util.Log;

import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.utils.currentuser.CurrentUserListener;
import com.example.c4q.capstone.utils.currentuser.CurrentUserUtility;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class CurrentUser {
    private static CurrentUser userInstance;
    private CurrentUserListener userListener;
    private CurrentUserUtility userUtility = new CurrentUserUtility();
    private static String TAG = "CURRENT USER INSTANCE";
    private static String userID;
    private static String userFullName;
    private static PrivateUser currentPrivateUser;


    private CurrentUser(){
        Log.d(TAG, "constructor called");
        setUserID();
        setUserListener();
        setCurrentPrivateUser();
    }
    public static CurrentUser getInstance(){
        Log.d(TAG, "getInstance: instance called");
        if (userInstance == null)
            userInstance = new CurrentUser();
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


    private void setUserID() {
        userID =userUtility.currentUserID;
    }

    private void setCurrentPrivateUser() {
        userUtility.getCurrentPrivateUser(userListener);
    }
    private void setUserListener(){
        userListener = new CurrentUserListener() {
            @Override
            public void getPrivateUser(PrivateUser privateUser) {
                currentPrivateUser = privateUser;
                userFullName = currentPrivateUser.getFirst_name() + " " + currentPrivateUser.getLast_name();
                Log.d(TAG, "user full name: " + userFullName);
            }
        };
    }
}
