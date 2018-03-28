package com.example.c4q.capstone.userinterface.events;

import android.util.Log;

import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.network.NetworkUtility;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserDataUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 3/28/18.
 */

public class VenueVoteUtility {
    FBUserDataUtility userDataUtility = new FBUserDataUtility();
    HashMap<String, List<Venue>> venueMap = new HashMap<>();
    private static VenueVoteUtility venueVoteUtility;
    public static final String TAG = "Venue Vote Util";

    public static VenueVoteUtility getVenueVoteUtility() {
        return new VenueVoteUtility();
    }

    public static void setVenueVoteUtility(VenueVoteUtility venueVoteUtility) {
        VenueVoteUtility.venueVoteUtility = venueVoteUtility;
    }

    public void getVoteListFromFourSquare(List<PublicUser> eventGuests){
        if(eventGuests != null){
            eventGuests.add(CurrentUser.getInstance().getCurrentPublicUser());
            String preferences = "beer karaoke";
            final int callCount = eventGuests.size();
            for (PublicUser guest: eventGuests){

                final String id = guest.getUser_id();
                String zipCode = guest.getZip_code();
                int radius = guest.getRadius();
                radius = radius * 1609;
                String userRadius = String.valueOf(radius);
                Log.d(TAG, "user radius" + guest.getFirst_name() + " : " + userRadius);

                NetworkUtility.getNetworkUtility().getFourSQList(zipCode, userRadius, preferences, new VenueNetworkListener() {
                    @Override
                    public void getFourSList(List<Venue> fourSVenues) {
                        Log.d(TAG, "Venue Vote Listener called");
                        Log.d(TAG, "list size" + fourSVenues.size());
                        venueMap.put(id, fourSVenues);
                        if (venueMap.size() == callCount){
                            Log.d(TAG, "ready to compare lists");
                        }
                    }
                });
            }
        }


    }


}
