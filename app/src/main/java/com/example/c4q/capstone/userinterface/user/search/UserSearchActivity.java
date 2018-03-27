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

import static com.example.c4q.capstone.utils.Constants.FRIEND_REQUEST;
import static com.example.c4q.capstone.utils.Constants.NOT_FRIENDS;
import static com.example.c4q.capstone.utils.Constants.PENDING;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.REQUEST_TYPE;
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
        callFirebaseAdapter();
    }


    private void callFirebaseAdapter() {

        FirebaseRecyclerAdapter<User, UserSearchViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<User, UserSearchViewHolder>(
                        User.class, R.layout.search_user_itemview, UserSearchViewHolder.class, searchUserDatabase) {

                    @Override
                    protected void populateViewHolder(final UserSearchViewHolder viewHolder, User model, final int position) {

                        viewHolder.setEmail(model.getEmail());

                        final String requestedFriendID = getRef(position).getKey();

                        viewHolder.requestFriendBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                if (!currentUserID.equals(requestedFriendID)) {
                                    /**
                                     * sends friend requests
                                     */
                                    sendFriendRequest(requestedFriendID, viewHolder.requestFriendBtn);

                                }

                                if (!currentUserID.equals(requestedFriendID) && viewHolder.requestFriendBtn.getText().equals("Pending")) {
                                    /**
                                     * cancels sent friend requests
                                     */
                                    cancelFriendRequest(requestedFriendID, viewHolder.requestFriendBtn);
                                }
                            }
                        });
                    }
                };

        searchResultsList.setAdapter(firebaseRecyclerAdapter);
    }

    public void sendFriendRequest(final String requestedFriendID, final Button requestFriendBtn) {
        HashMap<String, String> notificationData = new HashMap<>();
        notificationData.put(REQUEST_TYPE, FRIEND_REQUEST);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put(PUBLIC_USER + "/" + requestedFriendID + "/" + PENDING + "/" + currentUserID, notificationData);

        rootRef.updateChildren(requestMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    Toast.makeText(UserSearchActivity.this, "Error: Request not sent", Toast.LENGTH_SHORT).show();
                } else {

                    requestFriendBtn.setText("Pending");
                }
            }
        });
    }

    public void cancelFriendRequest(final String requestedFriendID, final Button requestFriendBtn) {
        AlertDialog.Builder builder = new AlertDialog.Builder(UserSearchActivity.this);

        builder.setTitle("Confirm");
        builder.setMessage("Cancel Friend Request?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                rootRef.child(PUBLIC_USER)
                        .child(requestedFriendID)
                        .child(PENDING)
                        .child(currentUserID)
                        .removeValue()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void v) {
                        requestFriendBtn.setText("Add Friend");
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
    }

}