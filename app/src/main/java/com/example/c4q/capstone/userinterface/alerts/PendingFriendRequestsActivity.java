package com.example.c4q.capstone.userinterface.alerts;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
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

import static com.example.c4q.capstone.utils.Constants.PENDING;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

public class PendingFriendRequestsActivity extends AppCompatActivity {
    private static final String TAG = "PendingFriendRequests";
    List<String> currentUserFriendList = new ArrayList<>();
    List<String> pendingUserFriendList = new ArrayList<>();
    private RecyclerView pendingList;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference rootRef, pendingRequests;
    private FirebaseUser currentUser;
    private FirebaseAuth authentication;
    private String currentUserID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_friend_requests);

        authentication = FirebaseAuth.getInstance();
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();

        rootRef = FirebaseDatabase.getInstance().getReference();
        pendingRequests = rootRef.child(PUBLIC_USER).child(currentUserID).child(PENDING);

        pendingList = findViewById(R.id.pending_requests_rv);
        pendingList.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        pendingList.setLayoutManager(linearLayoutManager);


        callFirebaseAdapter();
    }

    private void callFirebaseAdapter() {

        FirebaseRecyclerAdapter<PublicUser, FriendRequestAlertViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<PublicUser, FriendRequestAlertViewHolder>(
                        PublicUser.class, R.layout.friend_request_itemview, FriendRequestAlertViewHolder.class, pendingRequests) {

                    @Override
                    protected void populateViewHolder(final FriendRequestAlertViewHolder viewHolder, PublicUser model, final int position) {
                        final String pendingRequestID = getRef(position).getKey();

                        viewHolder.setEmail(pendingRequestID);

                        viewHolder.acceptBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                acceptFriendRequest(pendingRequestID);
                            }
                        });

                        viewHolder.denyBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                denyFriendRequest(pendingRequestID);
                            }
                        });

                    }
                };

        pendingList.setAdapter(firebaseRecyclerAdapter);
    }

    private void denyFriendRequest(String pendingRequestID) {

        rootRef.child(PUBLIC_USER)
                .child(currentUserID)
                .child(PENDING)
                .child(pendingRequestID)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        Toast.makeText(PendingFriendRequestsActivity.this, "Denied Friend Request", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void acceptFriendRequest(final String pendingRequestID) {
        currentUserFriendList.add(pendingRequestID);
        pendingUserFriendList.add(currentUserID);

        rootRef.child(PUBLIC_USER)
                .child(currentUserID)
                .child(PENDING)
                .child(pendingRequestID)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {

                        Map<String, Object> friendMap = new HashMap<>();
                            friendMap.put(USER_FRIENDS + "/" + currentUserID, pendingUserFriendList);
                            friendMap.put(USER_FRIENDS + "/" + pendingRequestID, currentUserFriendList);


                        rootRef.updateChildren(friendMap, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError != null) {
                                    Toast.makeText(PendingFriendRequestsActivity.this, "Error: Request not sent", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(PendingFriendRequestsActivity.this, "Accepted Friend Request", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
    }

}
