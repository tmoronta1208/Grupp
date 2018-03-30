package com.example.c4q.capstone.userinterface.events.createevent;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;

import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public class NewEventConverter {


    public NewEventConverter (){

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

    public EventGuest eventGuestFromPUblicUser(PublicUser publicUser, Boolean confirmed){
        EventGuest eventGuest = new EventGuest();
        eventGuest.setConfirmed_guest(confirmed);
        eventGuest.setUser_id(publicUser.getUser_id());
        eventGuest.setUser_firstname(publicUser.getFirst_name());
        eventGuest.setUser_lastname(publicUser.getLast_name());
        eventGuest.setZip_code(publicUser.getZip_code());
        eventGuest.setUser_preferences(publicUser.getUser_preferences());
        eventGuest.setRadius(publicUser.getRadius());
        eventGuest.setUser_icon(publicUser.getUser_icon());
        eventGuest.setVoted(false);
        return eventGuest;
    }

    public HashMap<String, EventGuest> guestMapFromPubUser(List<PublicUser> guestList, boolean confirmed){
        HashMap<String, EventGuest> guestHashMap = new HashMap<>();
        for (PublicUser user: guestList){
            EventGuest eventGuest = eventGuestFromPUblicUser(user, confirmed);
            guestHashMap.put(eventGuest.getUser_id(), eventGuest);
        }
        return  guestHashMap;
    }
}
