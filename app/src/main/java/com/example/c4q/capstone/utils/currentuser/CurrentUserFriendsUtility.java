package com.example.c4q.capstone.utils.currentuser;

import android.util.Log;
import android.widget.DatePicker;

import com.example.c4q.capstone.userinterface.CurrentUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.EVENTS;
import static com.example.c4q.capstone.utils.Constants.EVENT_INVITATIONS;
import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_EVENTS;

/**
 * Created by amirahoxendine on 3/28/18.
 */

public class CurrentUserFriendsUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference userEventsReference;
    private DatabaseReference eventInvitesReference;
    private static final String TAG = "FriendsUtility";
    private CurrentUserFriendsListener userFriendsListener;

    public CurrentUserFriendsUtility(){
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        userEventsReference = firebaseDatabase.child(USER_EVENTS);
        eventInvitesReference = firebaseDatabase.child(EVENT_INVITATIONS);
    }

    public void setListener(CurrentUserFriendsListener userFriendsListener){
        this.userFriendsListener = userFriendsListener;
    }

    public void getUserFriendsEventList(String friendID){
        userEventsReference.child(friendID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> friendEventIds = new ArrayList<>();
                if(dataSnapshot != null){
                    long childrenCount = dataSnapshot.getChildrenCount();
                    if (childrenCount != 0){
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            if (ds.getValue() !=null){
                                friendEventIds.add(ds.getValue().toString());
                            }
                        }
                        userFriendsListener.getFriendEventIds(friendEventIds);
                        Log.d(TAG, "friends event list size: " + friendEventIds.size());
                    } else {
                        userFriendsListener.getFriendEventIds(friendEventIds);
                        Log.d(TAG, "friends event list size: " + friendEventIds.size());
                    }
                } else{
                    userFriendsListener.getFriendEventIds(friendEventIds);
                    Log.d(TAG, "friends event list size: " + friendEventIds.size());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
