package com.example.c4q.capstone.userinterface.events;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public interface EventDataListener {
    void getEvent(Events event);
}
