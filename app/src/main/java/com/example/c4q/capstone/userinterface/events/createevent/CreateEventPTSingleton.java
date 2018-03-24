package com.example.c4q.capstone.userinterface.events.createevent;

import android.util.Log;

import com.example.c4q.capstone.userinterface.CurrentUser;

/**
 * Created by amirahoxendine on 3/24/18.
 */

public class CreateEventPTSingleton {
    private static CreateEventPTSingleton createEventPTSingleton;
    private static final String TAG = "CreateEventPTSingleton";

    private CreateEventPTSingleton(){

    }

    public static CreateEventPTSingleton getNewInstance(){
        Log.d(TAG, "getInstance: instance called");
        createEventPTSingleton = new CreateEventPTSingleton();
        return createEventPTSingleton;
    }

    public static CreateEventPTSingleton getInstance(){
            return createEventPTSingleton;
    }

}
