package com.example.c4q.capstone.userinterface;

import android.util.Log;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;

import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;
import com.example.c4q.capstone.utils.currentuser.CurrentUserPostUtility;

import java.util.List;

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

    public String postNewEvent(String key, Events event){
        userPostUtility.addEventToDb(key, event);
        userPostUtility.addEventToUserEvents(key);
        return newEventKey;
    }

    public void postNewBarPreferences(List<String> barPrefs){
        userPostUtility.updateBarPrefs(barPrefs);
    }

    public void postNewAmenityPreferences(List<String> resPrefs){
        userPostUtility.updateAmenityPrefs(resPrefs);
    }

    public void postEventToUserEventList(String eventKey, String userId, UserEvent userEvent){
        userPostUtility.addEventToUserEventsList(eventKey, userId, userEvent);
    }

    public void postEventToUserInvitations(String eventKey, String userId, UserEvent userEvent){
        userPostUtility.addEventToEventInviteList(eventKey, userId, userEvent);
    }

    public void postProfilePictoPublicUser(UserIcon userIcon){
        userPostUtility.updateProfilePic(userIcon);
    }

    public void postVenueVote(String eventKey, String venueKey, Boolean vote){
        userPostUtility.updateVenueVote(eventKey,venueKey, vote);
    }

    public void postVenueVoteCount(String eventKey, String venueKey, int voteCount){
        userPostUtility.updateVenueVoteCount(eventKey,venueKey, voteCount);
    }
    public void postEventGuest(String eventKey, String userId, EventGuest eventGuest){
        userPostUtility.updateEventGuest(eventKey,userId, eventGuest);
    }
    public void removeEventInvite(String eventKey, String userId) {
        userPostUtility.removeEventFromEventInviteList(eventKey, userId);
    }
    public void postTopVenue(String eventKey, String venueId, String photoUrl, Events events){
        userPostUtility.updateTopVenue(eventKey, venueId, photoUrl, events);
    }

    public void deleteEvent (Events events){
        userPostUtility.deleteEvent(events);
    }
}
