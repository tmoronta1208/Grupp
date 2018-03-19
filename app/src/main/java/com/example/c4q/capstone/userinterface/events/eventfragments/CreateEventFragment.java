package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment {
    View rootView;
    EditText eventName, addNote;
    TextView addDate, addTime;
    TimePicker timePicker;
    DatePicker datePicker;
    Button closeButton, createEventButton, addFriendsButton, addGroupButton;
    FrameLayout addFriendsContainer, addGroupContainer;


    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event, container, false);
        setViews();
        //TODO separate concerns
        return rootView;
    }
    /** Load views _ AJ */
    /*method to load views -AJ*/
    public void setViews(){
        addDate = (TextView) rootView.findViewById(R.id.add_date_text_view);
        addTime = (TextView) rootView.findViewById(R.id.add_time_text_view);
        datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        timePicker = (TimePicker) rootView.findViewById(R.id.time_picker);
        createEventButton = (Button) rootView.findViewById(R.id.create_event_button);
        addFriendsButton = (Button) rootView.findViewById(R.id.add_friends_button);
        addGroupButton = (Button) rootView.findViewById(R.id.add_group_button);
        addFriendsContainer = (FrameLayout) rootView.findViewById(R.id.add_friends_fragment_container);
        addGroupContainer = (FrameLayout)  rootView.findViewById(R.id.add_group_fragment_container);
        eventName = (EditText) rootView.findViewById(R.id.event_name_edit_text);
        addNote = (EditText) rootView.findViewById(R.id.add_note);
        eventName.setSingleLine();
        setViewLogic();
    }
    public void setViewLogic(){
        addUsersOnClickListener(addFriendsButton, addFriendsContainer);
        addUsersOnClickListener(addGroupButton, addGroupContainer);
        setCloseButton();
        setDatTimeClick();
        setEditText();
        hideKeyBoardOffFocus(rootView);
    }

    /** View Logic : Below is the logic for handling user input and interaction events
     * -AJ*/

    /** Add users -AJ*/
    /*click listener that toggles fragment visibility -AJ*/
    public void addUsersOnClickListener(final Button button, final FrameLayout frameLayout){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frameLayout.getVisibility() == View.VISIBLE) {
                    frameLayout.setVisibility(View.GONE);
                } else {
                    frameLayout.setVisibility(View.VISIBLE);
                    //TODO load fragments
                }
            }
        });
    }

    /** Date and Time -AJ */
    /*click listener for date and time -AJ*/
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

    /*click listner to hide date and time picker -AJ*/
    public void setCloseButton(){
        closeButton = (Button) rootView.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePicker.getVisibility() == View.VISIBLE) {
                    String time;
                    if (Build.VERSION.SDK_INT < 23) {
                        time = String.valueOf(timePicker.getCurrentHour()) + ":" + String.valueOf(timePicker.getCurrentMinute());
                    } else {
                        time = String.valueOf(timePicker.getHour()) + ":" + String.valueOf(timePicker.getMinute());
                    }
                    addTime.setText("Time: " + time);
                    timePicker.setVisibility(View.GONE);
                } else if (datePicker.getVisibility() == View.VISIBLE) {

                    String date = String.valueOf(datePicker.getMonth()) + "/" + String.valueOf(datePicker.getDayOfMonth());

                    addDate.setText("Event Date: " + date);
                    datePicker.setVisibility(View.GONE);
                }
                closeButton.setVisibility(View.GONE);
                //TODO add logic to grab choice data
            }
        });

    }

    /** Hide KeyBoards  -AJ*/
    /*method to set action listeners for edit texts so keyboard hides when users click go/enter*/
    public void setEditText() {
        setETActionListener(eventName);
        setETActionListener(addNote);
    }
    /*method to hide keyboard when user clicks go*/
    public void setETActionListener(EditText editText){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    hideSoftKeyboard(CreateEventFragment.this.getActivity());
                    handled = true;
                }
                return handled;
            }
        });
    }

    /*method to hide keyboard when edit text is no longer in focus -AJ*/
    public void hideKeyBoardOffFocus(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(CreateEventFragment.this.getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideKeyBoardOffFocus(innerView);
            }
        }
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
}
