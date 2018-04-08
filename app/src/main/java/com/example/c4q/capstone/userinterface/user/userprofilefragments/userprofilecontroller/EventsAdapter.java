package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.EventsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.DEFAULT_ICON;
import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;
import static com.example.c4q.capstone.utils.Constants.USER_ICON;


/**
 * Created by melg on 3/18/18.
 */

public class EventsAdapter extends FirebaseRecyclerAdapter<UserEvent, EventsViewHolder> {
    private DatabaseReference rootRef, iconRef;

    public EventsAdapter(Class<UserEvent> modelClass, int modelLayout, Class<EventsViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);

    }

    @Override
    protected void populateViewHolder(final EventsViewHolder viewHolder, final UserEvent model, final int position) {

        viewHolder.setEvent_date(model.getEvent_date());
        viewHolder.setEvent_name(model.getEvent_name());
        rootRef = FirebaseDatabase.getInstance().getReference();
        iconRef = rootRef.child(USER_ICON).child(model.getEvent_organizer());
        //Log.d("Events Adapter", "url " + model.getTop_venue_photo());
        viewHolder.setImage(model.getEvent_photo());

        iconRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserIcon userIcon = dataSnapshot.getValue(UserIcon.class);
                try {
                    String url = userIcon.getIcon_url();
                    viewHolder.setUserIcon(url);
                } catch (NullPointerException e) {
                    viewHolder.setUserIcon(DEFAULT_ICON);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventID = model.getEvent_id();
                ;
                Intent intent = new Intent(viewHolder.getEventContext(), EventActivity.class);
                intent.putExtra("eventID", eventID);
                intent.putExtra("eventType", "notNew");
                viewHolder.getEventContext().startActivity(intent);
            }
        });
    }
}
