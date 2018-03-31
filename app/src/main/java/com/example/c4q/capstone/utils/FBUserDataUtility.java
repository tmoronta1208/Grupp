package com.example.c4q.capstone.utils;

import android.util.Log;

import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
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

import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FBUserDataUtility {
    private FirebaseDatabase mFirebaseDatabase;

    private DatabaseReference firebaseDatabase;
    private DatabaseReference publicUserReference;



    List<String> dummyUsers;


    private static String TAG = "FB USER UTILITY: ";


    public FBUserDataUtility() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase = mFirebaseDatabase.getReference();
        publicUserReference = firebaseDatabase.child(PUBLIC_USER);
    }


    /**ajoxe:
     * this method get a single public user from the datatbase*/

    public void getPublicUser(final String userID, final FBUserDataListener userDataListener){
        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                PublicUser user = dataSnapshot.child(userID).getValue(PublicUser.class);
                if (user != null) {

                    Log.w(TAG, "getPublicUser: user first name: " + user.getFirst_name());
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
        publicUserReference.addListenerForSingleValueEvent(userListener);
    }



    public void getListPublicUsers(final FBUserFriendsListener userFriendsListener){

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dummyUsers = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String dummyKey = ds.getKey();
                    dummyUsers.add(dummyKey);
                }
                userFriendsListener.getUserFriendIds(dummyUsers);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        publicUserReference.addListenerForSingleValueEvent(userListener);
    }

}
