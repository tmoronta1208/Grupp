package com.example.c4q.capstone.network;

import com.example.c4q.capstone.database.events.Venue;

import java.util.HashMap;
import java.util.List;

/**
 * Created by amirahoxendine on 3/28/18.
 */

public interface FourSquareDetailListener {
    void getVenueDetail(Venue venueDetail);
    void getVenueDetailList(HashMap<String, Venue> venueDetailMap);
}
