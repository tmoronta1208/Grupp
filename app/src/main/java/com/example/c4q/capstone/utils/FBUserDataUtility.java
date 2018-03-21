package com.example.c4q.capstone.utils;

import android.util.Log;

import com.example.c4q.capstone.database.model.events.Events;
import com.example.c4q.capstone.database.model.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUserFriends;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.c4q.capstone.utils.Constants.PRIVATE_LOCATION;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FBUserDataUtility {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;

    private DatabaseReference firebaseDatabase;
    private DatabaseReference publicUserReference;
    private DatabaseReference userFriendsReference;

    public static FirebaseUser currentUser;

    public String currentUserID;
    PublicUser publicUser;
    PrivateUser privateUser;
    private static List<PublicUser> userfriendsPublicUserList;

    private static String TAG = "FB USER UTILITY: ";


    public FBUserDataUtility(){
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        publicUserReference = firebaseDatabase.child(PUBLIC_USER);
        userFriendsReference = firebaseDatabase.child(USER_FRIENDS);
        currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            currentUserID = currentUser.getUid();
        }
    }


    public void getPublicUser(final String userID, final FBUserDataListener userDataListener){
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                PublicUser user = dataSnapshot.child(userID).getValue(PublicUser.class);
                if (user != null) {

                    Log.w(TAG, "user first name" + user.getFirst_name());
                    userDataListener.getPublicUser(user);
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
        publicUserReference.addValueEventListener(userListener);
    }

    public void getCurrentUserFriends(final FBUserFriendsListener userFriendsListener){
        userfriendsPublicUserList = new ArrayList<>();

            getUserFriendKeys(currentUserID, new FBUserFriendsListener() {
                @Override
                public void getUserFriendIds(List<String> userFriendIds) {


                    for(String userFriend : userFriendIds) {
                        getPublicUser(userFriend, new FBUserDataListener() {
                            @Override
                            public void getUid(String userID) {

                            }

                            @Override
                            public void getPublicUser(PublicUser publicUser) {
                                userfriendsPublicUserList.add(publicUser);
                                userFriendsListener.getUserFriends(userfriendsPublicUserList);
                            }
                        });
                    }
                }

                @Override
                public void getUserFriends(List<PublicUser> userFriendsList) {
                }
            });


    }
    public void getUserFriendKeys(final String userID, final  FBUserFriendsListener userFriendsListener){

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> userFriendsList = new ArrayList<>();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String friendKey = ds.getValue().toString();

                    userFriendsList.add(friendKey);
                    Log.d(TAG, "friend key" + friendKey);
                }
                Log.d(TAG, "user friends list size" + userFriendsList.size());
                userFriendsListener.getUserFriendIds(userFriendsList);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w(TAG, "getFriendKeys:onCancelled", databaseError.toException());

            }
        };
       userFriendsReference.child(userID).addValueEventListener(userListener);
    }


    public void addUserFriends(final List<String> userFriendsList){
        PublicUserFriends friends = new PublicUserFriends(userFriendsList);
        Map<String, Object> userFriendsMap = new HashMap<>();
        userFriendsMap.put(currentUserID, userFriendsList);
       firebaseDatabase.child(USER_FRIENDS).updateChildren(userFriendsMap);
        Log.w(TAG, "add friends" + userFriendsList.size());
    }



}
