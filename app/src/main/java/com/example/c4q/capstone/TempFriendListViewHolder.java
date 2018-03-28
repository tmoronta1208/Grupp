package com.example.c4q.capstone;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class TempFriendListViewHolder extends RecyclerView.ViewHolder {
    CircleImageView userIcon;
    TextView username, email;

    public TempFriendListViewHolder(View itemView) {
        super(itemView);
    }

    public void setEmail(String userEmail) {
        email = itemView.findViewById(R.id.email_contactlist);
        email.setText(userEmail);
    }

    public void setUsername(String user) {
        username = itemView.findViewById(R.id.name_contactlist);
        username.setText(user);
    }

    public void setUserIcon(String url) {
        userIcon = itemView.findViewById(R.id.user_icon_contactlist);
        Glide.with(itemView.getContext()).load(url).into(userIcon);
    }
}
