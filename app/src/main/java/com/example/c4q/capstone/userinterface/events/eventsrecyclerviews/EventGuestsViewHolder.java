package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.EventGuest;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by amirahoxendine on 4/6/18.
 */

public class EventGuestsViewHolder extends RecyclerView.ViewHolder{
        private TextView firstName;
        private CircleImageView userIcon;
        private Context contactContext;

    public EventGuestsViewHolder(View itemView) {
        super(itemView);
        firstName = (TextView) itemView.findViewById(R.id.event_guest_name);
        userIcon = (CircleImageView) itemView.findViewById(R.id.event_guest_icon);
    }
    public Context getContactContext() {
        contactContext = itemView.getContext();
        return contactContext;
    }

    public void onBind(EventGuest eventGuest){
        String name = eventGuest.getUser_firstname() + " " + eventGuest.getUser_lastname();
        firstName.setText( name);
        if (eventGuest.getUser_icon().getIcon_url() != null){
            Glide.with(getContactContext()).load(eventGuest.getUser_icon().getIcon_url()).into(userIcon);
        }
    }
}
