package com.example.c4q.capstone.userinterface.user.search;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.c4q.capstone.utils.Constants.FRIENDS;
import static com.example.c4q.capstone.utils.Constants.FRIEND_REQUEST;
import static com.example.c4q.capstone.utils.Constants.NOT_FRIENDS;
import static com.example.c4q.capstone.utils.Constants.PENDING;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.REQUEST_SENT;
import static com.example.c4q.capstone.utils.Constants.REQUEST_TYPE;
import static com.example.c4q.capstone.utils.Constants.SENT;
import static com.example.c4q.capstone.utils.Constants.USER_SEARCH;

public class UserSearchActivity extends AppCompatActivity {
    private static final String TAG = "UserSearchActivity";
    private RecyclerView searchResultsList;
    private FirebaseAuth authentication;
    private DatabaseReference rootRef, searchUserDatabase;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseUser currentUser;
    private String currentState, currentUserID, currentUserEmail;
    private List<String> pendingFriendRequestsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        rootRef = FirebaseDatabase.getInstance().getReference();

        searchUserDatabase = rootRef.child(USER_SEARCH);

        linearLayoutManager = new LinearLayoutManager(this);

        authentication = FirebaseAuth.getInstance();
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();
        currentUserEmail = currentUser.getEmail();

        searchResultsList = findViewById(R.id.search_users_rv);
        searchResultsList.setHasFixedSize(true);
        searchResultsList.setLayoutManager(linearLayoutManager);

        currentState = NOT_FRIENDS;
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
                    protected void populateViewHolder(final UserSearchViewHolder viewHolder, User model, int position) {
                        viewHolder.setEmail(model.getEmail());

                        final String requestFriend = getRef(position).getKey();

                        viewHolder.requestFriendBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                sendRequest(requestFriend, viewHolder.requestFriendBtn);
                            }
                        });

                        /**
                         * Everything commented out below to prevent NullPointerException errors.
                         * will uncomment as data is made available in database
                         */

//                        viewHolder.setFirst_name(model.getFirst_name());
//                        viewHolder.setLast_name(model.getLast_name());
//                        viewHolder.setUsername(model.getUsername());
                    }
                };

        searchResultsList.setAdapter(firebaseRecyclerAdapter);
    }

    private void sendRequest(final String requestedID, final Button requestBtn) {

        /**
         * sends friend requests
         */

        if (currentState.equals(NOT_FRIENDS) && currentUserID != requestedID) {

            HashMap<String, String> notificationData = new HashMap<>();
            notificationData.put(REQUEST_TYPE, FRIEND_REQUEST);

            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put(PRIVATE_USER + "/" + requestedID + "/" + PENDING + "/" + currentUserID, notificationData);

            rootRef.updateChildren(requestMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        Toast.makeText(UserSearchActivity.this, "Error: Request not sent", Toast.LENGTH_SHORT).show();
                    } else {

                        requestBtn.setText("Pending");
                        currentState = REQUEST_SENT;
                        Log.d(TAG, "sendRequest: " + requestedID + " " + currentState);


                    }
                }
            });
        }

        /**
         * cancels sent friend requests
         */

        if (currentState.equals(REQUEST_SENT) && currentUserID != requestedID) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Confirm");
            builder.setMessage("Cancel Friend Request?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    rootRef.child(PRIVATE_USER).child(requestedID).child(PENDING).child(currentUserID).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void v) {
                            requestBtn.setText("Add Friend");
                            currentState = NOT_FRIENDS;
                        }
                    });

                    dialog.dismiss();
                }

            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();

//                friendReqDatabase.child(currentUserID).child(requestedID).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void v) {
//
//                    }
//                });
            Log.d(TAG, "sendRequest: " + requestedID + " " + currentState);

        }

    }

}