package com.example.c4q.capstone.userinterface.events.createevent.createeventux;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public class DoneUX {
    private Button createEventButton;
    private CreateEventPresenter eventPresenter;
    EventFragmentListener eventFragmentListener;
    private static String TAG = "CREATE_EVENT_UX: ";

    public DoneUX(Button createEventButton, CreateEventPresenter eventPresenter, EventFragmentListener eventFragmentListener) {
        this.createEventButton = createEventButton;
        this.eventPresenter = eventPresenter;
        this.eventFragmentListener = eventFragmentListener;

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
                    eventPresenter.sendEventToFireBase(eventFragmentListener);
                    Log.d(TAG, "create event: eventSent to firebase");
                    eventFragmentListener.swapFragments();
                    //TODO close create event frag, load event frag.
                }
            }
        });
    }
}
