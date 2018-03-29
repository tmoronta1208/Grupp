package com.example.c4q.capstone.userinterface.alerts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.FriendsList;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.Set;


import static com.example.c4q.capstone.utils.Constants.PENDING;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

public class PendingFriendRequestsActivity extends AppCompatActivity {
    private static final String TAG = "PendingFriendRequests";
    List<String> currentUserFriendList = new ArrayList<>();
    ArrayList<String> pendingUserFriendList = new ArrayList<>();
    private RecyclerView pendingListRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference rootRef, pendingRequests;
    private FirebaseUser currentUser;
    private FirebaseAuth authentication;
    private String currentUserID;
    private FriendsList friendsList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_friend_requests);

        authentication = FirebaseAuth.getInstance();
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();

        rootRef = FirebaseDatabase.getInstance().getReference();
        pendingRequests = rootRef.child(PUBLIC_USER).child(currentUserID).child(PENDING);

        pendingListRecyclerView = findViewById(R.id.pending_requests_rv);
        pendingListRecyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        pendingListRecyclerView.setLayoutManager(linearLayoutManager);
        /*rootRef.child(USER_FRIENDS)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        Toast.makeText(PendingFriendRequestsActivity.this, "Denied Friend Request", Toast.LENGTH_SHORT).show();
                    }
                });*/

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

        pendingListRecyclerView.setAdapter(firebaseRecyclerAdapter);
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

        rootRef.child(USER_FRIENDS).child(pendingRequestID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot != null){
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        pendingUserFriendList.add(ds.getValue().toString());
                    }
                    /*Set<String> pendingFriends = new HashSet<>();
                    pendingFriends.addAll(pendingUserFriendList);
                    pendingUserFriendList.clear();
                    pendingUserFriendList.addAll(pendingFriends);*/
                    if(!pendingUserFriendList.contains(currentUserID)){
                        pendingUserFriendList.add(currentUserID);
                        rootRef.child(USER_FRIENDS).child(pendingRequestID).setValue(pendingUserFriendList);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //        rootRef.child(PUBLIC_USER)
//                .child(currentUserID)
//                .child(PENDING)
//                .child(pendingRequestID)
//                .removeValue()
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void v) {
        if (CurrentUser.getInstance().isUserHasFriends()) {
            currentUserFriendList.addAll(CurrentUser.getInstance().getUserFriendIDList());
        }
        currentUserFriendList.add(pendingRequestID);
        Set<String> userFriends = new HashSet<>();
        userFriends.addAll(currentUserFriendList);
        currentUserFriendList.clear();
        currentUserFriendList.addAll(userFriends);
        rootRef.child(USER_FRIENDS).child(currentUserID).setValue(currentUserFriendList);

        /*Set<String> pendingFriends = new HashSet<>();
        pendingFriends.addAll(pendingUserFriendList);
        pendingUserFriendList.clear();
        pendingUserFriendList.addAll(pendingFriends);
        pendingUserFriendList.add(currentUserID);

        ;*/

    }

//                });
//    }

}
