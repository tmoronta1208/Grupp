package com.example.c4q.capstone.userinterface.user.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.c4q.capstone.utils.Constants.NOTIFICATIONS;
import static com.example.c4q.capstone.utils.Constants.USER_SEARCH;

public class UserSearchActivity extends AppCompatActivity {
    private RecyclerView searchResultsList;
    private DatabaseReference rootRef, newNotification, searchUserDatabase, friendReqDatabase, friendDatabase, notificationDatabase, usersDatabase;
    private LinearLayoutManager linearLayoutManager;
    FirebaseUser currentUser;
    String currentState;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        searchUserDatabase = FirebaseDatabase.getInstance().getReference().child(USER_SEARCH);

        linearLayoutManager = new LinearLayoutManager(this);

        searchResultsList = findViewById(R.id.search_users_rv);
        searchResultsList.setHasFixedSize(true);
        searchResultsList.setLayoutManager(linearLayoutManager);

        currentState = "not_friends";

    }

    @Override
    protected void onStart() {
        super.onStart();
        callFirebaseAdapter();
    }

    private void callFirebaseAdapter() {
        FirebaseRecyclerAdapter<User, UserSearchViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<User, UserSearchViewHolder>(
                        User.class,
                        R.layout.search_user_itemview,
                        UserSearchViewHolder.class,
                        searchUserDatabase
                ) {
                    @Override
                    protected void populateViewHolder(UserSearchViewHolder viewHolder, User model, int position) {
                        viewHolder.setEmail(model.getEmail());

                       final String user_id = getRef(position).getKey();

                        viewHolder.view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                sendRequest(user_id);
                            }
                        });


                        /**
                         * Everything commented out below to prevent NullPointerException errors.
                         * will uncomment as data is made available in database
                         */

                        //viewHolder.setFirst_name(model.getFirst_name());
                        //viewHolder.setLast_name(model.getLast_name());
                        //viewHolder.setUsername(model.getUsername());
                    }
                };

        searchResultsList.setAdapter(firebaseRecyclerAdapter);
    }

    private void sendRequest(String user_id) {
        if (currentState.equals("not_friends")) {

            newNotification = rootRef.child(NOTIFICATIONS).child(user_id).push();
            String newNotificationId = newNotification.getKey();

            HashMap<String, String> notificationData = new HashMap<>();
            notificationData.put("from", currentUser.getUid());
            notificationData.put("type", "request");

            Map requestMap = new HashMap();
            requestMap.put("Friend_req/" + currentUser.getUid() + "/" + user_id + "/request_type", "sent");
            requestMap.put("Friend_req/" + user_id + "/" + currentUser.getUid() + "/request_type", "received");
            requestMap.put("notifications/" + user_id + "/" + newNotificationId, notificationData);

            rootRef.updateChildren(requestMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {

                    if (databaseError != null) {

                        Toast.makeText(UserSearchActivity.this, "There was some error in sending request", Toast.LENGTH_SHORT).show();

                    } else {

                        currentState = "req_sent";
//                        profileSendReqBtn.setText("Cancel Friend Request");

                    }

//                    profileSendReqBtn.setEnabled(true);


                }
            });

        }


    }
}
