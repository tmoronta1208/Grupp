package com.example.c4q.capstone.utils.currentuser;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;

import java.util.List;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public interface CurrentUserListener {
    void getPrivateUser(PrivateUser privateUser);
    void getPublicUser(PublicUser publicUser);
    void getUserFriends(List<PublicUser> publicUserList);
    void getUserEvents(List<Events> eventsList);
    void userHasFriends(Boolean hasFriends);
    void userHasEvents(Boolean hasEvents);
    void setUser(Boolean userInDB, String id);
    void getUserFriendIDs(List<String> friendIds);
    void getUserEventIDs(List<String> eventIds);

}
