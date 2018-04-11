package com.example.c4q.capstone.userinterface.events;

import android.util.Log;

import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.network.FourSquareDetailListener;
import com.example.c4q.capstone.network.NetworkUtility;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.utils.FBUserDataUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by amirahoxendine on 3/28/18.
 */

public class VenueNetworkUtility {
    FBUserDataUtility userDataUtility = new FBUserDataUtility();
    HashMap<String, HashMap<String, Venue>> venueMap = new HashMap<>();
    Set<String> finalVenueIdSet = new HashSet<>();
    private static VenueNetworkUtility venueNetworkUtility;
    public static final String TAG = "Venue Netwk Util";
    VenueNetworkListener venueNetworkListener;

    public static VenueNetworkUtility getVenueNetworkUtility() {
        return new VenueNetworkUtility();
    }

    public void setVenueNetworkListener(VenueNetworkListener networkListener){
        this.venueNetworkListener = networkListener;
    }



    public void getVoteListFromFourSquare(List<PublicUser> eventGuests){

        if(eventGuests != null){
            Log.d(TAG, "venue util eventGuests" + eventGuests.size());
            eventGuests.add(CurrentUser.getInstance().getCurrentPublicUser());
            String preferences = "lounge";
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
                        Log.d(TAG, "Venue Network Listener called");
                        Log.d(TAG, "list size" + fourSVenues.size());
                        Log.d(TAG, "user" + id);
                        HashMap<String, Venue> listMap = new HashMap<>();
                        for(Venue venue: fourSVenues){
                            listMap.put(venue.getVenue_id(), venue);
                        }

                        venueMap.put(id, listMap);
                        if (venueMap.size() == callCount){
                            Log.d(TAG, "ready to compare lists");
                            compareUserVenueLists();
                        }
                        Log.d(TAG, "not ready call count" + callCount);
                        Log.d(TAG, "not ready venue map size" + venueMap.size());

                    }

                    @Override
                    public void getFourSVenueIds(List<String> fourSquareVenueIds) {

                    }

                });
            }
        }
    }

    public void compareUserVenueLists(){
        //get keys for venue map
        List<String> userId = new ArrayList<>();
        userId.addAll(venueMap.keySet());
        //get first list in map
        HashMap<String, Venue> userVenueListMap = venueMap.get(userId.get(0));
        //get ids from first list
        List<String> userVenueIdsList = new ArrayList<>();
        userVenueIdsList.addAll(userVenueListMap.keySet());

        //add id from first list to set
        finalVenueIdSet.addAll(userVenueIdsList);
        //make a set for each list in venue map
        int compareCount = userId.size();
        for (String s: venueMap.keySet()){
            HashMap<String, Venue> venueListMap = venueMap.get(s);
            //get ids list
            List<String> venueIds = new ArrayList<>();
            venueIds.addAll(venueListMap.keySet());
            Set<String> venueSet = new HashSet<>();
            venueSet.addAll(venueIds);
            finalVenueIdSet.retainAll(venueSet);
            Log.d(TAG, "final set retain size: " + finalVenueIdSet.size());
            compareCount --;
            if (compareCount == 0){
                Log.d(TAG, "comparison complete");
                Log.d(TAG, "final set size: " + finalVenueIdSet.size());
                for (String k: finalVenueIdSet){
                    Log.d(TAG, "final set size: " + k);
                }


                List<String> finalVenueIdList = new ArrayList<>();
                for (String vd: finalVenueIdSet){
                    finalVenueIdList.add(vd);
                    if (finalVenueIdList.size() == 5){
                        break;
                    }
                }
                //finalVenueIdList.addAll(finalVenueIdSet);

                venueNetworkListener.getFourSVenueIds(finalVenueIdList);
                //getDetailedVenues(finalVenueIdList);
            }
        }

    }

    public void getDetailedVenues(List<String> venueIds, final FourSquareDetailListener detailListener){
        final HashMap<String, Venue> venueDetailMap = new HashMap<>();
        final int callCount = venueIds.size();
        if (venueIds != null){
            if (venueIds.size() != 0){
                for (String id: venueIds){
                    NetworkUtility.getNetworkUtility().getFourSquareDetail(id, new FourSquareDetailListener() {
                        @Override
                        public void getVenueDetail(Venue venueDetail) {
                            Log.d(TAG, "venue detail listener called: " + venueDetail.getVenue_name());
                            venueDetailMap.put(venueDetail.getVenue_id(), venueDetail);
                            if (venueDetailMap.size() == callCount){
                                detailListener.getVenueDetailList(venueDetailMap);
                            }
                        }

                        @Override
                        public void getVenueDetailList(HashMap<String, Venue> venueDetailMap) {

                        }


                    });
                }

            }
        }
    }
}
