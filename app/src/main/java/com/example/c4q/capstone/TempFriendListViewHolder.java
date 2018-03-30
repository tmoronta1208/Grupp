package com.example.c4q.capstone;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;

import de.hdodenhof.circleimageview.CircleImageView;

public class TempFriendListViewHolder extends RecyclerView.ViewHolder {
    CircleImageView userIcon;
    TextView username, email;
    Context friendListContext;

    public TempFriendListViewHolder(View itemView) {
        super(itemView);
    }

    public void setEmail(String e) {
        email = itemView.findViewById(R.id.email_contactlist);
        email.setText(e);
    }

    public void setUsername(String n) {
        username = itemView.findViewById(R.id.name_contactlist);
        username.setText(n);
    }

    public void setUserIcon(String url) {
        userIcon = itemView.findViewById(R.id.user_icon_contactlist);
        Glide.with(getFriendListContext()).load(url).into(userIcon);
    }

    public Context getFriendListContext() {
        friendListContext = itemView.getContext();
        return friendListContext;
    }
}
