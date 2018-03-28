package com.example.c4q.capstone.userinterface;

import com.example.c4q.capstone.utils.currentuser.CurrentUserFriendsListener;
import com.example.c4q.capstone.utils.currentuser.CurrentUserFriendsUtility;

import java.util.List;

/**
 * Created by amirahoxendine on 3/28/18.
 */

public class CurrentUserFriends {
    private CurrentUserFriendsListener utilityFriendsListener;
    private CurrentUserFriendsListener friendsListener;
    private CurrentUserFriendsUtility userFriendsUtility = new CurrentUserFriendsUtility();
    private List<String> friendEventIds;

    public CurrentUserFriends(CurrentUserFriendsListener friendsListener){
        this.friendsListener = friendsListener;
        setUserFriendsListener();
        setUserFriendsUtility();
    }

    private void setUserFriendsUtility(){
        userFriendsUtility.setListener(utilityFriendsListener);
    }

    private void setUserFriendsListener(){
        utilityFriendsListener = new CurrentUserFriendsListener() {
            @Override
            public void getFriendEventIds(List<String> eventIds) {
                friendEventIds = eventIds;
                friendsListener.getFriendEventIds(friendEventIds);

            }
        };
    }

    public void setFriendEventIds(String friendId){
        userFriendsUtility.getUserFriendsEventList(friendId);
    }
}
