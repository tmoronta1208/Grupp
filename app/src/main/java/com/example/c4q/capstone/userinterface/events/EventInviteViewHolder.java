package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;

import static android.content.ContentValues.TAG;

/**
 * Created by amirahoxendine on 3/30/18.
 */

public class EventInviteViewHolder extends RecyclerView.ViewHolder {
    TextView eventName, eventDate, eventOrganizer;
    ImageView organizerPhoto;
    Button acceptButton;

    public EventInviteViewHolder(View itemView) {
        super(itemView);
        eventName = (TextView) itemView.findViewById(R.id.event_name_textview);
        eventDate = (TextView) itemView.findViewById(R.id.event_date_textview);
        eventOrganizer = (TextView) itemView.findViewById(R.id.event_organizer_textview);
        organizerPhoto = (ImageView) itemView.findViewById(R.id.organizer_icon);
        acceptButton = (Button) itemView.findViewById(R.id.accept_button);
    }

    public void onBind(UserEvent event, Context context){
        eventOrganizer.setText(event.getEvent_organizer_full_name());
        eventName.setText(event.getEvent_name());
        eventDate.setText(event.getEvent_date());
        acceptButton.setTag(event.getEvent_id());
        UserIcon userIcon = event.getEvent_organizer_icon();
        if(userIcon != null){
            String userIconUrl = userIcon.getIcon_url();
            Log.d(TAG, "user url " + userIconUrl);
            Glide.with(context)
                    .load(userIconUrl)
                    .into(organizerPhoto);
        }
    }
}
