package com.example.c4q.capstone.userinterface.events;

import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.network.barzz.barzzmodel.Results;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.Venues;

import java.util.List;

/**
 * Created by amirahoxendine on 3/27/18.
 */

public interface VenueNetworkListener {
    void getFourSList(List<Venue> fourSVenues);
    void getFourSVenueIds(List<String> fourSquareVenueIds);


}
