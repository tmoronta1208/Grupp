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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UPGroupFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private GroupsAdapter groupsAdapter;
    List<Integer> randomNumbersList = new ArrayList<>();


    public UPGroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_group, container, false);


        recyclerView = view.findViewById(R.id.groups_rec);

        for (int i = 1; i <= 5; i++) {
            randomNumbersList.add(i);
        }

        groupsAdapter = new GroupsAdapter(getContext(), randomNumbersList);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(groupsAdapter);
        return view;

    }

}
