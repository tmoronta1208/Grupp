package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;

import static android.content.ContentValues.TAG;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListViewHolder extends RecyclerView.ViewHolder {
    private TextView name,email;

    public ContactListViewHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name_contactlist);
        email = itemView.findViewById(R.id.email_contactlist);

    }

    public void onBind(PublicUser user) {


        Log.d(TAG, "onBind: " + user.getLast_name());
        name.setText(user.getFirst_name() + " " + user.getLast_name());
        email.setText(user.getEmail());

    }
}
