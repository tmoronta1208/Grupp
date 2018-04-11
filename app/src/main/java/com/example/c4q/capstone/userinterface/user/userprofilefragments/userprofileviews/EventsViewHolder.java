package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by melg on 3/18/18.
 */

public class EventsViewHolder extends RecyclerView.ViewHolder {
    private TextView event_name, event_date;
    private Context eventContext;
    private CircleImageView userIcon;
    ImageView eventImg;
    public CardView cardView;

    public EventsViewHolder(View itemView) {
        super(itemView);

      cardView =   itemView.findViewById(R.id.profileCard);



    }



    public void setEvent_name(String name) {
        event_name = itemView.findViewById(R.id.event_name);
        event_name.setText(name);
    }

    public Context getEventContext() {
        eventContext = itemView.getContext();
        return eventContext;
    }

    public void setUserIcon(String url) {
        userIcon = itemView.findViewById(R.id.userImageProfile);
        Glide.with(getEventContext()).load(url).into(userIcon);
    }
    public void setEvent_date(String date){
        event_date = itemView.findViewById(R.id.event_date_tv);
        event_date.setText(date);
    }


    public void setImage(String top_venue_photo) {
        eventImg = itemView.findViewById(R.id.cv_event_image);
        Glide.with(getEventContext()).load(top_venue_photo).into(eventImg);
    }
}
