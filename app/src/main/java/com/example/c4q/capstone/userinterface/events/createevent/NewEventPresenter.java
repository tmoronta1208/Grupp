package com.example.c4q.capstone.userinterface.events.createevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 4/3/18.
 */

public class NewEventPresenter implements NewEventListener {
    NewEventBuilder newEventBuilder = NewEventBuilder.getInstance();
    View view;
    Context context;
    Activity activity;
    public static String TAG = "NEW EVENT PRES";

    public NewEventPresenter(Context context, Activity activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void eventNameEntered(String eventName) {
        newEventBuilder.setEventName(eventName);
        Log.d(TAG, "event name : " + newEventBuilder.getEventName());
    }

    @Override
    public void dateButtonClicked(DatePickerFragment datePickerFragment, FragmentManager fragmentManager) {
        datePickerFragment.show(fragmentManager,"datePicker");
    }



    @Override
    public void dateEntered(int eventMonth, int eventDay) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder.append(months[eventMonth]);
        dateBuilder.append(" ");
        dateBuilder.append(String.valueOf(eventDay));
        newEventBuilder.setEventDate(dateBuilder.toString());
        Log.d(TAG, "event date : " + newEventBuilder.getEventDate());
    }

    @Override
    public void timeButtonClicked(TimePicker timePicker, Button closeButton, LinearLayout visibleLayout, LinearLayout hiddenLayout) {
        timePicker.setVisibility(View.VISIBLE);
        closeButton.setVisibility(View.VISIBLE);
        hiddenLayout.setVisibility(View.VISIBLE);
        visibleLayout.setVisibility(View.GONE);
    }

    @Override
    public void timeEntered(TimePicker timePicker) {
        int hour;
        int minute;
        if (Build.VERSION.SDK_INT < 23) {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        } else {
            hour = timePicker.getHour();
            minute =timePicker.getMinute();
        }
        StringBuilder timeBuilder = new StringBuilder();
        String amPm = "am";
        if (hour == 12){
            amPm = "pm";
        }
        if (hour > 12){
            hour = hour - 12;
            amPm = "pm";
        } else if (hour == 0){
            hour = 12;
        }
        timeBuilder.append(String.valueOf(hour));
        timeBuilder.append(":");
        if (minute < 10){
            timeBuilder.append("0");
        }
        timeBuilder.append(minute);
        timeBuilder.append(" ");
        timeBuilder.append(amPm);
        newEventBuilder.setEventTime(timeBuilder.toString());
        Log.d(TAG, "event time : " + newEventBuilder.getEventTime());
    }

    @Override
    public void closeButtonClicked(TimePicker timePicker, Button closeButton, LinearLayout visibleLayout, LinearLayout hiddenLayout) {
        if (timePicker.getVisibility() == View.VISIBLE) {
            timePicker.setVisibility(View.GONE);
            hiddenLayout.setVisibility(View.GONE);
            visibleLayout.setVisibility(View.VISIBLE);
            closeButton.setVisibility(View.GONE);
        }
    }


    @Override
    public void inviteFriendsButtonClicked() {

    }

    @Override
    public void friendInvited(PublicUser publicUser) {
        List<PublicUser> invitedFriends = newEventBuilder.getInvitedFriendsUserList();
        if (invitedFriends == null){
            invitedFriends = new ArrayList<>();
        }
        invitedFriends.add(publicUser);
        newEventBuilder.setInvitedFriendsUserList(invitedFriends);
        List<String> friendIdList = newEventBuilder.getInvitedGuests();
        if (friendIdList == null){
            friendIdList = new ArrayList<>();
        }
        friendIdList.add(publicUser.getUser_id());
        newEventBuilder.setInvitedGuests(friendIdList);
        Log.d(TAG, "event invite list : " + newEventBuilder.getInvitedFriendsUserList().size());
    }

    @Override
    public void addNoteClicked() {

    }

    @Override
    public void noteAdded(String eventNote) {
        newEventBuilder.setEventNote(eventNote);
        Log.d(TAG, "event note : " + newEventBuilder.getEventNote());
    }

    @Override
    public void doneButtonClicked() {
        boolean eventNameSet = newEventBuilder.getEventName() != null;
        boolean eventDateSet = newEventBuilder.getEventDate() != null;
        boolean eventTimeSet = newEventBuilder.getEventTime() != null;
        boolean eventGuestsSet = newEventBuilder.getInvitedFriendsUserList() != null;

        boolean validEvent = eventDateSet && eventTimeSet && eventNameSet && eventGuestsSet;
        Log.d(TAG, "event validation: " + validEvent);
        CreateEventController eventController = new CreateEventController();
        eventController.sendEventToFireBase(validEvent, new EventFragmentListener() {
            @Override
            public void swapFragments() {

            }

            @Override
            public void getEventIdKEy(String key) {
               String eventID = key;
                Log.d(TAG, "event id: " + eventID);
               launchEventActivity(eventID);
            }
        });


    }

    public void launchEventActivity(String eventID) { // change name
        Log.d(TAG, "launch event activity called ");
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("eventID", eventID);
        intent.putExtra("eventType", "new");
        context.startActivity(intent);
        //newEventBuilder.destroyInstance();
        activity.finish();
    }
}
