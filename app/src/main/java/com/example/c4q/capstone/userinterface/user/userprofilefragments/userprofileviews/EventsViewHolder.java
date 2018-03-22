package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.user.EventsListFragment;


/**
 * Created by melg on 3/18/18.
 */

public class EventsViewHolder extends RecyclerView.ViewHolder {
    private TextView eventName;
    private View linearLayout;
    private Context context;

    public EventsViewHolder(View itemView) {
        super(itemView);

        eventName = itemView.findViewById(R.id.event_name);
        linearLayout = itemView.findViewById(R.id.linearlayout);
        context = itemView.getContext();
    }

    public void onBind(Events event) {

        eventName.setText(event.getEvent_name());
    }
}
