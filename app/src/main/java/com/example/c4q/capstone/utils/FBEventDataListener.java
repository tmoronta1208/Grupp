package com.example.c4q.capstone.utils;

import com.example.c4q.capstone.database.events.Events;

import java.util.List;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public interface FBEventDataListener {
    void getAllEvents(List<Events> eventsList);
}
