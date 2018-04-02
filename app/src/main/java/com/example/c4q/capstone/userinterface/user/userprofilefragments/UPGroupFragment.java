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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* populate GROUP LIST HERE list here because the view pager calls oncreateview
        it populates the list as many times as you go into the fragment MG */
        if (randomNumbersList.size() == 0) {
            for (int i = 1; i <= 8; i++) {
                randomNumbersList.add(i);
            }
        }


    }

    /*public static UPGroupFragment newInstance(String title) {
        UPGroupFragment fragment = new UPGroupFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_group, container, false);


        recyclerView = view.findViewById(R.id.groups_rec);


        groupsAdapter = new GroupsAdapter(getContext(), randomNumbersList);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(groupsAdapter);
        return view;

    }

}
