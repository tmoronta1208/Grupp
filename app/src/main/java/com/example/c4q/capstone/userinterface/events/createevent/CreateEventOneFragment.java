package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.eventfragments.CreateEventFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventOneFragment extends Fragment {
    private String title;
    private int image;
    View rootView;


    public CreateEventOneFragment() {
        // Required empty public constructor
    }

    public static CreateEventOneFragment newInstance(String title) {
        CreateEventOneFragment fragment = new CreateEventOneFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        image = getArguments().getInt("image", 0);
        title = getArguments().getString("title");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event_one, container, false);
        TextView tvLabel = (TextView) rootView.findViewById(R.id.txtMain);
        tvLabel.setText(title);


        return rootView;
    }

}
