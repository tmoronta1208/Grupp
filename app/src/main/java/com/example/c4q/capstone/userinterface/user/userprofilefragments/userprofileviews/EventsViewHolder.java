package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;


/**
 * Created by melg on 3/18/18.
 */

public class EventsViewHolder extends RecyclerView.ViewHolder {
    private TextView event_name;
    private Context eventContext;

    public EventsViewHolder(View itemView) {
        super(itemView);
    }

    public void setEvent_name(String name) {
        event_name = itemView.findViewById(R.id.event_name);
        event_name.setText(name);
    }

    public Context getEventContext() {
        eventContext = itemView.getContext();
        return eventContext;
    }
}
