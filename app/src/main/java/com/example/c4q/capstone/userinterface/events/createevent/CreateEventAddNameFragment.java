package com.example.c4q.capstone.userinterface.events.createevent;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.EditTextUX;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventAddNameFragment extends Fragment implements DatePickerDialog.OnDateSetListener {
    View rootView;
    private static String TAG = "CREATE_EVENT_FRAG: ";
    EditText eventName;
    TextView addDate, addTime, dateAndTime;
    TimePicker timePicker;
    DatePicker datePicker;
    Button closeButton, createEventButton;
    EditTextUX editTextUX;
    CreateEventPresenter eventPresenter;
    private CreateEventPTSingleton createEventPTSingleton;
    String eventID;
    LinearLayout hiddenLayout;
    LinearLayout visibleLayout;
    DatePickerFragment datePickerFragment = new DatePickerFragment();

    private String dateString;

    public CreateEventAddNameFragment() {
        // Required empty public constructor
    }

    public static CreateEventAddNameFragment newInstance(CreateEventPTSingleton eventPTSingleton) {
        CreateEventAddNameFragment fragment = new CreateEventAddNameFragment();
        fragment.loadeEventSingleton(eventPTSingleton);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventPresenter = new CreateEventPresenter(CreateEventPTSingleton.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event_add_name_date, container, false);
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

    public void loadeEventSingleton(CreateEventPTSingleton eventPTSingleton) {
        createEventPTSingleton = eventPTSingleton;
        eventPresenter = new CreateEventPresenter(createEventPTSingleton);
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
        hiddenLayout = rootView.findViewById(R.id.hidden_linear_layout);
        visibleLayout = rootView.findViewById(R.id.visible_layout);

    }

    public void setCreateeventButton() {
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "event button clicked");

                if (!eventPresenter.validateEvent()) {
                    Log.d(TAG, "create event: event not valid");
                    //TODO alert user
                } else {
                    eventPresenter.sendEventToFB(new EventFragmentListener() {
                        @Override
                        public void swapFragments() {
                            Log.d(TAG, "Swap fragments called");
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

    public void setDatTimeClick() {
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);
                hiddenLayout.setVisibility(View.VISIBLE);
                visibleLayout.setVisibility(View.GONE);
            }
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                datePicker.setVisibility(View.VISIBLE);
//                closeButton.setVisibility(View.VISIBLE);
//                hiddenLayout.setVisibility(View.VISIBLE);
//                visibleLayout.setVisibility(View.GONE);

                datePickerFragment.show(getFragmentManager(),"datePicker");
            }
        });

    }

    public void setCloseButton() {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePicker.getVisibility() == View.VISIBLE) {

                    eventPresenter.setEventTime(timePicker);
                    dateAndTime.setText(eventPresenter.dateTime);
                    timePicker.setVisibility(View.GONE);
                    hiddenLayout.setVisibility(View.GONE);
                } else if (datePicker.getVisibility() == View.VISIBLE) {

                    eventPresenter.setEventDate(datePicker);
                    dateAndTime.setText(dateString);
                    datePicker.setVisibility(View.GONE);
                    hiddenLayout.setVisibility(View.GONE);
                }
                visibleLayout.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.GONE);
                if (!eventPresenter.validateNameDone()) {
                    Log.d(TAG, "create event: event not valid");
                    //TODO alert user
                } else {
                    eventPresenter.sendEventToFB(new EventFragmentListener() {
                        @Override
                        public void swapFragments() {
                            Log.d(TAG, "Swap fragments called");
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
                //TODO add logic to grab choice data
            }
        });
    }

    public void setEnterNameEditText() {
        editTextUX = new EditTextUX(eventName, eventPresenter, CreateEventAddNameFragment.this.getActivity(), rootView, "eventName", new EventFragmentListener() {
            @Override
            public void swapFragments() {
                Log.d(TAG, "Swap fragments called");
                loadEventFragment();
            }

            @Override
            public void getEventIdKEy(String key) {
                eventID = key;
                loadEventFragment();
            }
        });
    }

    public void loadEventFragment() {
        Intent intent = new Intent(CreateEventAddNameFragment.this.getActivity(), EventActivity.class);
        intent.putExtra("eventID", eventID);
        intent.putExtra("eventType", "new");
        startActivity(intent);
        createEventPTSingleton.destroyInstance();
        getActivity().finish();
    }
/*Set's time to the dateandtimetextview. method can be found in DatePickerFragment*/
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    }
}