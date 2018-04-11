package com.example.c4q.capstone.userinterface.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.example.c4q.capstone.userinterface.events.createevent.NewEventConverter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.EventsAdapter;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import static com.example.c4q.capstone.utils.Constants.EVENT_INVITATIONS;

public class EventInviteActivity extends AppCompatActivity {
    private RecyclerView eventInviteRecyclerView;
    private EventsAdapter eventsAdapter;
    private List<UserEvent> eventInvites;
    private static final String TAG = "EVENT INVITE";
    private DatabaseReference rootRef, invites;
    private String currentUserID = CurrentUser.userID;
    LinearLayoutManager linearLayout;
    View.OnClickListener acceptClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_invite);

        rootRef = FirebaseDatabase.getInstance().getReference();
        invites = rootRef.child(EVENT_INVITATIONS).child(currentUserID);

        eventInviteRecyclerView = findViewById(R.id.rec_view);
        eventInviteRecyclerView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(getApplicationContext());
        linearLayout.setStackFromEnd(true);
        linearLayout.setReverseLayout(true);
        eventInviteRecyclerView.setLayoutManager(linearLayout);
        eventInviteRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));


        callFireBaseAdapter();
    }

    private void callFireBaseAdapter() {
        FirebaseRecyclerAdapter<UserEvent, EventInviteViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<UserEvent, EventInviteViewHolder>(UserEvent.class, R.layout.event_invite_item_view, EventInviteViewHolder.class, invites) {

                    @Override
                    protected void populateViewHolder(EventInviteViewHolder viewHolder, UserEvent model, int position) {
                        setClickListener(model);
                        viewHolder.onBind(model, getApplicationContext());
                        String name = model.getEvent_organizer_full_name();
                        Log.d(TAG, "organizer name: " + name);
                        viewHolder.acceptButton.setOnClickListener(acceptClickListener);
                    }
                };

        eventInviteRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public void setClickListener(final UserEvent userEvent) {
        acceptClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change state of button
                v.setBackground(getResources().getDrawable(R.drawable.ic_check_circle_checked_24dp));
                String eventKey = userEvent.getEvent_id();
                CurrentUserPost currentUserPost = CurrentUserPost.getInstance();
                //post event to user event;
                currentUserPost.postEventToUserEventList(eventKey, currentUserID, userEvent);
                //convert public user to event guest
                NewEventConverter newEventConverter = new NewEventConverter();
                EventGuest userGuest = newEventConverter.eventGuestFromPUblicUser(CurrentUser.getInstance().getCurrentPublicUser(), true);
                //post new event guest to event
                currentUserPost.postEventGuest(eventKey, currentUserID, userGuest);
                //remove event from invite list
                currentUserPost.removeEventInvite(eventKey, currentUserID);
            }
        };
    }
}
