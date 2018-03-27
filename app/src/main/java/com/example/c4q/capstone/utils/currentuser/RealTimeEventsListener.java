package com.example.c4q.capstone.utils.currentuser;

import android.widget.LinearLayout;

import com.example.c4q.capstone.database.events.Events;

import java.util.List;

/**
 * Created by amirahoxendine on 3/26/18.
 */

public interface RealTimeEventsListener {
    void getRealTimeEvents(List<Events> eventList);
}
