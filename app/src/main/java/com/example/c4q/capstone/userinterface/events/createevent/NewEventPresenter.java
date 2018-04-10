package com.example.c4q.capstone.userinterface.events.createevent;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.FriendsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 4/3/18.
 */

public class NewEventPresenter implements NewEventListener {
    NewEventBuilder newEventBuilder = NewEventBuilder.getInstance();
    Context context;
    Activity activity;
    Button createEventButton;
    Button inviteDone;
    public static String TAG = "NEW EVENT PRES";
    Dialog alertDialog;
    TextView eventName, eventDate, editEvent, createEvent, goToEvent;
    LinearLayout progressBar, eventReady, rvLayout;
    RecyclerView friends;
    String eventID;

    public NewEventPresenter(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void eventNameEntered(String eventName) {
        newEventBuilder.setEventName(eventName);
        Log.d(TAG, "event name : " + newEventBuilder.getEventName());
        if (validEvent()) {
            createEventButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void dateButtonClicked(DatePickerFragment datePickerFragment, FragmentManager fragmentManager) {
        datePickerFragment.show(fragmentManager, "datePicker");
    }

    @Override
    public void dateEntered(int eventMonth, int eventDay, TextView addDate) {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        StringBuilder dateBuilder = new StringBuilder();
        dateBuilder.append(months[eventMonth]);
        dateBuilder.append(" ");
        dateBuilder.append(String.valueOf(eventDay));
        newEventBuilder.setEventDate(dateBuilder.toString());
        addDate.setText(dateBuilder.toString());
        Log.d(TAG, "event date : " + newEventBuilder.getEventDate());
        if (validEvent()) {
            createEventButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void timeEntered(int hour, int minute, TextView addTime) {
        StringBuilder timeBuilder = new StringBuilder();
        String amPm = "am";
        if (hour == 12) {
            amPm = "pm";
        }
        if (hour > 12) {
            hour = hour - 12;
            amPm = "pm";
        } else if (hour == 0) {
            hour = 12;
        }
        timeBuilder.append(String.valueOf(hour));
        timeBuilder.append(":");
        if (minute < 10) {
            timeBuilder.append("0");
        }
        timeBuilder.append(minute);
        timeBuilder.append(" ");
        timeBuilder.append(amPm);
        newEventBuilder.setEventTime(timeBuilder.toString());
        addTime.setText(timeBuilder.toString());
        Log.d(TAG, "event time : " + newEventBuilder.getEventTime());
        if (validEvent()) {
            createEventButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void friendInvited(PublicUser publicUser) {
        List<PublicUser> invitedFriends = newEventBuilder.getInvitedFriendsUserList();
        if (invitedFriends == null) {
            invitedFriends = new ArrayList<>();
        }
        invitedFriends.add(publicUser);
        newEventBuilder.setInvitedFriendsUserList(invitedFriends);
        List<String> friendIdList = newEventBuilder.getInvitedGuests();
        if (friendIdList == null) {
            friendIdList = new ArrayList<>();
        }
        friendIdList.add(publicUser.getUser_id());
        newEventBuilder.setInvitedGuests(friendIdList);
        Log.d(TAG, "event invite list : " + newEventBuilder.getInvitedFriendsUserList().size());
        inviteDone.setBackground(context.getResources().getDrawable(R.drawable.ic_check_circle_checked_24dp));
    }

    @Override
    public void friendUnInvited(PublicUser publicUser) {
        List<PublicUser> invitedFriends = newEventBuilder.getInvitedFriendsUserList();
        invitedFriends.remove(publicUser);// not quite
        newEventBuilder.setInvitedFriendsUserList(invitedFriends);
        List<String> friendIdList = newEventBuilder.getInvitedGuests();
        friendIdList.remove(publicUser.getUser_id());
        newEventBuilder.setInvitedGuests(friendIdList);
    }

    @Override
    public void inviteFriendsButtonClicked(TextView inviteFriends, BottomSheetBehavior bottomSheetBehavior, Button inviteDone, NestedScrollView nestedScrollView) {
        this.inviteDone = inviteDone;
        inviteDone.setVisibility(View.VISIBLE);
        nestedScrollView.setVisibility(View.VISIBLE);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
    public void createEventButtonClicked() {
        showNewEventAlert();

        /*Log.d(TAG, "event validation: " + validEvent());
        CreateEventController eventController = new CreateEventController(this);
        eventController.sendEventToFireBase(validEvent(), new EventFragmentListener() {
            @Override
            public void getEventIdKEy(String key) {
               String eventID = key;
                Log.d(TAG, "event id: " + eventID);
               launchEventActivity(eventID);
            }
        });*/
    }

    @Override
    public void inviteDoneButtonClicked(Button inviteDone, BottomSheetBehavior bottomSheetBehavior, NestedScrollView nestedScrollView) {
        this.inviteDone = inviteDone;
        nestedScrollView.setVisibility(View.GONE);
        inviteDone.setVisibility(View.GONE);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        Log.d(TAG, " invite done clicked");
        if (validEvent()) {
            createEventButton.setVisibility(View.VISIBLE);
        }

    }

    private boolean validEvent() {
        boolean eventNameSet = newEventBuilder.getEventName() != null;
        boolean eventDateSet = newEventBuilder.getEventDate() != null;
        boolean eventTimeSet = newEventBuilder.getEventTime() != null;
        boolean eventGuestsSet = newEventBuilder.getInvitedFriendsUserList() != null;
        return eventDateSet && eventTimeSet && eventNameSet && eventGuestsSet;
    }

    @Override
    public void showCreateEventButton(Button createEventButton) {
        this.createEventButton = createEventButton;
        if (validEvent()) {
            createEventButton.setVisibility(View.VISIBLE);
        } else {
            createEventButton.setVisibility(View.GONE);
        }
    }

    private void launchEventActivity(String eventID) { // change name
        Log.d(TAG, "launch event activity called ");
        Intent intent = new Intent(context, EventActivity.class);
        intent.putExtra("eventID", eventID);
        intent.putExtra("eventType", "new");
        context.startActivity(intent);
        activity.finish();
    }

    private void showNewEventAlert() {
        alertDialog = new Dialog(activity);
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setContentView(R.layout.alert_create_event);
        eventName = alertDialog.findViewById(R.id.alert_event_name_tv);
        eventDate = alertDialog.findViewById(R.id.alert_event_date_time_tv);
        String name = newEventBuilder.getEventName();
        String date = newEventBuilder.getEventDate() + " @ " + newEventBuilder.getEventTime();
        eventName.setText(name);
        eventDate.setText(date);
        friends = alertDialog.findViewById(R.id.alert_recycler_view);
        friends.setAdapter(new FriendsAdapter(newEventBuilder.getInvitedFriendsUserList()));
        friends.setLayoutManager(new GridLayoutManager(context, 3));
        progressBar = alertDialog.findViewById(R.id.alert_progressBar);
        eventReady = alertDialog.findViewById(R.id.alert_event_ready_button);
        rvLayout = alertDialog.findViewById(R.id.alert_rv_button_layout);
        editEvent = alertDialog.findViewById(R.id.alert_edit_button);
        editEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        createEvent = alertDialog.findViewById(R.id.alert_create_button);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "event validation: " + validEvent());
                CreateEventController eventController = new CreateEventController(NewEventPresenter.this);
                eventController.sendEventToFireBase(validEvent(), new EventFragmentListener() {
                    @Override
                    public void getEventIdKEy(String key) {
                        eventID = key;
                        Log.d(TAG, "event id: " + eventID);
                        //
                    }
                });
                progressBar.setVisibility(View.VISIBLE);
                rvLayout.setVisibility(View.GONE);
            }
        });
        eventReady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchEventActivity(eventID);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public void alertShowEventReady(int venueListSize) {
        progressBar.setVisibility(View.INVISIBLE);
        launchEventActivity(eventID);
        alertDialog.dismiss();
        //eventReady.setVisibility(View.VISIBLE);
    }
}
