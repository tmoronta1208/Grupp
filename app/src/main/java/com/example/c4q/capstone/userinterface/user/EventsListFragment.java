package com.example.c4q.capstone.userinterface.user;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventDataListener;
import com.example.c4q.capstone.utils.FBEventDataListener;
import com.example.c4q.capstone.utils.FBEventDataUtility;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melg on 3/20/18.
 */

public class EventsListFragment extends Fragment{

    private View view;





    public EventsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_eventslist, container, false);


        return view;
    }



}
