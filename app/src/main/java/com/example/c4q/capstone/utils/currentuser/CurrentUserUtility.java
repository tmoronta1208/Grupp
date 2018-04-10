package com.example.c4q.capstone.utils.currentuser;

import android.util.Log;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
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
import static com.example.c4q.capstone.utils.Constants.EVENT_INVITATIONS;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_EVENT_LIST;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

/**
 * Created by ajoxe on 3/23/18.
 * This is a utility class used to query current user info from the database
 */

public class CurrentUserUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private static FirebaseAuth mAuth;
    private DatabaseReference firebaseDatabase;
    private DatabaseReference publicUserReference;
    private static DatabaseReference publicStaticUserReference;
    private DatabaseReference privateUserReference;
    private DatabaseReference userFriendsReference;
    private DatabaseReference userEventsReference;
    private DatabaseReference userEventsListReference;
    private DatabaseReference eventInviteListReference;
    private DatabaseReference eventsReference;
    private static FirebaseUser currentUser;

    public static String currentUserID;
    private static boolean currentUserExists;
    public boolean userHasFriends;
    public boolean userHasEvents;
    public boolean userHasPrivateProfile;
    public static boolean userHasPublicProfile;
    private List<String> userEventIDs = new ArrayList<>();
    private List<Events> userEventsList = new ArrayList<>();
    private List<String> userFriendIds = new ArrayList<>();
    private List<PublicUser> userFriendsPublicUserList = new ArrayList<>();
    HashMap<String, Events> eventsMap = new HashMap<>();

    private static final String TAG = "CURRENT USER UTILITY: ";
    CurrentUserListener currentUserListener;

    public CurrentUserUtility() {
        Log.d(TAG, "constructor called");
        setCurrentUserID();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        publicUserReference = firebaseDatabase.child(PUBLIC_USER);
        publicStaticUserReference = firebaseDatabase.child(PUBLIC_USER);
        privateUserReference = firebaseDatabase.child(PRIVATE_USER);
        userFriendsReference = firebaseDatabase.child(USER_FRIENDS);
        userEventsReference = firebaseDatabase.child("user_events");
        userEventsListReference = firebaseDatabase.child(USER_EVENT_LIST);
        eventInviteListReference = firebaseDatabase.child(EVENT_INVITATIONS);
        eventsReference = firebaseDatabase.child(EVENTS);
    }

    public void setListener(CurrentUserListener currentUserListener){
        this.currentUserListener = currentUserListener;

        if (currentUserExists) {
            currentUserListener.setUser(true, currentUserID);
            getCurrentPrivateUser();
            getCurrentPublicUser();
            getCurrentUserFriendIDs();
            getCurrentUserEventIds();
            getCurrentUserEventInviteMap();
            getCurrentUserEventMap();
            //getCurrentUserFriends();
            //getCurrentUserEvents();
        } else{
            currentUserListener.setUser(false, null);
        }
    }

    public static  String setCurrentUserID() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUserID = currentUser.getUid();
            Log.d(TAG, "user id " + currentUserID);
            currentUserExists = true;

            return currentUserID;

        } else {
            //currentUserListener.setUser(false, null);
            currentUserExists = false;
            return currentUserID;
        }
    }

    /**
     * ajoxe:
     * this method gets current private user (Private user object from db) user from the datatbase
     */

    public void getCurrentPrivateUser() {
        ValueEventListener privateUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (currentUserExists){
                    PrivateUser user = dataSnapshot.child(currentUserID).getValue(PrivateUser.class);
                    if (user != null) {
                        userHasPrivateProfile = true;
                        Log.d(TAG, "getPrivateUser: user first name: " + user.getFirst_name());
                        currentUserListener.getPrivateUser(user);
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
        privateUserReference.addListenerForSingleValueEvent(privateUserListener);
    }

    /**
     * ajoxe:
     * this method gets current private user (Private user object from db) user from the datatbase
     */

    public void getCurrentPublicUser() {
            ValueEventListener publicUserListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (currentUserExists){
                        PublicUser user = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
                        if (user != null) {
                            userHasPublicProfile = true;
                            Log.d(TAG, "getPublicUser: user first name: " + user.getFirst_name());
                            currentUserListener.getPublicUser(user);
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
            publicUserReference.addListenerForSingleValueEvent(publicUserListener);
    }

    public void getCurrentPublicUser(final PublicUserListener userListener) {
        ValueEventListener publicUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (currentUserExists){
                    PublicUser user = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
                    if (user != null) {
                        userHasPublicProfile = true;
                        Log.d(TAG, "getPublicUser with listener: user first name: " + user.getFirst_name());
                        //currentUserListener.getPublicUser(user);
                        userListener.publicUserExists(userHasPublicProfile);
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
        publicUserReference.addListenerForSingleValueEvent(publicUserListener);
    }

    public static void getCurrentPublicUserProfile() {
        ValueEventListener publicUserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (currentUserExists){
                    PublicUser user = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
                    if (user != null) {
                        userHasPublicProfile = true;
                        Log.d(TAG, "getPublicUser: user first name: " + user.getFirst_name());
                        //currentUserListener.getPublicUser(user);
                        //userListener.publicUserExists(userHasPublicProfile);
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
        publicStaticUserReference.addListenerForSingleValueEvent(publicUserListener);
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
//                    getCurrentUserFriends();
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
    public void getCurrentUserFriends() {
        Log.d(TAG, "get current user friends called :");

        publicUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    currentUserListener.getUserFriends(userFriendsPublicUserList);
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
                getCurrentUserEvents();
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

    public void getCurrentUserEvents() {
       /* Log.d(TAG, "get current user events called :");


        eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
                               currentUserListener.getUserEvents(userEventsList);
                           }

                        }

                    }
                    Log.d(TAG, "get current user events called :" + userEventsList.size());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }

    public void getRealTimeCurrentUserEvents(final RealTimeEventsListener listener) {
        Log.d(TAG, "get current user events called :");


        eventsReference.addListenerForSingleValueEvent(new ValueEventListener() {
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


    public void getCurrentUserEventMap(){
        userEventsListReference.child(currentUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    Map<String, UserEvent> userEventHashMap= new HashMap<>();

                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        UserEvent userEvent = ds.getValue(UserEvent.class);
                        userEventHashMap.put(userEvent.getEvent_id(), userEvent);
                    }
                    currentUserListener.getUserEventList(userEventHashMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getCurrentUserEventInviteMap(){
        eventInviteListReference.child(currentUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    Map<String, UserEvent> eventInviteHashMap = new HashMap<>();

                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        UserEvent userEvent = ds.getValue(UserEvent.class);
                        eventInviteHashMap.put(userEvent.getEvent_id(), userEvent);

                    }
                    currentUserListener.getUserEventList(eventInviteHashMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getSingleUserEventList(String userID, final UserEventListener userEventListener){
        userEventsListReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                   Map<String, UserEvent> userEventHashMap= new HashMap<>();

                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        UserEvent userEvent = ds.getValue(UserEvent.class);
                        userEventHashMap.put(userEvent.getEvent_id(), userEvent);
                    }
                    userEventListener.getUserEventList(userEventHashMap);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getSingleEventInviteList(String userID, final UserEventListener userEventListener){
        eventInviteListReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null){
                    Map<String, UserEvent> eventInviteHashMap = new HashMap<>();
                    if (eventInviteHashMap != null){

                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            UserEvent userEvent = ds.getValue(UserEvent.class);
                            eventInviteHashMap.put(userEvent.getEvent_id(), userEvent);
                            Log.d("curr user util", "event invites called");
                        }
                        userEventListener.getUserEventList(eventInviteHashMap);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
