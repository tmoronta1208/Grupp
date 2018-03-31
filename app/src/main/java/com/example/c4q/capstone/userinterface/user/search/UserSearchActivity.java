package com.example.c4q.capstone.userinterface.user.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.database.publicuserdata.UserSearch;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
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

import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;
import static com.example.c4q.capstone.utils.Constants.USER_SEARCH;

public class UserSearchActivity extends AppCompatActivity {
    private static final String TAG = "UserSearchActivity";
    private RecyclerView searchContactsRecyclerView;
    private FirebaseAuth authentication;
    private DatabaseReference rootRef, searchUserRef;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseUser currentUser;
    private String currentUserID;

    /**
     * TODO: create a searchable user interface
     * <p>
     * At the moment the app can retrieve everyone in the user search node. want to be able to
     * search for specific users instead.
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_search);

        authentication = FirebaseAuth.getInstance();
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();

        rootRef = FirebaseDatabase.getInstance().getReference();
        searchUserRef = rootRef.child(USER_SEARCH);

        searchContactsRecyclerView = findViewById(R.id.search_users_rv);
        searchContactsRecyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        searchContactsRecyclerView.setLayoutManager(linearLayoutManager);

        getContactListAdapter();

    }

    private void getContactListAdapter() {

        FirebaseRecyclerAdapter<UserSearch, UserSearchViewHolder> contactsListAdapter = new FirebaseRecyclerAdapter<UserSearch, UserSearchViewHolder>(
                UserSearch.class, R.layout.add_contact_itemview, UserSearchViewHolder.class, searchUserRef) {

            @Override
            protected void populateViewHolder(final UserSearchViewHolder viewHolder, UserSearch model, int position) {

                final String contactID = getRef(position).getKey();

                final String email = model.getEmail();
                final String first = model.getFirst_name();
                final String last = model.getLast_name();
                final String icon = model.getIcon_url();
                final String zipCode = model.getZip_code();
                final int radius = model.getRadius();

                viewHolder.setEmail(email);
                viewHolder.setFullName(first, last);
                viewHolder.setIcon(icon);

                viewHolder.addContactButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addToContactList(contactID, viewHolder.addContactButton, first, last, email, icon, radius, zipCode);
                    }
                });
            }
        };

        searchContactsRecyclerView.setAdapter(contactsListAdapter);
    }

    public void addToContactList(String contactID, final Button addContactButton, String first, String last, String email, String url, int radius, String zipcode) {
        /**
         * TODO: Write logic to retrieve contacts list first, and then update the list with the new values.
         * TODO: also need to write logic to check if user is already in contact list
         */
        final PublicUserDetails publicUserDetails = new PublicUserDetails(first, last, email, url, contactID, radius, zipcode);

        final HashMap<String, Object> user_contacts = new HashMap<>();

        final DatabaseReference userContactsRef = rootRef.child(USER_CONTACTS).child(currentUserID);
        userContactsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        PublicUserDetails pubUser = ds.getValue(PublicUserDetails.class);
                        if (pubUser != null) {
                            user_contacts.put(pubUser.getUid(), pubUser);
                        }
                    }
                    user_contacts.put(publicUserDetails.getUid(), publicUserDetails);
                    userContactsRef.updateChildren(user_contacts);
                    addContactButton.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //before we add new contacts, we need a map of contacts that are already there.


        //currentUserContactList.add(publicUserDetails);


        // userContactsRef.setValue(userContacts);
        //this updates the user's contacts.


        /*userContactsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                addContactButton.setVisibility(View.INVISIBLE);
                Log.d(TAG, "onDataChange: " + dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}