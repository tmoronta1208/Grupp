package com.example.c4q.capstone.userinterface.events;

import com.example.c4q.capstone.network.barzz.barzzmodel.Results;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.Venues;

import java.util.List;

/**
 * Created by amirahoxendine on 3/27/18.
 */

public interface VenueNetworkListener {
    void getBarzList(List<Results> venueList);
}
