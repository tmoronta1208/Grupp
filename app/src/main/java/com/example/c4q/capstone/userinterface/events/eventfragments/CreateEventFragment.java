package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.eventfragments.createeventux.DateTimeUX;
import com.example.c4q.capstone.userinterface.events.eventfragments.createeventux.DoneUX;
import com.example.c4q.capstone.userinterface.events.eventfragments.createeventux.EditTextUX;
import com.example.c4q.capstone.userinterface.events.eventfragments.createeventux.ExpandUX;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    DatabaseReference rootRef, friendDatabase, friendReqDatabase, notificationDatabase, usersDatabase;
    ProgressDialog progressDialog;
    FirebaseUser mCurrent_user;

    SingleEventFragment singleEventFragment = new SingleEventFragment();
    DateTimeUX dateTimeUX;
    DoneUX doneUX;
    EditTextUX editTextUX;
    ExpandUX expandUX;
    Bundle bundle;


    public CreateEventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event, container, false);

        friendReqDatabase = FirebaseDatabase.getInstance().getReference().child("Friend_req");
        friendDatabase = FirebaseDatabase.getInstance().getReference().child("Friends");


        setViews();
        loadPresenters();
        addFriends();

        return rootView;
    }

    /**
     * Load views _ AJ
     */
    /*method to load views -AJ*/
    public void setViews() {
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

    public void loadPresenters() {
        eventPresenter = new CreateEventPresenter();
        dateTimeUX = new DateTimeUX(timePicker, datePicker, closeButton, addDate, addTime, dateAndTime, eventPresenter);
        expandUX = new ExpandUX(inviteGuestsContainer, addFriendsButton, addGroupButton);
        editTextUX = new EditTextUX(eventName, addNote, eventPresenter, CreateEventFragment.this.getActivity(), rootView);
        doneUX = new DoneUX(createEventButton, eventPresenter, new EventFragmentListener() {
            @Override
            public void swapFragments() {
                loadEventFragment();
            }

            @Override
            public void getEventIdKEy(String key) {
                bundle = new Bundle();
                bundle.putString("eventID", key);
            }
        });

    }

    public void loadEventFragment() {
        Log.d("Create Event Frag", "loadEventFragment called");
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        singleEventFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.event_fragment_container, singleEventFragment);
        fragmentTransaction.addToBackStack("next");
        fragmentTransaction.commit();
    }

    public void addFriends() {
        addFriendsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
