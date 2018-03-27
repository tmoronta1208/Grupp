package com.example.c4q.capstone.userinterface.alerts;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.c4q.capstone.utils.Constants.PENDING;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

public class PendingFriendRequestsActivity extends AppCompatActivity {
    private static final String TAG = "PendingFriendRequests";
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


                    }
                };

        pendingList.setAdapter(firebaseRecyclerAdapter);
    }
}
