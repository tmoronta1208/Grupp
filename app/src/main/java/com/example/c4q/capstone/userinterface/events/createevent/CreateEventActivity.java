package com.example.c4q.capstone.userinterface.events.createevent;

import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.EditTextUX;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog.DatePickerFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashSet;
import java.util.Set;

import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

public class CreateEventActivity extends AppCompatActivity {

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
    View.OnClickListener listener;
    String currentUserId = CurrentUser.userID;
    NewEventListener newEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        newEventListener = new NewEventPresenter(getApplicationContext(), this);
        setFriendClick();
        DatabaseReference contactsRef = FirebaseDatabase.getInstance().getReference().child(USER_CONTACTS).child(currentUserId);
        contactListAdapter = new InviteListAdapter(PublicUserDetails.class, R.layout.contact_item_view,
                ContactListViewHolder.class, contactsRef, listener, newEventListener, getApplicationContext());

        setViews();
        setDateTimeClick();
        setCloseButton();
        setEnterNameEditText();
        datePickerFragment.setEventPresnter(newEventListener);
        recyclerView = (RecyclerView) findViewById(R.id.invite_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEventListener.doneButtonClicked();
            }
        });

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
    public void setEnterNameEditText() {
        eventName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //hideSoftKeyboard(activity);


                    String name = eventName.getText().toString();
                    newEventListener.eventNameEntered(name);
                    handled = true;
                }
                return handled;
            }
        });
    }

    private void setFriendClick() {
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO separate logic - send everything to presenter, presenter sends to builder
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        };
    }

    public void setDateTimeClick() {
        //TODO set Date method call to presenter, takes these views as parameters
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEventListener.timeButtonClicked(timePicker, closeButton, visibleLayout, hiddenLayout);
            }
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newEventListener.dateButtonClicked(datePickerFragment, getSupportFragmentManager());
            }
        });
    }

    public void setCloseButton() {
        //TODO set close button method call to presenter, takes these views as parameters
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePicker.getVisibility() == View.VISIBLE) {
                    newEventListener.closeButtonClicked(timePicker, closeButton, visibleLayout, hiddenLayout);
                    if (Build.VERSION.SDK_INT < 23) {
                        newEventListener.timeEntered(timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                    } else {
                        newEventListener.timeEntered(timePicker.getHour(), timePicker.getMinute());
                    }

                }
            }
        });
    }

    public interface NewEventListener {
        void eventNameEntered(String eventName);

        void dateButtonClicked(DatePickerFragment datePickerFragment, FragmentManager fragmentManager);

        void dateEntered(int eventMonth, int eventDay);

        void timeButtonClicked(TimePicker timePicker, Button closeButton, LinearLayout visibleLayout, LinearLayout hiddenLayout);

        void timeEntered(int hour, int minute);

        void closeButtonClicked(TimePicker timePicker, Button closeButton, LinearLayout visibleLayout, LinearLayout hiddenLayout);

        void inviteFriendsButtonClicked();

        void friendInvited(PublicUser publicUser);

        void addNoteClicked();

        void noteAdded(String eventNote);

        void doneButtonClicked();
    }
}
