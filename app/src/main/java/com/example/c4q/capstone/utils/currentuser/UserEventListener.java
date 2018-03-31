package com.example.c4q.capstone.utils.currentuser;

import com.example.c4q.capstone.database.events.UserEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public interface UserEventListener {
    void getUserEventList(Map<String, UserEvent> userEventMap);

}
