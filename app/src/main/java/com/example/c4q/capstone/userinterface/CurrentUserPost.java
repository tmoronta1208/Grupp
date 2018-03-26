package com.example.c4q.capstone.userinterface;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.utils.currentuser.CurrentUserPostUtility;

/**
 * Created by amirahoxendine on 3/26/18.
 */

public class CurrentUserPost {
    private static CurrentUserPost userPostInstance;
    CurrentUserPostUtility userPostUtility = new CurrentUserPostUtility();
    private static String TAG = "CURRENT USER POST";
    private String newEventKey;

    private CurrentUserPost(){

    }

    public static CurrentUserPost getInstance(){
        Log.d(TAG, "getInstance: instance called");
        if (userPostInstance == null){
            userPostInstance = new CurrentUserPost();
        }
        return userPostInstance;
    }
    public String newEventKey(){
        return userPostUtility.getNewEventKey();
    }
    public String postNewEvent(Events event){
        newEventKey = newEventKey();
        userPostUtility.addEventToDb(newEventKey, event);
        userPostUtility.addEventToUserEvents(newEventKey);
        return newEventKey;
    }
}
