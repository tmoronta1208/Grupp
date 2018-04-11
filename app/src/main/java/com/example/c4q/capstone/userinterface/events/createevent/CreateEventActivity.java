package com.example.c4q.capstone.userinterface.events.createevent;

import android.content.DialogInterface;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c4q.capstone.LoginActivity;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.EditTextUX;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.TimePickerFragment;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.FriendsAdapter;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.InviteFriendsViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = "CREATE_EVENT_FRAG: ";
    EditText eventName;
    TextView addDate, addTime, dateAndTime, inviteFriends;
    Button createEventButton, inviteDoneButton;
    DatePickerFragment datePickerFragment = new DatePickerFragment();
    RecyclerView recyclerView;
    RecyclerView displayRecyclerView;
    InviteListAdapter contactListAdapter;
    LinearLayoutManager linearLayoutManager;
    String currentUserId = CurrentUser.userID;
    NewEventListener newEventListener;
    EditTextUX editTextUX;
    TimePickerFragment timePickerFragment = new TimePickerFragment();
    NestedScrollView inviteBottomSheet;
    BottomSheetBehavior bottomSheetBehavior;
    FriendsAdapter invitedFriendsAdapter;
    List<PublicUser> invitedFriendsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        newEventListener = new NewEventPresenter(getApplicationContext(), this);
        setViews();
        setViewCLickListeners();
        DatabaseReference contactsRef = FirebaseDatabase.getInstance().getReference().child(USER_CONTACTS).child(currentUserId);
        contactListAdapter = new InviteListAdapter(PublicUserDetails.class, R.layout.invite_friends_item_view,
                InviteFriendsViewHolder.class, contactsRef, newEventListener, getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.invite_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

    }

    public void setViews() {
        addDate = (TextView) findViewById(R.id.add_date_text_view);
        addTime = (TextView) findViewById(R.id.add_time_text_view);
        inviteFriends = (TextView) findViewById(R.id.invite_friends_text_view);
        createEventButton = (Button) findViewById(R.id.create_event_button);
        createEventButton.setVisibility(View.GONE);
        newEventListener.showCreateEventButton(createEventButton);
        eventName = (EditText) findViewById(R.id.event_name_edit_text);
        inviteDoneButton = (Button) findViewById(R.id.invite_done_button);
        inviteBottomSheet = (NestedScrollView) findViewById(R.id.invite_friends_bottom_sheet);
        inviteBottomSheet.setVisibility(View.GONE);
        bottomSheetBehavior = BottomSheetBehavior.from(inviteBottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        displayRecyclerView = (RecyclerView) findViewById(R.id.invited_friends_recycler_view);
        displayRecyclerView.setVisibility(View.GONE);
        invitedFriendsAdapter = new FriendsAdapter(invitedFriendsList);
        displayRecyclerView.setAdapter(invitedFriendsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CreateEventActivity.this, LinearLayoutManager.HORIZONTAL, false);
        displayRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setViewCLickListeners(){
        addDate.setOnClickListener(this);
        addTime.setOnClickListener(this);
        inviteFriends.setOnClickListener(this);
        createEventButton.setOnClickListener(this);
        datePickerFragment.setEventPresnter(newEventListener, addDate);
        timePickerFragment.setEventPresnter(newEventListener, addTime);
        editTextUX = new EditTextUX(eventName, newEventListener, CreateEventActivity.this, findViewById(R.id.create_event_parent), "eventName" );
    }
    public void newEventAlert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(CreateEventActivity.this).create();
        alertDialog.setIcon(getResources().getDrawable(R.drawable.ic_grupp_icon_24));
        alertDialog.setTitle("New Event!");
        alertDialog.setMessage(message);
        //alertDialog.setView();
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        newEventListener.createEventButtonClicked();
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_date_text_view:
                newEventListener.dateButtonClicked(datePickerFragment, getSupportFragmentManager());
                break;
            case R.id.add_time_text_view:
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");
                //newEventListener.timeButtonClicked(timePicker, closeButton, visibleLayout, hiddenLayout);
                break;
            case R.id.invite_friends_text_view:
                newEventListener.inviteFriendsButtonClicked(inviteFriends, bottomSheetBehavior, inviteDoneButton, inviteBottomSheet);
                break;
            case R.id.create_event_button:
                //newEventAlert(NewEventBuilder.getInstance().getEventName());
                newEventListener.createEventButtonClicked();

                break;
            case R.id.invite_done_button:
                newEventListener.inviteDoneButtonClicked(inviteDoneButton, bottomSheetBehavior, inviteBottomSheet);
                inviteDoneButton.setBackground(getResources().getDrawable(R.drawable.ic_check_circle_checked_24dp));
                inviteFriends.setText("Guest List");
                displayRecyclerView.setVisibility(View.VISIBLE);
                invitedFriendsList.clear();
                if (NewEventBuilder.getInstance().getInvitedFriendsUserList() != null ){
                    invitedFriendsList.addAll(NewEventBuilder.getInstance().getInvitedFriendsUserList());
                    invitedFriendsAdapter.notifyDataSetChanged();
                }

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        NewEventBuilder.getInstance().destroyInstance();
        finish();
    }

    public void categoryClick(View view) {
        view.setBackground(getResources().getDrawable(R.drawable.buttonpeach));
    }
}
