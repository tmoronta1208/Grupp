package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.GroupsAdapter;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPCreateGroupFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String currentUserID;
    private DatabaseReference rootRef, preferencesDB;
    private FirebaseUser currentUser;
    RecyclerView recyclerView;
    GroupsAdapter groupsAdapter;
    List<Integer> numbers = new ArrayList<>();
    View rootView;
    EditText groupTitle;
    FloatingActionButton button;


    public UPCreateGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_upgroup_display, container, false);


        //Firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUserID = currentUser.getUid();
        rootRef = FirebaseDatabase.getInstance().getReference();
        preferencesDB = rootRef.child(PRIVATE_USER);

        button = rootView.findViewById(R.id.group_return_button);


        for (int i = 0; i < 6; i++) {

            numbers.add(i);

        }

        recyclerView = rootView.findViewById(R.id.grupp_create_group_rv);


        groupsAdapter = new GroupsAdapter(getContext(), numbers);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(groupsAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}
