package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.CurrentUserPost;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by amirahoxendine on 4/6/18.
 */

public class EventGuestsViewHolder extends RecyclerView.ViewHolder{
        private TextView firstName, status, setStatus;
        private CircleImageView userIcon;
        private Context contactContext;
        Button updateDone;
        EditText updateStatus;

    public EventGuestsViewHolder(View itemView) {
        super(itemView);
        firstName = (TextView) itemView.findViewById(R.id.event_guest_name);
        userIcon = (CircleImageView) itemView.findViewById(R.id.event_guest_icon);
        status = itemView.findViewById(R.id.event_guest_status);
        setStatus = itemView.findViewById(R.id.set_status_button);
        updateDone = itemView.findViewById(R.id.update_done);
        updateStatus = itemView.findViewById(R.id.status_edittxt);
    }
    public Context getContactContext() {
        contactContext = itemView.getContext();
        return contactContext;
    }

    public void onBind(EventGuest eventGuest, final String eventID){
        String name = eventGuest.getUser_firstname() + " " + eventGuest.getUser_lastname();
        firstName.setText( name);
        if(eventGuest.getUser_status() != null){
            status.setText(eventGuest.getUser_status());
        }

        if (eventGuest.getUser_icon().getIcon_url() != null){
            Glide.with(getContactContext()).load(eventGuest.getUser_icon().getIcon_url()).into(userIcon);
        }
        if(eventGuest.getUser_id().equals(CurrentUser.userID)){
            setStatus.setVisibility(View.VISIBLE);
        }
        setStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDone.setVisibility(View.VISIBLE);
                updateStatus.setVisibility(View.VISIBLE);
            }
        });
        updateDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gueststatus = updateStatus.getText().toString();
                updateDone.setVisibility(View.GONE);
                updateStatus.setVisibility(View.GONE);
                CurrentUserPost.getInstance().postStatus(eventID,gueststatus, CurrentUser.userID);

            }
        });
    }
}
