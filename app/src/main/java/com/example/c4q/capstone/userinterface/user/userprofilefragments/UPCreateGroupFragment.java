package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.GroupsAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.GROUPS;
import static com.example.c4q.capstone.utils.Constants.GROUP_NAME;
import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPCreateGroupFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID;
    private DatabaseReference rootRef, contactsRef;
    private FirebaseUser currentUser;
    private String groupTitle;
    RecyclerView recyclerView;
    GroupsAdapter groupsAdapter;
    List<String> groupMembers = new ArrayList<>();
    View rootView;
    EditText groupTitleInput;
    FloatingActionButton returnButton;
    private ContactListAdapter contactListAdapter;
    Button addButton;


    public UPCreateGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_upgroup_create, container, false);
        addButton = rootView.findViewById(R.id.add_contact_button);
        addButton.setVisibility(View.VISIBLE);
        returnButton = rootView.findViewById(R.id.group_return_button);
        groupTitleInput = rootView.findViewById(R.id.create_group_title_name);


        //Firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();
        contactsRef = rootRef.child(USER_CONTACTS).child(currentUserID);


        recyclerView = rootView.findViewById(R.id.grupp_create_group_rv);

        contactListAdapter = new ContactListAdapter(PublicUserDetails.class, R.layout.add_contact_itemview, ContactListViewHolder.class, contactsRef);

        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView.setAdapter(contactListAdapter);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addButton.setVisibility(View.INVISIBLE);

                //rootRef.child(GROUPS).child(currentUserID).push().child(GROUP_NAME).setValue(groupTitle);
              //  UserSearchActivity.addToContactList(contactID, viewHolder.addContactButton, first, last, email, icon, radiusString, zipCode);


            }
        });


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (groupTitle != null) {

                    groupTitle = groupTitleInput.getText().toString();

                    rootRef.child(GROUPS).child(currentUserID).push().child(GROUP_NAME).setValue(groupTitle);
                }

                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }


}
