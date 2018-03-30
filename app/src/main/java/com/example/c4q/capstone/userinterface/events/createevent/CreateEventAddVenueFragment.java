package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventAddVenueFragment extends Fragment {

    View rootView;
    Button barButton, restaurantButton;
    NewEventBuilder newEventBuilder;
    CreateEventPresenter eventPresenter;



    public CreateEventAddVenueFragment() {
        // Required empty public constructor
    }

    public static CreateEventAddVenueFragment newInstance(NewEventBuilder newEventBuilder) {
        CreateEventAddVenueFragment fragment = new CreateEventAddVenueFragment();
        fragment.loadEventSingleton(newEventBuilder);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventPresenter = new CreateEventPresenter(NewEventBuilder.getInstance());
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event_add_venue, container, false);
        barButton = (Button) rootView.findViewById(R.id.bar_choice_button);
        restaurantButton = (Button) rootView.findViewById(R.id.restaurant_choice_button);


        return rootView;
    }

    public void loadEventSingleton(NewEventBuilder eventPTSingleton){
        newEventBuilder = eventPTSingleton;
        eventPresenter = new CreateEventPresenter(newEventBuilder);
    }

    @Override
    public void onPause() {
        super.onPause();
        //(CreateEventActivity) getActivity().

        Log.d("Create Event One", "on pause called");
    }

    public void onButtonClick(){
        View.OnClickListener choiceClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (v.getTag().toString().equals("bar")) {
                newEventBuilder.setEventVenueType("bar");
            } else {
                newEventBuilder.setEventVenueType("restaurant");
            }
            v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        };
        barButton.setOnClickListener(choiceClickListener);
        restaurantButton.setOnClickListener(choiceClickListener);
    }

}
