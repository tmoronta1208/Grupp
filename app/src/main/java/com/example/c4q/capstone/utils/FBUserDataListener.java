package com.example.c4q.capstone.utils;

import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public interface FBUserDataListener {
    void getUid(String userID);
    void getPublicUser(PublicUser publicUser);
}
