package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.GroupsAdapter;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPGroupDetailsFragment extends Fragment {

    RecyclerView recyclerView;
    GroupsAdapter groupsAdapter;
    List<Integer> list = new ArrayList<>();
    View rootView;
    FloatingActionButton returnButton;


    public UPGroupDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_upgroup_details, container, false);
        returnButton = rootView.findViewById(R.id.group__detailsreturn_button);
        recyclerView = rootView.findViewById(R.id.group_details_rv);
        for (int i = 0; i < 6; i++) {

            list.add(i);

        }

        groupsAdapter = new GroupsAdapter(getContext(), list);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(groupsAdapter);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                startActivity(intent);
            }
        });
        return rootView;


    }

}
