package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;

/**
 * Created by melg on 3/18/18.
 */

public class EventsViewHolder extends RecyclerView.ViewHolder {
    private TextView eventName;

    public EventsViewHolder(View itemView) {
        super(itemView);

        eventName = itemView.findViewById(R.id.event_name);

    }

    public void onBind(int position) {
    }
}
