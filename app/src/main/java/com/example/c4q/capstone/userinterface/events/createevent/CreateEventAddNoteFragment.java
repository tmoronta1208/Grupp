package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventAddNoteFragment extends Fragment {


    public CreateEventAddNoteFragment() {
        // Required empty public constructor
    }

    public static CreateEventAddNoteFragment newInstance(String title) {
        CreateEventAddNoteFragment fragment = new CreateEventAddNoteFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //title = getArguments().getString("title");
        //CurrentUser.getInstance().
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_event_add_note, container, false);
    }

}
