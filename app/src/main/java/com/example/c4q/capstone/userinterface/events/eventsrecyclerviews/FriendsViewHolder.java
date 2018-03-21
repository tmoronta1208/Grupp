package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FriendsViewHolder extends RecyclerView.ViewHolder {
    TextView userInitials, userFullName, userInfo;

    public FriendsViewHolder(View itemView) {
        super(itemView);
        userFullName = (TextView) itemView.findViewById(R.id.user_full_name_text_view);
        userInitials = (TextView) itemView.findViewById(R.id.user_initial_avatar_text_view);
        userInfo = (TextView) itemView.findViewById(R.id.user_info_text_view);
    }

    public void onBind(PublicUser publicUser){
        StringBuilder sb = new StringBuilder("");
        sb.append(publicUser.getFirst_name().charAt(0));
        sb.append(publicUser.getLast_name().charAt(0));
        String userInit = sb.toString();
        userInitials.setText(userInit);

        String fullName = publicUser.getFirst_name() + " " + publicUser.getLast_name();
        userFullName.setText(fullName);
        userInfo.setText(publicUser.getZip_code());
    }
}
