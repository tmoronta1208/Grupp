package com.example.c4q.capstone.userinterface.events;

import android.os.Build;
import android.util.Log;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.network.FourSquareDetailListener;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.events.createevent.NewEventBuilder;
import com.example.c4q.capstone.userinterface.events.createevent.NewEventConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 3/19/18.
 * This is a class to handle data input for CreateEventAddNameFragment
 */

public class CreateEventPresenter {
    private Events newEvent;
    private boolean eventNameSet, eventDateSet, eventTimeSet, eventGuestsSet, eventNoteSet;
    private boolean nameDone, friendsDone;
    public String dateOfEvent;
    public String timeOfEvent;
    public String dateTime = "";
    private static String TAG = "CREATE_EVENT_PRES: ";
    NewEventBuilder newEventBuilder;
    NewEventConverter newEventConverter = new NewEventConverter();
    String key;
    HashMap<String, EventGuest> eventGuestHashMap;
    String currentUserID = CurrentUser.userID;

    public CreateEventPresenter(NewEventBuilder eventPTSingleton){
        newEventBuilder = eventPTSingleton;
        newEvent = new Events();
    }

    public void sendEventToFireBase(EventFragmentListener listener){
        if(validateEvent()){
            key = setFinalizedEvent();
            Log.d(TAG, "event key : " + key);
            listener.getEventIdKEy(key);
            makeNetworkCall(newEventBuilder.getInvitedFriendsUserList());
            Log.d(TAG, "post event called" + newEventBuilder.getInvitedFriendsUserList().size());

            CurrentUserPost.getInstance().postNewEvent(key, newEvent);
            //UserEvent userEvent = creatUserEventFromEvent(newEvent);
            UserEvent userEvent = newEventConverter.creatUserEventFromEvent(newEvent);
            CurrentUserPost.getInstance().postEventToUserEventList(key, currentUserID,userEvent);
            sendInvites(userEvent, newEvent);
        }
    }

    public void sendInvites(UserEvent userEvent, Events events){
            List<String> invitedGuests = new ArrayList<>();
            invitedGuests.addAll(events.getInvited_guests());
            //invitedGuests.add(currentUserID);
            for (String guest: invitedGuests){
                CurrentUserPost.getInstance().postEventToUserInvitations(key, guest,userEvent);
            }
    }

    private void makeNetworkCall(List<PublicUser> eventGuests){
        final VenueNetworkUtility venueNetworkUtility = new VenueNetworkUtility();
       venueNetworkUtility.setVenueNetworkListener(new VenueNetworkListener() {
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
                        CurrentUserPost.getInstance().postNewEvent(key, newEvent);
                        venueNetworkUtility.getDetailedVenues(fourSquareVenueIds, new FourSquareDetailListener() {
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
                                        CurrentUserPost.getInstance().postNewEvent(key, newEvent);
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
       venueNetworkUtility.getVoteListFromFourSquare(eventGuests);
    }

    public void setEventName(String eventName){
        newEventBuilder.setEventName(eventName);
        eventNameSet = true;
        Log.d(TAG, "event name : " + eventName);
        validateEvent();
    }

    public void setEventDate(String date){
        dateOfEvent = date;
        eventDateSet = true;
        newEventBuilder.setEventDate(dateOfEvent);
        Log.d(TAG, "event date  set: " + dateOfEvent);
        setDateAndTime();
        validateEvent();
    }

    public void setEventTime(TimePicker timePicker){

        if (Build.VERSION.SDK_INT < 23) {
            timeOfEvent = String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute());
        } else {
            timeOfEvent = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
        }
        newEventBuilder.setEventTime(timeOfEvent);
        setDateAndTime();
        eventTimeSet = true;
        validateEvent();
        Log.d(TAG, "event time : " + timeOfEvent);

    }

    public void setDateAndTime(){
        dateTime = "";
        StringBuilder sb = new StringBuilder(dateTime);
        if (newEventBuilder.getEventDate() != null) {
            sb.append("Date: ").append(newEventBuilder.getEventDate()).append(" ");
        }
        if (newEventBuilder.getEventTime() != null){
            sb.append("Time: ").append(newEventBuilder.getEventTime());
        }
        dateTime = sb.toString();
    }

    public void setEventGuests(List<String> invitedGuests){
        newEventBuilder.setInvitedGuests(invitedGuests);
        eventGuestsSet = true;
        Log.d(TAG, "invite size" + invitedGuests.size());
        validateEvent();
    }
    public boolean validateFriendsDone(){
        return friendsDone;
    }
    public boolean validateNameDone(){
        return eventTimeSet && eventDateSet && eventNameSet && eventGuestsSet;
    }

    public void setEventNote(String note){
        newEventBuilder.setEventNote(note);
        Log.d(TAG, "event type" + note);
        eventNoteSet = true;
        validateEvent();
    }

    public boolean validateEvent(){
        boolean validEvent = false;
        //TODO: set booleans to new event builder info.
        if (newEventBuilder.getInvitedFriendsUserList() != null
                && newEventBuilder.getInvitedFriendsUserList().size() != 0 ){
            eventGuestsSet = true;
        }
        if (eventTimeSet && eventNameSet && eventDateSet && eventGuestsSet){
            Log.d(TAG, "create event: event valid - event guests set" + eventGuestsSet);
            Log.d(TAG, "create event: event valid - event date set" + eventDateSet);
            Log.d(TAG, "create event: event valid - event time set" + eventTimeSet);
            Log.d(TAG, "create event: event valid - event name set" + eventNameSet);

            validEvent = true;

        } else {
            Log.d(TAG, "create event: event not valid");
            Log.d(TAG, "create event: event not valid - event guests set" + eventGuestsSet);
            Log.d(TAG, "create event: event not valid - event date set" + eventDateSet);
            Log.d(TAG, "create event: event  not valid - event time set" + eventTimeSet);
            Log.d(TAG, "create event: event not  valid - event name set" + eventNameSet);
        }
        return validEvent;
    }

    public String setFinalizedEvent(){
        //TODO new event builder converter method method
        key = CurrentUserPost.getInstance().newEventKey();
        List<String> confirmedGuest = new ArrayList<>();
        confirmedGuest.add(currentUserID);
        newEvent.setEvent_id(newEventBuilder.getEventID());
        newEvent.setEvent_name(newEventBuilder.getEventName());
        newEvent.setEvent_note(newEventBuilder.getEventNote());
        newEvent.setEvent_time(newEventBuilder.getEventTime());
        newEvent.setEvent_date(newEventBuilder.getEventDate());
        newEvent.setVenue_type(newEventBuilder.getEventVenueType());
        newEvent.setEvent_note(newEventBuilder.getEventNote());
        newEvent.setInvited_guests(newEventBuilder.getInvitedGuests());
        newEvent.setEvent_organizer(currentUserID);
        newEvent.setConfirmed_guests(confirmedGuest);
        if (newEventBuilder.getInvitedFriendsUserList() != null){
            Log.d(TAG, "pub user list size: " + newEventBuilder.getInvitedFriendsUserList());
            eventGuestHashMap = new HashMap<>();
            eventGuestHashMap = newEventConverter.guestMapFromPubUser(newEventBuilder.getInvitedFriendsUserList(), false);
            EventGuest currentUserGuest = newEventConverter.eventGuestFromPUblicUser(CurrentUser.getInstance().getCurrentPublicUser(), true);
            eventGuestHashMap.put(currentUserID,currentUserGuest);
            newEventBuilder.setEventGuestMap(eventGuestHashMap);

        } else{
            Log.d(TAG, "pub user list is null");
        }
        newEvent.setEvent_guest_map(eventGuestHashMap);
        newEvent.setEvent_id(key);
        Log.d(TAG, "event type" + newEventBuilder.getEventVenueType());
        return key;
    }
}
