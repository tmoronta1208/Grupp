package com.example.c4q.capstone.userinterface.events.createevent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.TempUserActivity;
import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.userinterface.alerts.InviteNotifications;
import com.example.c4q.capstone.userinterface.user.MainProfileFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public class VenueVoteUtility {
    public Events event;
    public boolean vote_complete;
    private Context context;
    public Venue topVenue;
    public HashMap<String, Venue> venueIdMap;
    public HashMap<String, Integer> venueVoteCountMap;
    public List<String> venueIDList;
    public List<String> orderedVenueIdList;
    public List<Venue> orderedVenueList;
    public int yayCount;
    public static final String TAG = "VOTE UTIL";

    // add context for event

    public VenueVoteUtility() {

    }

    public VenueVoteUtility(Events event, Context context) {
        this.event = event;
        this.context = context;
        vote_complete = checkVoteComplete();
        venueVoteCountMap = mapVenueVotes();
        venueIdMap = mapVenueIds();
        venueIDList = venueIdsToList();
        orderedVenueIdList = orderVenuesByVote();

    }

    private boolean checkVoteComplete() {
        List<EventGuest> guestList = new ArrayList<>();
        guestList.addAll(event.getEvent_guest_map().values());
        for (EventGuest guest : guestList) {
            if (!guest.isVoted()) {
                return false;
            }


        }

        return true;
    }


    private int countVenueVote(Venue venue) {
        yayCount = 0;
        if (venue.getVenue_vote() != null) {
            for (String user : venue.getVenue_vote().keySet()) {
                if (venue.getVenue_vote().get(user)) {
                    yayCount++;
                }
            }
        }
        return yayCount;
    }

    private HashMap<String, Venue> mapVenueIds() {
        venueIdMap = new HashMap<>();
        for (Venue venue : event.getVenue_map().values()) {
            venueIdMap.put(venue.getVenue_id(), venue);
        }
        return venueIdMap;
    }

    private HashMap<String, Integer> mapVenueVotes() {
        venueVoteCountMap = new HashMap<>();
        for (Venue venue : event.getVenue_map().values()) {
            venueVoteCountMap.put(venue.getVenue_id(), countVenueVote(venue));
        }
        return venueVoteCountMap;
    }

    private List<String> venueIdsToList() {
        venueIDList = new ArrayList<>();
        venueIDList.addAll(venueIdMap.keySet());
        return venueIDList;
    }

    private List<String> orderVenuesByVote() {
        orderedVenueIdList = new ArrayList<String>(venueIDList.size());
        orderedVenueList = new ArrayList<Venue>(venueIDList.size());
        for (int i = 0; i < venueIDList.size(); i++) {
            int highestVote = venueVoteCountMap.get(venueIDList.get(i));
            String topVenueId = venueIDList.get(i);
            for (int k = venueIDList.size() - 1; k > i; k--) {
                int currentVote = venueVoteCountMap.get(venueIDList.get(k));
                String currentVenueId = venueIDList.get(k);
                if (currentVote >= highestVote) {
                    highestVote = currentVote;
                    topVenueId = currentVenueId;
                }
            }
            //Log.d(TAG, "TOP VENUE ID: " + topVenueId);
            //Log.d(TAG, "TOP VENUE ID: " + venueIdMap.get(topVenueId).getVenue_name());
            venueIDList.set(i, venueIDList.set(venueIDList.indexOf(topVenueId), venueIDList.get(i)));
        }
        for (int j = 0; j < venueIDList.size(); j++) {
            orderedVenueList.add(venueIdMap.get(venueIDList.get(j)));
            orderedVenueIdList.add(venueIDList.get(j));
        }
        //
        // orderedVenueList.set(i, venueIdMap.get(topVenueId));
        topVenue = orderedVenueList.get(0);
        return orderedVenueIdList;
    }


}

