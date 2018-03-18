package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by melg on 3/18/18.
 */

public class GroupViewHolder extends RecyclerView.ViewHolder {

    private CircleImageView groupImage;
    private TextView groupName;

    public GroupViewHolder(View itemView) {
        super(itemView);

        groupImage = itemView.findViewById(R.id.group_image);
        groupName = itemView.findViewById(R.id.group_name);

    }

    public void onBind(int position) {

        ;
    }
}
