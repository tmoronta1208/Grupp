package com.example.c4q.capstone.userinterface.events.createevent;


import android.app.Activity;
import android.content.Intent;
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

import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.EditTextUX;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment {
    View rootView;
    private static String TAG = "CREATE_EVENT_FRAG: ";
    EditText eventName;
    TextView addDate, addTime, dateAndTime;
    TimePicker timePicker;
    DatePicker datePicker;
    Button closeButton, createEventButton;
    EditTextUX editTextUX;

    CreateEventPresenter eventPresenter;


    String eventID;
    String title;


    public CreateEventFragment() {
        // Required empty public constructor
    }

    public static CreateEventFragment newInstance(String title) {
        CreateEventFragment fragment = new CreateEventFragment();
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
        rootView = inflater.inflate(R.layout.fragment_create_event, container, false);
        rootView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        setViews();
        setCreateeventButton();
        setDatTimeClick();
        setCloseButton();
        setEnterNameEditText();
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
        eventName = (EditText) rootView.findViewById(R.id.event_name_edit_text);
        eventPresenter = new CreateEventPresenter();
    }

    public void setCreateeventButton(){
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eventPresenter.validateEvent()){
                    Log.d(TAG, "create event: event not valid");
                    //TODO alert user
                } else{
                    eventPresenter.sendEventToFB(new EventFragmentListener() {
                        @Override
                        public void swapFragments() {
                            Log.d(TAG,"Swap fragments called");
                            loadEventFragment();
                        }

                        @Override
                        public void getEventIdKEy(String key) {
                            eventID = key;
                            loadEventFragment();
                        }
                    });
                    Log.d(TAG, "create event: eventSent to firebase");

                }
            }
        });
    }

    public void setDatTimeClick(){
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);
            }
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);
            }
        });

    }

    public void setCloseButton(){
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePicker.getVisibility() == View.VISIBLE) {
                    eventPresenter.setEventTime(timePicker);
                    dateAndTime.setText(eventPresenter.dateTime);
                    timePicker.setVisibility(View.GONE);
                } else if (datePicker.getVisibility() == View.VISIBLE) {
                    eventPresenter.setEventDate(datePicker);
                    dateAndTime.setText(eventPresenter.dateTime);
                    datePicker.setVisibility(View.GONE);
                }
                closeButton.setVisibility(View.GONE);
                //TODO add logic to grab choice data
            }
        });
    }
    public void setEnterNameEditText(){
        editTextUX = new EditTextUX(eventName, eventPresenter,CreateEventFragment.this.getActivity(), rootView, "eventName");

    }

    /*method to hide soft keyboard -AJ*/
    public static void hideSoftKeyboard(Activity activity) {
        try {
            if (activity != null) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) activity.getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(
                        activity.getCurrentFocus().getWindowToken(), 0);
            }

        } catch (NullPointerException n) {
            n.printStackTrace();
        }


    }


    public void loadEventFragment() {
       Intent intent = new Intent(CreateEventFragment.this.getActivity(), EventActivity.class);
       intent.putExtra("eventID", eventID);
       startActivity(intent);
    }

}