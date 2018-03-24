package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.eventfragments.CreateEventFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventOneFragment extends Fragment {
    private String title;
    View rootView;
    Button barButton, restaurantButton;
    CreateEventModel createEventModel;
    ViewPager viewPager;


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
        title = getArguments().getString("title");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event_one, container, false);





        barButton = (Button) rootView.findViewById(R.id.bar_choice_button);
        restaurantButton = (Button) rootView.findViewById(R.id.restaurant_choice_button);


        return rootView;
    }

    public void onButtonClick(){
        View.OnClickListener choiceClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (v.getTag().toString().equals("bar")) {
                createEventModel.setEventVenueType("bar");
            } else {
                createEventModel.setEventVenueType("restaurant");
            }
            v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        };
        barButton.setOnClickListener(choiceClickListener);
        restaurantButton.setOnClickListener(choiceClickListener);
    }

}
