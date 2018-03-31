package com.example.c4q.capstone.userinterface.user.search;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class UserSearchViewHolder extends RecyclerView.ViewHolder {
    CircleImageView icon;
    TextView fullName, emailTxt;
    Context searchContext;
    Button requestFriendBtn;

    public UserSearchViewHolder(View itemView) {
        super(itemView);
        searchContext = itemView.getContext();
        requestFriendBtn = itemView.findViewById(R.id.request_friend_btn);
    }

    public void setIcon(String url) {
        icon = itemView.findViewById(R.id.search_user_image);
        Glide.with(searchContext).load(url).into(icon);
    }

    public void setFullName(String full_name) {
        fullName = itemView.findViewById(R.id.search_name);
        fullName.setText(full_name);
    }

    public void setEmail(String email) {
        emailTxt = itemView.findViewById(R.id.search_email);
        emailTxt.setText(email);
    }

}
