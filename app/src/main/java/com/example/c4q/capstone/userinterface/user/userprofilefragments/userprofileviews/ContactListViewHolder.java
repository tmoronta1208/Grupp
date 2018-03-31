package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListViewHolder extends RecyclerView.ViewHolder {
    private TextView name, email;
    private CircleImageView userIcon;
    private Context contactContext;


    public ContactListViewHolder(View itemView) {
        super(itemView);
    }

    public Context getContactContext() {
        contactContext = itemView.getContext();
        return contactContext;
    }

    public void setName(String first, String last) {
        name = itemView.findViewById(R.id.name_contactlist);
        name.setText(first + " " + last);
    }

    public void setEmail(String emailAddress) {
        email = itemView.findViewById(R.id.email_contactlist);
        email.setText(emailAddress);
    }

    public void setUserIcon(String url) {
        userIcon = itemView.findViewById(R.id.user_icon_contactlist);
        Glide.with(getContactContext()).load(url).into(userIcon);
    }

}
