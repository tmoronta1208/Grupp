package com.example.c4q.capstone.userinterface.events.createevent;

import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;

/**
 * Created by amirahoxendine on 4/3/18.
 */

public interface NewEventListener {
    void eventNameEntered(String eventName);

    void dateButtonClicked(DatePickerFragment datePickerFragment, FragmentManager fragmentManager);

    void dateEntered(int eventMonth, int eventDay);

    void timeButtonClicked(TimePicker timePicker, Button closeButton, LinearLayout visibleLayout, LinearLayout hiddenLayout);

    void timeEntered(TimePicker timePicker);

    void closeButtonClicked(TimePicker timePicker, Button closeButton, LinearLayout visibleLayout, LinearLayout hiddenLayout);

    void inviteFriendsButtonClicked();

    void friendInvited(PublicUser publicUser);

    void addNoteClicked();

    void noteAdded(String eventNote);

    void doneButtonClicked();
}
