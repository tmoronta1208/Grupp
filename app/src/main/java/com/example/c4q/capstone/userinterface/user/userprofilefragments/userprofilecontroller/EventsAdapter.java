package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventAddNameFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.EventsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melg on 3/18/18.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {
    List<Events> eventsList = new ArrayList<>();
    Context context;

       /*constructor to take in list and context from retrofit call*/

        public EventsAdapter(List<Events> eventsList, Context context){
            this.context = context;
            this.eventsList = eventsList;


    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_item_view, parent, false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
            final Events event = eventsList.get(position);
        holder.onBind(eventsList.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventID = event.getEvent_id();
                Intent intent = new Intent(context, EventActivity.class);
                intent.putExtra("eventID", eventID);
                intent.putExtra("eventType", "notNew");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}
