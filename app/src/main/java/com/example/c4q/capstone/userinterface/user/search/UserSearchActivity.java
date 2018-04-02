package com.example.c4q.capstone.userinterface.user.search;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

public class UserSearchActivity extends AppCompatActivity {
    private static final String TAG = "UserSearchActivity";
    private RecyclerView searchContactsRecyclerView;
    private FirebaseAuth authentication;
    private DatabaseReference rootRef, searchUserRef;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseUser currentUser;
    private String currentUserID;
    private Button searchBtn;
    private EditText searchField;

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

        searchBtn = findViewById(R.id.search_user_btn);
        searchField = findViewById(R.id.search_field);

        authentication = FirebaseAuth.getInstance();
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();

        rootRef = FirebaseDatabase.getInstance().getReference();
        searchUserRef = rootRef.child(PUBLIC_USER);

        searchContactsRecyclerView = findViewById(R.id.search_users_rv);
        searchContactsRecyclerView.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this);
        searchContactsRecyclerView.setLayoutManager(linearLayoutManager);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = searchField.getText().toString().trim();
                if (!searchText.isEmpty()) {
                    searchUser(searchText);
                }
            }
        });
    }

    private void searchUser(String query) {

        Query userSearchQuery;

        if (query.contains("@")) {
            userSearchQuery = searchUserRef.orderByChild("email").startAt(query).endAt(query + "\uf8ff");
        } else {
            userSearchQuery = searchUserRef.orderByChild("first_name").startAt(query).endAt(query + "\uf8ff");
        }

        FirebaseRecyclerAdapter<PublicUser, UserSearchViewHolder> contactsListAdapter = new FirebaseRecyclerAdapter<PublicUser, UserSearchViewHolder>(
                PublicUser.class, R.layout.add_contact_itemview, UserSearchViewHolder.class, userSearchQuery) {

            @Override
            protected void populateViewHolder(final UserSearchViewHolder viewHolder, final PublicUser model, int position) {

                final String contactID = getRef(position).getKey();

                final String email = model.getEmail();
                final String first = model.getFirst_name();
                final String last = model.getLast_name();
                final String icon = model.getUser_icon().getIcon_url();
                final String zipCode = model.getZip_code();
                //final int radius = model.getRadius();

                viewHolder.setEmail(email);
                viewHolder.setFullName(first, last);
                viewHolder.setIcon(icon);

                viewHolder.addContactButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String radiusString = "20";
                        addToContactList(contactID, viewHolder.addContactButton, first, last, email, icon, radiusString, zipCode);
                    }
                });
            }
        };

        searchContactsRecyclerView.setAdapter(contactsListAdapter);
    }

    public void addToContactList(String contactID, final Button addContactButton, String
            first, String last, String email, String url, String radius, String zipcode) {
        /**
         * TODO: Write logic to retrieve contacts list first, and then update the list with the new values.
         * TODO: also need to write logic to check if user is already in contact list
         */
        final PublicUserDetails publicUserDetails = new PublicUserDetails(first, last, email, url, contactID, radius, zipcode);

        final Map<String, Object> user_contacts = new HashMap<>();

        final DatabaseReference userContactsRef = rootRef.child(USER_CONTACTS).child(currentUserID);
        userContactsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        PublicUserDetails pubUser = ds.getValue(PublicUserDetails.class);
                        if (pubUser != null) {
                            String userId = pubUser.getUid();
                            user_contacts.put(userId, pubUser);
                        }
                    }
                    String currentUID = publicUserDetails.getUid();
                    user_contacts.put(currentUID, publicUserDetails);
                    userContactsRef.updateChildren(user_contacts);
                    addContactButton.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}