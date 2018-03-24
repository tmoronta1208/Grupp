package com.example.c4q.capstone.utils.currentuser;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserFriends;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserFriendsListener;
import com.example.c4q.capstone.utils.currentuser.CurrentUserListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.c4q.capstone.utils.Constants.EVENTS;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

/**
 * Created by ajoxe on 3/23/18.
 * This is a utility class used to query current user info from the database
 */

public class CurrentUserUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;

    private DatabaseReference firebaseDatabase;
    private DatabaseReference publicUserReference;
    private DatabaseReference privateUserReference;
    private DatabaseReference userFriendsReference;
    private DatabaseReference userEventsReference;
    private DatabaseReference eventsReference;

    public static FirebaseUser currentUser;

    public String currentUserID;
    //PublicUserFriends friendIds;

    List<String> userEventIDs = new ArrayList<>();
    List<Events> userEventsList;
    private static List<String> userFriendIds = new ArrayList<>();
    private static List<PublicUser> userfriendsPublicUserList;

    private static String TAG = "CURRENT USER UTILITY: ";


    public CurrentUserUtility() {
        setCurrentUserID();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        publicUserReference = firebaseDatabase.child(PUBLIC_USER);
        privateUserReference = firebaseDatabase.child(PRIVATE_USER);
        userFriendsReference = firebaseDatabase.child(USER_FRIENDS);
        userEventsReference = firebaseDatabase.child("user_events");
        eventsReference = firebaseDatabase.child(EVENTS);
        if (currentUser != null) {
            /**
             * this needs to be called after sign in
             */
            getCurrentUserFriendIDs();
            getCurrentUserEventIds();

        }
    }

    private void setCurrentUserID() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUserID = currentUser.getUid();
        }
    }

    /**
     * ajoxe:
     * this method gets current private user (Private user object from db) user from the datatbase
     */

    public void getCurrentPrivateUser(final CurrentUserListener listener) {
        ValueEventListener privateUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                PrivateUser user = dataSnapshot.child(currentUserID).getValue(PrivateUser.class);
                if (user != null) {

                    Log.w(TAG, "getPublicUser: user first name: " + user.getFirst_name());
                    listener.getPrivateUser(user);
                }
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        privateUserReference.addValueEventListener(privateUserListener);
    }

    /**
     * ajoxe:
     * this method gets current private user (Private user object from db) user from the datatbase
     */

    public void getCurrentPublicUser(final CurrentUserListener listener) {
        ValueEventListener publicUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                PublicUser user = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
                if (user != null) {
                    Log.w(TAG, "getPublicUser: user first name: " + user.getFirst_name());
                    listener.getPublicUser(user);
                }
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        publicUserReference.addValueEventListener(publicUserListener);
    }

    /**
     * ajoxe:
     * this method gets current users friend IDs
     */
    public void getCurrentUserFriendIDs() {

        userFriendsReference.child(currentUserID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                userFriendIds.add(dataSnapshot.getValue().toString());
                Log.d(TAG, "getCurrentUserFriendIds: list size " + userFriendIds.size());
                String id = dataSnapshot.getValue().toString();
                Log.d(TAG, "getCurrentUserFriendIds: id " + id);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                userFriendIds.remove(dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * ajoxe:
     * this method gets current users friends as public user objects
     */
    public void getCurrentUserFriends(final CurrentUserListener listener) {
        Log.d(TAG, "get current user friends called :");
        userfriendsPublicUserList = new ArrayList<>();

        publicUserReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final String friendID : userFriendIds) {
                    PublicUser user = dataSnapshot.child(friendID).getValue(PublicUser.class);
                    userfriendsPublicUserList.add(user);
                }
                Log.d(TAG, "get current user friends called :" + userfriendsPublicUserList.size());
                listener.getUserFriends(userfriendsPublicUserList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * ajoxe:
     * this method gets current users events IDs
     */

    public void getCurrentUserEventIds() {

        userEventsReference.child(currentUserID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                userEventIDs.add(dataSnapshot.getValue().toString());
                Log.d(TAG, "getCurrentUserEventIds: list size " + userEventIDs.size());
                String id = dataSnapshot.getValue().toString();
                Log.d(TAG, "getCurrentUserEventIds: id " + id);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                userEventIDs.remove(dataSnapshot.getValue().toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * ajoxe:
     * this method gets current users events as objects
     */

    public void getCurrentUserEvents(final CurrentUserListener listener) {
        Log.d(TAG, "get current user events called :");
        userEventsList = new ArrayList<>();

        eventsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final String eventID : userEventIDs) {
                    Events event = dataSnapshot.child(eventID).getValue(Events.class);
                    userEventsList.add(event);
                }
                Log.d(TAG, "get current user events called :" + userEventsList.size());
                listener.getUserEvents(userEventsList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * ajoxe:
     * add methods : not complete
     */
    public void addUserFriends(final List<String> userFriendsList) {
        Map<String, Object> userFriendsMap = new HashMap<>();
        userFriendsMap.put(currentUserID, userFriendsList);
        firebaseDatabase.child(USER_FRIENDS).updateChildren(userFriendsMap);
        Log.w(TAG, "addUserFriends: " + userFriendsList.size());
    }

    /**
     * ajoxe:
     * this method adds a single friend to users friend list.
     * Not tested
     */
    public void addSingleUserFriend(final String friendID) {
        Map<String, Object> userFriendsMap = new HashMap<>();
        userFriendsMap.put(currentUserID, friendID);
        firebaseDatabase.child(USER_FRIENDS).updateChildren(userFriendsMap);
        Log.w(TAG, "add friends" + friendID);
    }

}
