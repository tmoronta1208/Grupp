package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.groups.Groups;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.GroupsAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.GroupViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.GROUPS;
import static com.example.c4q.capstone.utils.Constants.USER_EVENTS_LIST;
import static com.example.c4q.capstone.utils.currentuser.CurrentUserUtility.currentUserID;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPGroupFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private GroupsAdapter groupsAdapter;
    private DatabaseReference rootRef, groupRef;


    public UPGroupFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        String currentUserID = CurrentUser.getInstance().getUserID();
//
//        rootRef = FirebaseDatabase.getInstance().getReference();
//        groupRef = rootRef.child(GROUPS).child(currentUserID);
//
//        /* populate GROUP LIST HERE list here because the view pager calls oncreateview
//        it populates the list as many times as you go into the fragment MG */
//        if (randomNumbersList.size() == 0) {
//            for (int i = 1; i <= 8; i++) {
//                randomNumbersList.add(i);
//            }
//        }
//
//
//    }

    /*public static UPGroupFragment newInstance(String title) {
        UPGroupFragment fragment = new UPGroupFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }*/

    //    String currentUserID = CurrentUser.getInstance().getUserID();
//    rootRef = FirebaseDatabase.getInstance().getReference();
//    eventsRef = rootRef.child(USER_EVENTS_LIST).child(currentUserID);
//
//    recyclerView = view.findViewById(R.id.events_rec);
//        recyclerView.setHasFixedSize(true);
//
//    LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
//        linearLayout.setStackFromEnd(true);
//        linearLayout.setReverseLayout(true);
//        recyclerView.setLayoutManager(linearLayout);
//
//    eventsAdapter = new EventsAdapter(Events.class, R.layout.events_item_view, EventsViewHolder.class, eventsRef);
//
//        recyclerView.setAdapter(eventsAdapter);
//        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_group, container, false);

        String currentUserID = CurrentUser.getInstance().getUserID();

        rootRef = FirebaseDatabase.getInstance().getReference();
        groupRef = rootRef.child(GROUPS).child(currentUserID);

        recyclerView = view.findViewById(R.id.groups_rec);


        groupsAdapter = new GroupsAdapter(Groups.class, R.layout.group_item_view, GroupViewHolder.class, groupRef);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(groupsAdapter);
        return view;

    }

}
