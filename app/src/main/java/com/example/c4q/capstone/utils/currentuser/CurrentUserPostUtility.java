package com.example.c4q.capstone.utils.currentuser;

import android.util.Log;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.EventInviteActivity;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.example.c4q.capstone.utils.Constants.AMENITY_PREFS;
import static com.example.c4q.capstone.utils.Constants.BAR_PREFS;
import static com.example.c4q.capstone.utils.Constants.EVENTS;
import static com.example.c4q.capstone.utils.Constants.EVENT_GUEST_MAP;
import static com.example.c4q.capstone.utils.Constants.EVENT_INVITATIONS;
import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_EVENTS;
import static com.example.c4q.capstone.utils.Constants.USER_EVENT_LIST;
import static com.example.c4q.capstone.utils.Constants.USER_ICON;
import static com.example.c4q.capstone.utils.Constants.VENUE_MAP;
import static com.example.c4q.capstone.utils.Constants.VENUE_VOTE;
import static com.example.c4q.capstone.utils.Constants.VENUE_VOTE_COUNT;

/**
 * Created by amirahoxendine on 3/26/18.
 */

public class CurrentUserPostUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference userEventsReference;
    private DatabaseReference preferencesReference;
    private DatabaseReference eventsReference;
    private DatabaseReference publicUserReference;
    private DatabaseReference eventGuestReference;
    private DatabaseReference userEventListReference;


    private DatabaseReference eventInvitesReference;
    private static final String TAG = "PostUtility";
    private String currentUserId = CurrentUser.userID;

    public CurrentUserPostUtility(){

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        userEventsReference = firebaseDatabase.child(USER_EVENTS);
        eventsReference = firebaseDatabase.child(EVENTS);
        preferencesReference = firebaseDatabase.child(PUBLIC_USER).child(currentUserId).child(PREFERENCES);
        eventInvitesReference = firebaseDatabase.child(EVENT_INVITATIONS);
        publicUserReference = firebaseDatabase.child(PUBLIC_USER);
        userEventListReference = firebaseDatabase.child(USER_EVENT_LIST);
        //eventGuestReference = firebaseDatabase.child(E);
    }

    public String getNewEventKey(){
       String key = eventsReference.push().getKey();
       return key;
    }

    public void addEventToDb(String key, Events newEvent){
        Log.d(TAG, "event key" + key);
        Log.d(TAG, "event name" + newEvent.getEvent_name());
        eventsReference.child(key).setValue(newEvent);
    }

    public void addEventToUserEvents(String key){
        List<String> userEventKeys = CurrentUser.getInstance().getUserEventIDList();
        if (userEventKeys == null){
            userEventKeys = new ArrayList<>();
        }
        userEventKeys.add(key);
        Set<String> eventKeys = new HashSet<>();
        eventKeys.addAll(userEventKeys);
        userEventKeys = new ArrayList<>();
        userEventKeys.addAll(eventKeys);
        Map<String, Object> user_events = new HashMap<>();
        user_events.put(currentUserId, userEventKeys);
        userEventsReference.updateChildren(user_events);
    }

    public void addEventToUserEventsList(final String eventKey, final String userId, final UserEvent userEvent){
        CurrentUserUtility currentUserUtility = new CurrentUserUtility();
        //get map, add event to map, update child node.
        currentUserUtility.getSingleUserEventList(userId, new UserEventListener() {
            @Override
            public void getUserEventList(Map<String, UserEvent> userEventMap) {
                if (userEventMap != null){
                    Map<String, Object> eventMap = new HashMap<>();
                    eventMap.putAll(userEventMap);
                    eventMap.put(eventKey, userEvent);
                    userEventListReference.child(userId).updateChildren(eventMap);

                }

            }

        });
    }

    public void addEventToEventInviteList(final String eventKey, final String userId, final UserEvent userEvent){
        CurrentUserUtility currentUserUtility = new CurrentUserUtility();
        //get map, add event to map, update child node.
        currentUserUtility.getSingleEventInviteList(userId, new UserEventListener() {
            @Override
            public void getUserEventList(Map<String, UserEvent> userEventMap) {
                if (userEventMap != null){
                    Map<String, Object> eventMap = new HashMap<>();
                    eventMap.putAll(userEventMap);
                    eventMap.put(eventKey, userEvent);
                    eventInvitesReference.child(userId).updateChildren(eventMap);
                }
            }

        });
    }
    public void removeEventFromEventInviteList(final String eventKey, final String userId){
                    eventInvitesReference.child(userId).child(eventKey).removeValue();
    }

    public void updateBarPrefs(List<String> barPrefs){
        Map<String, Object> userPrefs = new HashMap<>();
        userPrefs.put(BAR_PREFS, barPrefs);
        preferencesReference.updateChildren(userPrefs);
    }

    public void updateAmenityPrefs(List<String> amenityPrefs){
        Map<String, Object> userPrefs = new HashMap<>();
        userPrefs.put(AMENITY_PREFS, amenityPrefs);
        preferencesReference.updateChildren(userPrefs);
    }
    public void updateProfilePic(UserIcon userIcon){

        publicUserReference.child(currentUserId).child(USER_ICON).setValue(userIcon);
    }

    public void updateVenueVote(String eventKey, String venueKey, Boolean vote){
       eventsReference.child(eventKey).child(VENUE_MAP).child(venueKey).child(VENUE_VOTE).child(currentUserId).setValue(vote);
    }

    public void updateVenueVoteCount(String eventKey, String venueKey, int voteCount){
        eventsReference.child(eventKey).child(VENUE_MAP).child(venueKey).child(VENUE_VOTE_COUNT).setValue(voteCount);
    }
    public void updateVenueVoteComplete(String eventKey,boolean voteComplete){
        eventsReference.child(eventKey).child("vote_complete").setValue(voteComplete);
    }
    public void updateEventGuest(String eventKey, String userId, EventGuest eventGuest){
        eventsReference.child(eventKey).child(EVENT_GUEST_MAP).child(userId).setValue(eventGuest);
    }
    public void removeEventFromUserList(String eventId, String userId){
        userEventListReference.child(userId).child(eventId).removeValue();
    }
    public void deleteEvent(Events events){
        String eventId = events.getEvent_id();
        eventsReference.child(eventId).removeValue();
        if (events.getConfirmed_guests() != null) {
            List<String> confirmed_guests = events.getConfirmed_guests();
            for (String c: confirmed_guests){
                userEventListReference.child(c).child(eventId).removeValue();
            }
        }
       if (events.getInvited_guests() != null){
           List<String> inviteList = events.getInvited_guests();
           for (String s: inviteList){
               eventInvitesReference.child(s).child(eventId).removeValue();
           }
       }
    }
    public void updateTopVenue(String eventKey, String venueID, String photoUrl, Events event){
        eventsReference.child(eventKey).child("top_venue").setValue(venueID);
        if (photoUrl != null){
            eventsReference.child(eventKey).child("top_venue_photo").setValue(photoUrl);
        }
        for (String s: event.getEvent_guest_map().keySet()){
            EventGuest guest = event.getEvent_guest_map().get(s);
            if (guest.isConfirmed_guest()){
                userEventListReference.child(s).child(eventKey).child("event_photo").setValue(photoUrl);
            }else if (!guest.isConfirmed_guest()){
                eventInvitesReference.child(s).child(eventKey).child("event_photo").setValue(photoUrl);
            }
        }
    }

    public void updateGuestStatus(String eventKey, String status, String userId){
        eventsReference.child(eventKey).child(EVENT_GUEST_MAP).child(userId).child("user_status").setValue(status);
    }
}
