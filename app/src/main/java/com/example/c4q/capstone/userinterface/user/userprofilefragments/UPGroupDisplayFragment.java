package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.GroupsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPGroupDisplayFragment extends Fragment {

    RecyclerView recyclerView;
    GroupsAdapter groupsAdapter;
    List<Integer> numbers = new ArrayList<>();
    View rootView;


    public UPGroupDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_upgroup_display, container, false);

        for (int i = 0; i < 6; i++) {

            numbers.add(i);

        }

        recyclerView = rootView.findViewById(R.id.grupp_display_member_rv);


        groupsAdapter = new GroupsAdapter(getContext(), numbers);
        recyclerView.setLayoutManager(new GridLayoutManager(rootView.getContext(), 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(groupsAdapter);


        return rootView;
    }

}
