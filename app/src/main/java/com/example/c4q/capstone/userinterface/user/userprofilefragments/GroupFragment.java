package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.GroupsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private GroupsAdapter groupsAdapter;


    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_preferences, container, false);


        recyclerView = view.findViewById(R.id.groups_rec);

        groupsAdapter = new GroupsAdapter();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayout);
        return view;

    }

}
