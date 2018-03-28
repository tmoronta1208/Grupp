package com.example.c4q.capstone.userinterface.user.search;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.c4q.capstone.R;


public class UserSearchViewHolder extends RecyclerView.ViewHolder {
    TextView usernameTxt, firstName, lastName, emailTxt;
    Button requestFriendBtn;

    public UserSearchViewHolder(View itemView) {
        super(itemView);
        requestFriendBtn = itemView.findViewById(R.id.request_friend_btn);
    }

    public void setFirst_name(String first_name) {
        firstName = itemView.findViewById(R.id.search_first_name);
        firstName.setText(first_name);
    }

    public void setLast_name(String last_name) {
        lastName = itemView.findViewById(R.id.search_last_name);
        lastName.setText(last_name);
    }

    public void setEmail(String email) {
        emailTxt = itemView.findViewById(R.id.search_email);
        emailTxt.setText(email);
    }

    public void setUsername(String username) {
        usernameTxt = itemView.findViewById(R.id.search_username);
        usernameTxt.setText(username);
    }
}
