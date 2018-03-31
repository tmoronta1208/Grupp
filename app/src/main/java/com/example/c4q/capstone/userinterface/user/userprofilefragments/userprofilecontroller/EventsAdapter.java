package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;

import android.content.Intent;
import android.view.View;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.EventsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


/**
 * Created by melg on 3/18/18.
 */

public class EventsAdapter extends FirebaseRecyclerAdapter<Events, EventsViewHolder> {

    public EventsAdapter(Class<Events> modelClass, int modelLayout, Class<EventsViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);

    }

    @Override
    protected void populateViewHolder(final EventsViewHolder viewHolder, final Events model, final int position) {
        viewHolder.setEvent_name(model.getEvent_name());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventID = model.getEvent_id();;
                Intent intent = new Intent(viewHolder.getEventContext(), EventActivity.class);
                intent.putExtra("eventID", eventID);
                intent.putExtra("eventType", "notNew");
                viewHolder.getEventContext().startActivity(intent);
            }
        });
    }
}
