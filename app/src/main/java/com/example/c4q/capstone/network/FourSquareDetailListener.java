package com.example.c4q.capstone.network;

import com.example.c4q.capstone.database.events.Venue;

/**
 * Created by amirahoxendine on 3/28/18.
 */

public interface FourSquareDetailListener {
    void getVenueDetail(Venue venueDetail);
}
