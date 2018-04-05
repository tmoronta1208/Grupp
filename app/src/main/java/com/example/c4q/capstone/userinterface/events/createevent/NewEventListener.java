package com.example.c4q.capstone.userinterface.events.createevent;

import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.FriendsAdapter;

import java.util.List;

/**
 * Created by amirahoxendine on 4/3/18.
 */

public interface NewEventListener {
    void eventNameEntered(String eventName);

    void dateButtonClicked(DatePickerFragment datePickerFragment, FragmentManager fragmentManager);

    void dateEntered(int eventMonth, int eventDay, TextView addDate);

    void timeEntered(int hour, int minute, TextView addTime);

    void friendInvited(PublicUser publicUser);
    void friendUnInvited(PublicUser publicUser);
    void inviteFriendsButtonClicked(TextView inviteFriends, BottomSheetBehavior bottomSheetBehavior, Button inviteDone, NestedScrollView nestedScrollView);
    void addNoteClicked();

    void noteAdded(String eventNote);

    void createEventButtonClicked();
    void inviteDoneButtonClicked(Button inviteDone, BottomSheetBehavior bottomSheetBehavior, NestedScrollView nestedScrollView);
    void showCreateEventButton(Button createEventButton);
}
