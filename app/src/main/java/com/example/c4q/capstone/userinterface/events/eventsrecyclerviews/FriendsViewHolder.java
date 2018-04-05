package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FriendsViewHolder extends RecyclerView.ViewHolder {
    private TextView firstName;
    private CircleImageView userIcon;
    private Context contactContext;

    public FriendsViewHolder(View itemView) {
        super(itemView);
    }

    public Context getContactContext() {
        contactContext = itemView.getContext();
        return contactContext;
    }

    public void setName(String first) {
        firstName = itemView.findViewById(R.id.name_first);
        firstName.setText(first);

    }

    public void setUserIcon(String url) {
        userIcon = itemView.findViewById(R.id.user_icon_contactlist);
        Log.d("friend invite adapter",  "url: " + url);
        if (url != null){
           /* Picasso.with(contactContext)
                    .load(url)
                    .resize(800, 500)
                    .into(userIcon);*/
            Glide.with(getContactContext()).load(url).into(userIcon);
        } else{
            userIcon.setImageDrawable(contactContext.getResources().getDrawable(R.drawable.default_avatar));
        }

    }
}
