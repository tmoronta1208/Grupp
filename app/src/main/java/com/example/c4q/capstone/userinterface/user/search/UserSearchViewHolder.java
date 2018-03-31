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
    TextView emailText, fullNameTxt;
    Button addContactButton;
    Context requestContext;

    public UserSearchViewHolder(View itemView) {
        super(itemView);
        addContactButton = itemView.findViewById(R.id.add_contact_button);
    }

    public void setEmail(String email) {
        emailText = itemView.findViewById(R.id.search_email);
        emailText.setText(email);
    }

    public void setFullName(String first, String last) {
        fullNameTxt = itemView.findViewById(R.id.search_name);
        fullNameTxt.setText(first+" "+last);
    }

    public void setIcon(String url) {
        icon = itemView.findViewById(R.id.search_user_image);
        Glide.with(getRequestContext()).load(url).into(icon);
    }


    public Context getRequestContext() {
        requestContext = itemView.getContext();
        return requestContext;
    }
}
