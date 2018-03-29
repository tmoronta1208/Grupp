package com.example.c4q.capstone.userinterface.events;

import android.os.Build;
import android.util.Log;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.network.FourSquareDetailListener;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPTSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 3/19/18.
 * This is a class to handle data input for CreateEventAddNameFragment
 */

public class CreateEventPresenter {
    private Events newEvent;
    CurrentUser currentUser = CurrentUser.getInstance();
    CurrentUserPost currentUserPost = CurrentUserPost.getInstance();
    private static List<String> invitedGuests = new ArrayList<>();
    private boolean eventNameSet, eventDateSet, eventTimeSet, eventGuestsSet, eventNoteSet;
    private boolean nameDone, friendsDone;
    public String dateOfEvent;
    public String timeOfEvent;
    public String dateTime = "";
    private static String TAG = "CREATE_EVENT_PRES: ";
    List<String> dummyUsers;
    CreateEventPTSingleton createEventPTSingleton;
    String key;

    public CreateEventPresenter(CreateEventPTSingleton eventPTSingleton){
        createEventPTSingleton = eventPTSingleton;
        newEvent = new Events();
    }

    public void sendEventToFireBase(EventFragmentListener listener){
       key = setFinalizedEvent();

       Log.d(TAG, "event key : " + key);
       listener.getEventIdKEy(key);
       makeNetworkCall(createEventPTSingleton.getInvitedFriendsUserList());
        Log.d(TAG, "post event called");
       currentUserPost.postNewEvent(key, newEvent);
       UserEvent userEvent = creatUserEventFromEvent(newEvent);
       currentUserPost.postEventToUserEventList(key, currentUser.getUserID(),userEvent);
       sendInvites(userEvent, newEvent);
    }

    public void sendInvites(UserEvent userEvent, Events events){
        List<String> invitedGuests = new ArrayList<>();
        invitedGuests.addAll(events.getInvited_guests());
        invitedGuests.remove(currentUser.getUserID());
        for (String guest: invitedGuests){
            currentUserPost.postEventToUserInvitations(key, guest,userEvent);
        }

    }

    private void makeNetworkCall(List<PublicUser> eventGuests){
        final VenueVoteUtility venueVoteUtility = new VenueVoteUtility();
       venueVoteUtility.setVenueNetworkListener(new VenueNetworkListener() {
            @Override
            public void getFourSList(List<Venue> fourSVenues) {

            }

            @Override
            public void getFourSVenueIds(List<String> fourSquareVenueIds) {
                Log.d(TAG, "final venue list listener is called");
                if (fourSquareVenueIds != null){
                    Log.d(TAG, "final venue list size" + fourSquareVenueIds.size());
                    if (fourSquareVenueIds.size() != 0){
                        newEvent.setPotential_venues(fourSquareVenueIds);
                        currentUserPost.postNewEvent(key, newEvent);
                        venueVoteUtility.getDetailedVenues(fourSquareVenueIds, new FourSquareDetailListener() {
                            @Override
                            public void getVenueDetail(Venue venueDetail) {

                            }

                            @Override
                            public void getVenueDetailList(HashMap<String, Venue> venueDetailMap) {
                                Log.d(TAG, "venue detail listener called");
                                if (venueDetailMap!= null){
                                    if( venueDetailMap.size() != 0){
                                        Log.d(TAG, "venue detail list" + venueDetailMap.size());
                                        newEvent.setVenue_map(venueDetailMap);
                                        currentUserPost.postNewEvent(key, newEvent);
                                    }
                                } else{
                                    Log.d(TAG, "final venue list is null");
                                }
                            }
                        });
                    }
                } else{
                    Log.d(TAG, "final venue list is null");
                }

            }

       });
       venueVoteUtility.getVoteListFromFourSquare(eventGuests);
    }

    public void setEventName(String eventName){
        createEventPTSingleton.setEventName(eventName);
        eventNameSet = true;
        Log.d(TAG, "event name : " + eventName);
        eventNameSet = true;
        validateEvent();
    }

    public void setEventDate(String date){
        dateOfEvent = date;
        eventDateSet = true;
        Log.d(TAG, "event date  set: " + dateOfEvent);
        createEventPTSingleton.setEventDate(dateOfEvent);
        setDateAndTime();
        validateEvent();
    }

    public void setEventTime(TimePicker timePicker){

        if (Build.VERSION.SDK_INT < 23) {
            timeOfEvent = String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute());
        } else {
            timeOfEvent = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
        }
        createEventPTSingleton.setEventTime(timeOfEvent);
        setDateAndTime();
        eventTimeSet = true;
        validateEvent();
        Log.d(TAG, "event time : " + timeOfEvent);

    }

    public void setDateAndTime(){
        dateTime = "";
        StringBuilder sb = new StringBuilder(dateTime);
        if (createEventPTSingleton.getEventDate() != null) {
            sb.append("Date: ").append(createEventPTSingleton.getEventDate()).append(" ");
        }
        if (createEventPTSingleton.getEventTime() != null){
            sb.append("Time: ").append(createEventPTSingleton.getEventTime());
        }
        dateTime = sb.toString();
    }

    public void setEventGuests(List<String> invitedGuests){
        createEventPTSingleton.setInvitedGuests(invitedGuests);
        eventGuestsSet = true;
        friendsDone = true;
        Log.d(TAG, "invite size" + invitedGuests.size());
        validateEvent();
    }
    public boolean validateFriendsDone(){
        return friendsDone;
    }
    public boolean validateNameDone(){
        return eventTimeSet && eventDateSet && eventNameSet;
    }

    public void setEventNote(String note){
        createEventPTSingleton.setEventNote(note);
        Log.d(TAG, "event type" + note);
        eventNoteSet = true;
        validateEvent();
    }

    public boolean validateEvent(){
        boolean validEvent = false;
        if (eventTimeSet && eventNameSet && eventDateSet){
            Log.d(TAG, "create event: event valid");

            validEvent = true;

        } else {
            Log.d(TAG, "create event: event not valid");
        }
        return validEvent;
    }

    public String setFinalizedEvent(){
        key = currentUserPost.newEventKey();
        List<String> confirmedGuest = new ArrayList<>();
        List<String> invitedGuest = new ArrayList<>();
        invitedGuest.addAll(createEventPTSingleton.getInvitedGuests());
        invitedGuest.add(currentUser.getUserID());

        confirmedGuest.add(currentUser.getUserID());
        newEvent.setEvent_id(createEventPTSingleton.getEventID());
        newEvent.setEvent_name(createEventPTSingleton.getEventName());
        newEvent.setEvent_note(createEventPTSingleton.getEventNote());
        newEvent.setEvent_time(createEventPTSingleton.getEventTime());
        newEvent.setEvent_date(createEventPTSingleton.getEventDate());
        newEvent.setVenue_type(createEventPTSingleton.getEventVenueType());
        newEvent.setEvent_note(createEventPTSingleton.getEventNote());
        newEvent.setInvited_guests(invitedGuest);
        newEvent.setEvent_organizer(currentUser.getUserID());
        newEvent.setConfirmed_guests(confirmedGuest);
        if (createEventPTSingleton.getInvitedFriendsUserList() != null){
            Log.d(TAG, "pub user list size: " + createEventPTSingleton.getInvitedFriendsUserList());
        } else{
            Log.d(TAG, "pub user list is null");
        }
        newEvent.setEvent_id(key);
        Log.d(TAG, "event type" + createEventPTSingleton.getEventVenueType());
        return key;
    }

    public UserEvent creatUserEventFromEvent(Events event){
        UserEvent userEvent = new UserEvent();
        userEvent.setEvent_date(event.getEvent_date());
        userEvent.setEvent_id(event.getEvent_id());
        userEvent.setEvent_date(event.getEvent_date());
        userEvent.setEvent_time(userEvent.getEvent_time());
        userEvent.setEvent_organizer(event.getEvent_organizer());
        userEvent.setEvent_note(event.getEvent_note());
        userEvent.setEvent_name(event.getEvent_name());
        userEvent.setFinal_venue(event.getFinal_venue());
        return userEvent;
    }
}
