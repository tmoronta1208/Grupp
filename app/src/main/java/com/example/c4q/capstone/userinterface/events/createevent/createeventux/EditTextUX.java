package com.example.c4q.capstone.userinterface.events.createevent.createeventux;

import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.createevent.NewEventListener;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public class EditTextUX {
    private EditText editText;
    private NewEventListener newEventListener;
    private Activity activity;
    private  View rootView;
    private String type;
    private EventFragmentListener listener;


    public EditTextUX(EditText editText, NewEventListener newEventListener, Activity activity, View rootview, String type) {
        this.editText = editText;
        this.newEventListener = newEventListener;
        this.activity = activity;
        this.rootView = rootview;
        this.type = type;
        this.listener = listener;

        setEditText();
       hideKeyBoardOffFocus(rootView);
    }

    /** Hide KeyBoards  -AJ*/
    /*method to set action listeners for edit texts so keyboard hides when users click go/enter*/
    public void setEditText() {
        setETActionListener(editText);
        //setETActionListener(addNote);
    }
    /*method to hide keyboard when user clicks go & add data*/
    public void setETActionListener(final EditText editText){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    hideSoftKeyboard(activity);
                    if(type.equals("eventName")){
                        String name = editText.getText().toString().trim();
                        if (!name.equals("")){
                            newEventListener.eventNameEntered(name);
                        }
                    } else if(type.equals("addNote")) {
                        //newEventListener.(editText.getText().toString());
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

                    if(event.getActionMasked() == MotionEvent.ACTION_UP) {
                        hideSoftKeyboard(activity);
                        Log.d("Edit text UX" , "hide keyboard called");
                        if (type.equals("eventName")) {
                            String name = editText.getText().toString().trim();
                            if (!name.equals("")){
                                newEventListener.eventNameEntered(name);
                            }
                        } else if(type.equals("eventNote")){
                            String note = editText.getText().toString().trim();
                            if (!note.equals("")){
                                //add method fir setting note
                            }
                        }
                    }


                    /*if(!editText.getText().toString().equals("")) {
                        String text = editText.getText().toString();

                        if (type.equals("eventName")){
                            eventPresenter.setEventName(text);
                        } else if (type.equals("eventName")) {
                            eventPresenter.setEventNote(text);
                        }
                    } else {
                        editText.setError("Please enter a name");
                    }*/
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
            if (activity != null) {
                InputMethodManager inputMethodManager =
                        (InputMethodManager) activity.getSystemService(
                                Activity.INPUT_METHOD_SERVICE);
                if (activity.getCurrentFocus() != null){
                    if(inputMethodManager != null){
                        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
                    }
                }
            }
    }
}
