package com.example.c4q.capstone.userinterface.events;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
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
import com.example.c4q.capstone.userinterface.events.eventfragments.CreateEventFragment;

/**
 * Created by amirahoxendine on 3/19/18.
 * This is a class to handle interactions for CreateEventFragment.
 */

public class CreateEventInteractions {
    private static String TAG = "CREATE_EVENT_UX: ";
    private  View rootView;
    private EditText eventName, addNote;
    private TextView addDate, addTime, dateAndTime;
    private TimePicker timePicker;
    private DatePicker datePicker;
    private Button closeButton, createEventButton, addFriendsButton, addGroupButton;
    private FrameLayout inviteGuestsContainer;
    private CreateEventPresenter eventPresenter;
    private Activity activity;

    public CreateEventInteractions(Activity activity, View rootView, EditText eventName, EditText addNote, TextView addDate, TextView addTime, TextView dateAndTime, TimePicker timePicker, DatePicker datePicker, Button closeButton, Button createEventButton, Button addFriendsButton, Button addGroupButton, FrameLayout inviteGuestsContainer, CreateEventPresenter eventPresenter) {
        this.rootView = rootView;
        this.eventName = eventName;
        this.addNote = addNote;
        this.addDate = addDate;
        this.addTime = addTime;
        this.dateAndTime = dateAndTime;
        this.timePicker = timePicker;
        this.datePicker = datePicker;
        this.closeButton = closeButton;
        this.createEventButton = createEventButton;
        this.addFriendsButton = addFriendsButton;
        this.addGroupButton = addGroupButton;
        this.inviteGuestsContainer = inviteGuestsContainer;
        this.eventPresenter = eventPresenter;
        this.activity = activity;
    }

    /** View Logic : Below is the logic for handling user input and interaction events
     * -AJ*/
    public void setViewLogic(){
        dateAndTime.setText(eventPresenter.dateTime);
        addUsersOnClickListener(addFriendsButton);
        addUsersOnClickListener(addGroupButton);
        setCloseButton();
        setDatTimeClick();
        setEditText();
        hideKeyBoardOffFocus(rootView);
        setCreateEventButton();
    }

    /** Add users -AJ*/
    public void setCreateEventButton(){
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!eventPresenter.validateEvent()){
                    Log.d(TAG, "create event: event not valid");
                    //TODO alert user
                } else{
                    eventPresenter.sendEventToFB();
                    Log.d(TAG, "create event: eventSent to firebase");
                    //TODO close create event frag, load event frag.
                }
            }
        });
    }

    /*click listener that toggles fragment visibility -AJ*/
    public void addUsersOnClickListener(final Button button){
        //TODO consolide framelayouts
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "add users clicked" + button.getTag().toString());
                if (inviteGuestsContainer.getVisibility() == View.VISIBLE) {
                    inviteGuestsContainer.setVisibility(View.GONE);
                } else {
                    inviteGuestsContainer.setVisibility(View.VISIBLE);
                    //TODO load fragments logic for adding users to guest list
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

    /** Hide KeyBoards  -AJ*/
    /*method to set action listeners for edit texts so keyboard hides when users click go/enter*/
    public void setEditText() {
        setETActionListener(eventName);
        setETActionListener(addNote);
    }
    /*method to hide keyboard when user clicks go & add data*/
    public void setETActionListener(final EditText editText){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    hideSoftKeyboard(activity);

                    if(editText.getId() == R.id.event_name_edit_text){
                        String name = editText.getText().toString();
                        eventPresenter.setEventName(name);
                    } else {
                        eventPresenter.setEventNote(editText.getText().toString());
                    }
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
                    hideSoftKeyboard(activity);
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
