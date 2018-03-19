package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.model.events.Events;
import com.example.c4q.capstone.userinterface.events.CreateEventInteractions;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment {
    View rootView;
    private static String TAG = "CREATE_EVENT_FRAG: ";
    EditText eventName, addNote;
    TextView addDate, addTime, dateAndTime;
    TimePicker timePicker;
    DatePicker datePicker;
    Button closeButton, createEventButton, addFriendsButton, addGroupButton;
    FrameLayout inviteGuestsContainer;
    CreateEventPresenter eventPresenter;
    CreateEventInteractions eventInteractions;



    public CreateEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event, container, false);
        eventPresenter = new CreateEventPresenter();

        setViews();
        setViewInteractions();

        return rootView;
    }
    /** Load views _ AJ */
    /*method to load views -AJ*/
    public void setViews(){
        addDate = (TextView) rootView.findViewById(R.id.add_date_text_view);
        addTime = (TextView) rootView.findViewById(R.id.add_time_text_view);
        dateAndTime = (TextView) rootView.findViewById(R.id.date_time_text_view);
        datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        timePicker = (TimePicker) rootView.findViewById(R.id.time_picker);
        closeButton = (Button) rootView.findViewById(R.id.close_button);
        createEventButton = (Button) rootView.findViewById(R.id.create_event_button);
        addFriendsButton = (Button) rootView.findViewById(R.id.add_friends_button);
        addGroupButton = (Button) rootView.findViewById(R.id.add_group_button);
        inviteGuestsContainer = (FrameLayout) rootView.findViewById(R.id.invite_guests_fragment_container);
        eventName = (EditText) rootView.findViewById(R.id.event_name_edit_text);
        addNote = (EditText) rootView.findViewById(R.id.add_note);
        eventName.setSingleLine();
    }

    public void setViewInteractions(){
        eventInteractions = new CreateEventInteractions(CreateEventFragment.this.getActivity(), rootView, eventName, addNote, addDate, addTime,dateAndTime, timePicker, datePicker, closeButton, createEventButton,addFriendsButton, addGroupButton, inviteGuestsContainer, eventPresenter);
        eventInteractions.setViewLogic();
    }

}
