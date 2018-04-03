package com.example.c4q.capstone.userinterface.events.createevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.EditTextUX;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener{

    private static String TAG = "CREATE_EVENT_FRAG: ";
    EditText eventName;
    TextView addDate, addTime, dateAndTime;
    TimePicker timePicker;
    DatePicker datePicker;
    Button closeButton, createEventButton;
    LinearLayout hiddenLayout;
    LinearLayout visibleLayout;
    DatePickerFragment datePickerFragment = new DatePickerFragment();
    RecyclerView recyclerView;
    InviteListAdapter contactListAdapter;
    LinearLayoutManager linearLayoutManager;
    String currentUserId = CurrentUser.userID;
    NewEventListener newEventListener;
    EditTextUX editTextUX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        newEventListener = new NewEventPresenter(getApplicationContext(), this);
        setViews();
        setViewCLickListeners();
        DatabaseReference contactsRef = FirebaseDatabase.getInstance().getReference().child(USER_CONTACTS).child(currentUserId);
        contactListAdapter = new InviteListAdapter(PublicUserDetails.class, R.layout.contact_item_view,
                ContactListViewHolder.class, contactsRef, newEventListener, getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.invite_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
    }

    public void setViews() {
        addDate = (TextView) findViewById(R.id.add_date_text_view);
        addTime = (TextView) findViewById(R.id.add_time_text_view);
        dateAndTime = (TextView) findViewById(R.id.date_time_text_view);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        timePicker = (TimePicker) findViewById(R.id.time_picker);
        closeButton = (Button) findViewById(R.id.close_button);
        createEventButton = (Button) findViewById(R.id.create_event_button);
        eventName = (EditText) findViewById(R.id.event_name_edit_text);
        hiddenLayout = findViewById(R.id.hidden_linear_layout);
        visibleLayout = findViewById(R.id.visible_layout);
    }

    public void setViewCLickListeners(){
        addDate.setOnClickListener(this);
        addTime.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        createEventButton.setOnClickListener(this);
        datePickerFragment.setEventPresnter(newEventListener);
        editTextUX = new EditTextUX(eventName, newEventListener, CreateEventActivity.this, findViewById(R.id.create_event_parent), "eventName" );
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.add_date_text_view:
                newEventListener.dateButtonClicked(datePickerFragment, getSupportFragmentManager());
                break;
            case R.id.add_time_text_view:
                newEventListener.timeButtonClicked(timePicker, closeButton, visibleLayout, hiddenLayout);
                break;
            case R.id.close_button:
                if (timePicker.getVisibility() == View.VISIBLE) {
                    newEventListener.closeButtonClicked(timePicker, closeButton, visibleLayout, hiddenLayout);
                    newEventListener.timeEntered(timePicker);
                }
                break;
            case R.id.create_event_button:
                newEventListener.doneButtonClicked();
                break;
        }
    }
}
