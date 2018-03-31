package com.example.c4q.capstone;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

public class TempFriendListActivity extends AppCompatActivity {
    private static final String TAG = "TempFriendList";
    private RecyclerView friendListRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference rootRef, friendsRef, userIDRef;
    private TempFriendListAdapter tempFriendListAdapter;
    private String currentUserID;
    private List<String> friendList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        currentUserID = CurrentUser.getInstance().getUserID();

        rootRef = FirebaseDatabase.getInstance().getReference();
        userIDRef = rootRef.child(USER_FRIENDS).child(currentUserID);

        friendListRecyclerView = findViewById(R.id.friendlistRv);
        friendListRecyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(TempFriendListActivity.this);
        friendListRecyclerView.setLayoutManager(linearLayoutManager);

        tempFriendListAdapter = new TempFriendListAdapter(PublicUserDetails.class, R.layout.contact_item_view, TempFriendListViewHolder.class, friendsRef);
        friendListRecyclerView.setAdapter(tempFriendListAdapter);

    }

}
