package com.example.c4q.capstone.utils.currentuser;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private static FirebaseUser currentUser;

    public String currentUserID;
    public boolean currentUserExists;
    public boolean userHasFriends;
    public boolean userHasEvents;
    public boolean userHasPrivateProfile;
    public boolean userHasPublicProfile;
    private List<String> userEventIDs = new ArrayList<>();
    private List<Events> userEventsList = new ArrayList<>();
    private List<String> userFriendIds = new ArrayList<>();
    private List<PublicUser> userFriendsPublicUserList = new ArrayList<>();
    HashMap<String, Events> eventsMap = new HashMap<>();

    private static final String TAG = "CURRENT USER UTILITY: ";
    CurrentUserListener currentUserListener;

    public CurrentUserUtility() {
        Log.d(TAG, "constructor called");

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        publicUserReference = firebaseDatabase.child(PUBLIC_USER);
        privateUserReference = firebaseDatabase.child(PRIVATE_USER);
        userFriendsReference = firebaseDatabase.child(USER_FRIENDS);
        userEventsReference = firebaseDatabase.child("user_events");
        eventsReference = firebaseDatabase.child(EVENTS);
    }

    public void setListener(CurrentUserListener currentUserListener){
        this.currentUserListener = currentUserListener;
        currentUserExists = setCurrentUserID();
        if (currentUserExists) {
            /**
             * this needs to be called after sign in
             */
            getCurrentUserFriendIDs();
            getCurrentUserEventIds();
        }
    }

    private boolean setCurrentUserID() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUserID = currentUser.getUid();
            currentUserListener.setUser(true, currentUserID);
            return true;
        } else {
            currentUserListener.setUser(false, null);
            return false;
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
                if (currentUserExists){
                    PrivateUser user = dataSnapshot.child(currentUserID).getValue(PrivateUser.class);
                    if (user != null) {
                        userHasPrivateProfile = true;
                        Log.d(TAG, "getPrivateUser: user first name: " + user.getFirst_name());
                        listener.getPrivateUser(user);
                    } else {
                        userHasPrivateProfile = false;
                    }
                    Log.d(TAG, "getPrivateUser: user has private profile: " + userHasPrivateProfile);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
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
                    if (currentUserExists){
                        PublicUser user = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
                        if (user != null) {
                            userHasPublicProfile = true;
                            Log.d(TAG, "getPublicUser: user first name: " + user.getFirst_name());
                            listener.getPublicUser(user);
                        } else{
                            userHasPublicProfile = false;
                        }
                        Log.d(TAG, "getPublicUser: user has public profile " + userHasPublicProfile);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Getting Post failed, log a message
                    Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
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
        if(!userHasFriends){
            currentUserListener.userHasFriends(userHasFriends);
        }
            userFriendsReference.child(currentUserID).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    if (dataSnapshot.getValue() != null) {
                        userHasFriends = true;
                        String friendID = dataSnapshot.getValue().toString();
                        Log.d(TAG, "getCurrentUserFriendIds: id " + friendID);
                        userFriendIds.add(friendID);
                        Log.d(TAG, "getCurrentUserFriendIds: list size " + userFriendIds.size());
                    } else{
                        userHasFriends = false;
                    }
                    Log.d(TAG, "getCurrentUserFriendIds: user has friends:" + userHasFriends);
                    currentUserListener.userHasFriends(userHasFriends);
                    currentUserListener.getUserFriendIDs(userFriendIds);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue() != null){
                        String friendIdRemoved = dataSnapshot.getValue().toString();
                        userFriendIds.remove(friendIdRemoved);
                        Log.d(TAG, "getCurrentUserFriendIds: friend removed ");
                        Log.d(TAG, "getCurrentUserFriendIds: removed id " + friendIdRemoved);
                        if (userFriendIds.size() == 0){
                            userHasFriends = false;
                            Log.d(TAG, "getCurrentUserFriendIds: user has no friends ");
                        }
                    }
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

        publicUserReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userFriendsPublicUserList = new ArrayList<>();
                if (userHasFriends && userFriendIds.size() != 0){
                    for (final String friendID : userFriendIds) {
                        if (dataSnapshot.child(friendID).getValue(PublicUser.class) != null){
                            PublicUser user = dataSnapshot.child(friendID).getValue(PublicUser.class);
                            userFriendsPublicUserList.add(user);
                        }
                    }
                    Log.d(TAG, "get current user friends called : list size = " + userFriendsPublicUserList.size());
                    listener.getUserFriends(userFriendsPublicUserList);
                }
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
        if(!userHasEvents){
            currentUserListener.userHasEvents(userHasEvents);
        }

        userEventsReference.child(currentUserID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getValue() != null) {
                    userHasEvents = true;
                    String eventId = dataSnapshot.getValue().toString();

                    Log.d(TAG, "getCurrentUserEventIds: id " + eventId);
                    userEventIDs.add(eventId);
                    Log.d(TAG, "getCurrentUserEventIds: list size " + userEventIDs.size());
                } else {
                    userHasEvents = false;
                }
                Log.d(TAG, "getCurrentUserEventIds: user has events: " + userHasEvents);
                currentUserListener.userHasEvents(userHasEvents);
                currentUserListener.getUserEventIDs(userEventIDs);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null){
                    String eventRemoved = dataSnapshot.getValue().toString();
                    userEventIDs.remove(eventRemoved);
                    Log.d(TAG, "getCurrentUserEventIds: event removed ");
                    Log.d(TAG, "getCurrentUserEventIds: removed id: " + eventRemoved);
                    if (userEventIDs.size() == 0){
                        userHasEvents = false;
                        Log.d(TAG, "getCurrentUserEventIds: user has no events ");
                    }
                }
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


        eventsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userEventsList = new ArrayList<>();
                if (userHasEvents && userEventIDs.size() != 0){
                    for (final String eventID : userEventIDs) {
                        if (dataSnapshot.child(eventID).getValue(Events.class) != null){
                            Events event = dataSnapshot.child(eventID).getValue(Events.class);
                           if (event != null){
                               eventsMap.put(event.getEvent_id(), event);
                               userEventsList = new ArrayList<>();
                               for (String s :eventsMap.keySet()){
                                   userEventsList.add(eventsMap.get(s));
                               }
                               //userEventsList.add(event);
                               listener.getUserEvents(userEventsList);
                           }

                        }

                    }
                    Log.d(TAG, "get current user events called :" + userEventsList.size());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getRealTimeCurrentUserEvents(final RealTimeEventsListener listener) {
        Log.d(TAG, "get current user events called :");


        eventsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userEventsList = new ArrayList<>();
                if (userHasEvents && userEventIDs.size() != 0){
                    for (final String eventID : userEventIDs) {
                        if (dataSnapshot.child(eventID).getValue(Events.class) != null){
                            Events event = dataSnapshot.child(eventID).getValue(Events.class);
                            if (event != null){
                                eventsMap.put(event.getEvent_id(), event);
                                userEventsList = new ArrayList<>();
                                for (String s :eventsMap.keySet()){
                                    userEventsList.add(eventsMap.get(s));
                                }
                                //userEventsList.add(event);
                                listener.getRealTimeEvents(userEventsList);
                                Log.d(TAG, "get realtime events called :" + userEventsList.size());
                            }

                        }

                    }
                }
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
