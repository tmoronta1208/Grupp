package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.ContactListFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by melg on 3/18/18.
 */

public class GroupViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private CircleImageView groupImage;
    private TextView groupName;

    public GroupViewHolder(final View itemView) {
        super(itemView);

        groupImage = itemView.findViewById(R.id.group_image);
        context = itemView.getContext();
        groupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent groupDetailIntent = new Intent(context, ContactListFragment.class);
                context.startActivity(groupDetailIntent);

            }
        });
        groupName = itemView.findViewById(R.id.group_name);

    }

    public void onBind(int position) {


    }
}
