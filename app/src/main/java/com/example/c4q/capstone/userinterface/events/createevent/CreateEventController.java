package com.example.c4q.capstone.userinterface.events.createevent;

import android.util.Log;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.network.FourSquareDetailListener;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.VenueNetworkListener;
import com.example.c4q.capstone.userinterface.events.VenueNetworkUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 4/3/18.
 * This class controls sending new events to the database, getting lists of venues and updating new events.
 */

public class CreateEventController {
    private static String TAG = "NEW_EVENT_CONT: ";
    NewEventConverter newEventConverter = new NewEventConverter();
    String key;
    HashMap<String, EventGuest> eventGuestHashMap;
    String currentUserID = CurrentUser.userID;
    Events newEvent = new Events();
    NewEventBuilder eventBuilder = NewEventBuilder.getInstance();
    NewEventPresenter newEventListener;

    public CreateEventController(NewEventPresenter newEventListener){
        this.newEventListener = newEventListener;

    }

    public void sendEventToFireBase(boolean validEvent, EventFragmentListener listener){
        Log.d(TAG, " send event to fire base called ");
        Log.d(TAG, " event validation: " + validEvent);
        if(validEvent){
            Log.d(TAG, " event name: " + eventBuilder.getEventName());
            Log.d(TAG, " event date: " + eventBuilder.getEventDate());
            Log.d(TAG, " event time: " + eventBuilder.getEventTime());
            Log.d(TAG, " event invited list size: " + eventBuilder.getInvitedFriendsUserList().size());
            key = setFinalizedEvent();
            Log.d(TAG, "event key : " + key);
            listener.getEventIdKEy(key);
            List<PublicUser> eventGuests = eventBuilder.getInvitedFriendsUserList();
            makeNetworkCall(eventGuests);
            eventBuilder.destroyInstance();
            Log.d(TAG, "post event called" + eventGuests);
            CurrentUserPost.getInstance().postNewEvent(key, newEvent);
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
            //CurrentUserPost.getInstance().postEventToUserEventList(key, guest, userEvent);
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
                                        newEventListener.alertShowEventReady(venueDetailMap.size());
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
        Log.d(TAG, "event guests" + eventGuests.get(0).getFirst_name());

        venueNetworkUtility.getVoteListFromFourSquare(eventGuests);
    }

    public String setFinalizedEvent(){
        //TODO new event builder converter method method
        key = CurrentUserPost.getInstance().newEventKey();
        List<String> confirmedGuest = new ArrayList<>();
        confirmedGuest.add(currentUserID);
        newEvent.setEvent_id(eventBuilder.getEventID());
        newEvent.setEvent_name(eventBuilder.getEventName());
        newEvent.setEvent_note(eventBuilder.getEventNote());
        newEvent.setEvent_time(eventBuilder.getEventTime());
        newEvent.setEvent_date(eventBuilder.getEventDate());
        //newEvent.setVenue_type(eventBuilder.getEventVenueType());
        newEvent.setEvent_note(eventBuilder.getEventNote());
        newEvent.setInvited_guests(eventBuilder.getInvitedGuests());
        newEvent.setEvent_organizer(currentUserID);
        newEvent.setConfirmed_guests(confirmedGuest);
        if (eventBuilder.getInvitedFriendsUserList() != null){
            Log.d(TAG, "pub user list size: " + eventBuilder.getInvitedFriendsUserList().size());
            Log.d(TAG, "pub user list first name: " + eventBuilder.getInvitedFriendsUserList().get(0).getFirst_name());
            eventGuestHashMap = new HashMap<>();
            eventGuestHashMap = newEventConverter.guestMapFromPubUser(eventBuilder.getInvitedFriendsUserList(), false);
            EventGuest currentUserGuest = newEventConverter.eventGuestFromPUblicUser(CurrentUser.getInstance().getCurrentPublicUser(), true);
            eventGuestHashMap.put(currentUserID,currentUserGuest);
            eventBuilder.setEventGuestMap(eventGuestHashMap);

        } else{
            Log.d(TAG, "pub user list is null");
        }
        newEvent.setEvent_guest_map(eventGuestHashMap);
        newEvent.setEvent_id(key);
        //Log.d(TAG, "event type" + eventBuilder.getEventVenueType());
        return key;
    }
}
