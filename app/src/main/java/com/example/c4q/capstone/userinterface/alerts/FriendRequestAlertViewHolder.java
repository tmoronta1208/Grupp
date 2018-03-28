package com.example.c4q.capstone.userinterface.alerts;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.c4q.capstone.R;

public class FriendRequestAlertViewHolder extends RecyclerView.ViewHolder {
    TextView emailText;
    Button acceptBtn, denyBtn;
    View view;

    public FriendRequestAlertViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        acceptBtn = itemView.findViewById(R.id.button_accept);
        denyBtn = itemView.findViewById(R.id.button_deny);
    }

    public void setEmail(String email){
        emailText = view.findViewById(R.id.textview_email);
        emailText.setText(email);
    }

}
