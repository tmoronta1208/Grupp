package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;

import static android.content.ContentValues.TAG;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    public ContactListViewHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name_contactlist);



    }

    public void onBind(int position) {


        Log.d(TAG, "onBind: " + position);
        name.setText(String.valueOf(position));
    }
}
