package com.example.c4q.capstone.userinterface.events.eventfragments.createeventux;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;

import org.w3c.dom.Text;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public class EditTextUX {
    private EditText eventName, addNote;
    private CreateEventPresenter eventPresenter;
    private Activity activity;
    private  View rootView;


    public EditTextUX(EditText eventName, EditText addNote, CreateEventPresenter eventPresenter, Activity activity, View rootview) {
        this.eventName = eventName;
        this.addNote = addNote;
        this.eventPresenter = eventPresenter;
        this.activity = activity;
        this.rootView = rootview;

        setEditText();
       // hideKeyBoardOffFocus(rootView);
    }

    /** Hide KeyBoards  -AJ*/
    /*method to set action listeners for edit texts so keyboard hides when users click go/enter*/
    public void setEditText() {
        setETActionListener(eventName);
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
                    //hideSoftKeyboard(activity);
                    if(!eventName.getText().toString().equals("")) {
                        String name = eventName.getText().toString();
                        eventPresenter.setEventName(name);
                    }

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
