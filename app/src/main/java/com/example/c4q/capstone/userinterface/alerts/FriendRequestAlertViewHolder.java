package com.example.c4q.capstone.userinterface.alerts;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class FriendRequestAlertViewHolder extends RecyclerView.ViewHolder {
    CircleImageView icon;
    TextView emailText, fullNameTxt;
    Button acceptBtn, denyBtn;
    Context requestContext;

    public FriendRequestAlertViewHolder(View itemView) {
        super(itemView);
        requestContext = itemView.getContext();
        acceptBtn = itemView.findViewById(R.id.button_accept);
        denyBtn = itemView.findViewById(R.id.button_deny);
    }

    public void setEmail(String email) {
        emailText = itemView.findViewById(R.id.request_email);
        emailText.setText(email);
    }

    public void setFullName(String name) {
        fullNameTxt = itemView.findViewById(R.id.request_name);
        fullNameTxt.setText(name);
    }

    public void setIcon(String url) {
        icon = itemView.findViewById(R.id.request_user_image);
        Glide.with(getRequestContext()).load(url).into(icon);
    }

    public Context getRequestContext() {
        return requestContext;
    }
}
