package com.example.c4q.capstone.userinterface.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private List<Integer> numbers = new ArrayList<>();

    public ContactListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_contactlist, container, false);

        recyclerView = view.findViewById(R.id.contact_list_rec);

        for (int i = 0; i <=20 ; i++) {
            numbers.add(i);
        }

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        ContactListAdapter contactListAdapter = new ContactListAdapter(numbers,getContext());
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(linearLayout);



            return view;

    }

}
