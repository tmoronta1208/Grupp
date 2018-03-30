package com.example.c4q.capstone.userinterface.events.createevent;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.Venue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public class VenueVoteUtility {
    public Events event;
    public boolean vote_complete;
    public Venue topVenue;
    public HashMap<String, Venue> venueIdMap;
    public HashMap<String, Integer> venueVoteCountMap;
    public List<String> venueIDList;
    public List<String> orderedVenueIdList;
    public int yayCount;

    public VenueVoteUtility(Events event) {
        this.event = event;
        vote_complete = checkVoteComplete();
        venueVoteCountMap = mapVenueVotes();
        venueIdMap = mapVenueIds();
        venueIDList = venueIdsToList();
        orderedVenueIdList = orderVenuesByVote();
    }

    public boolean checkVoteComplete() {
        List<EventGuest> guestList = new ArrayList<>();
        guestList.addAll(event.getEvent_guest_map().values());
        for (EventGuest guest : guestList) {
            if (!guest.hasVoted()) {
                return false;
            }
        }
        return true;
    }

    public int countVenueVote(Venue venue) {
        yayCount = 0;
        for (String user : venue.getVenue_vote().keySet()) {
            if (venue.getVenue_vote().get(user)) {
                yayCount++;
            }
        }
        return yayCount;
    }



    public HashMap<String, Venue> mapVenueIds(){
        for (Venue venue : event.getVenue_map().values()) {
            venueIdMap.put(venue.getVenue_id(), venue);
        }
        return venueIdMap;
    }
    public HashMap<String, Integer> mapVenueVotes(){
        for (Venue venue : event.getVenue_map().values()) {
            venueVoteCountMap.put(venue.getVenue_id(), countVenueVote(venue));
        }
        return venueVoteCountMap;
    }

    public List<String> venueIdsToList(){
        venueIDList = new ArrayList<>();
        for(String venueId: venueIdMap.keySet()){
            venueIDList.add(venueId);
        }
        return venueIDList;
    }

    public List<String>orderVenuesByVote() {
       orderedVenueIdList = new ArrayList<>();
        for (int i = venueIDList.size(); i>0; i--){
            int higestVote = venueVoteCountMap.get(venueIDList.get(i));
            String topVenueId = venueIDList.get(i);

            for (int k = 0; k<i; k++){
                int currentVote = venueVoteCountMap.get(venueIDList.get(k));
                String currentVenueId = venueIDList.get(k);
                if(currentVote > higestVote){
                    higestVote = currentVote;
                    topVenueId = currentVenueId;
                }
            }
            orderedVenueIdList.add(topVenueId);
        }
       return orderedVenueIdList;
    }
}
