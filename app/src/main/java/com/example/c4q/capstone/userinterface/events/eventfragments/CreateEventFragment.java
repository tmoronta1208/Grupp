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
    Button closeButton;


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
    public void setViews(){

        addDate = (TextView) rootView.findViewById(R.id.add_date_text_view);
        addTime = (TextView) rootView.findViewById(R.id.add_time_text_view);
        datePicker = (DatePicker) rootView.findViewById(R.id.date_picker);
        timePicker = (TimePicker) rootView.findViewById(R.id.time_picker);
        setCloseButton();
        setDatTimeClick();
        setEditText();
        setupUI(rootView);
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

    public void setEditText() {
        eventName = (EditText) rootView.findViewById(R.id.event_name_edit_text);
        addNote = (EditText) rootView.findViewById(R.id.add_note);
        eventName.setSingleLine();
        setETActionListener(eventName);
        setETActionListener(addNote);
    }

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

    public void setupUI(View view) {

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
                setupUI(innerView);
            }
        }
    }

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
