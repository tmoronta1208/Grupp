package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.TempUserActivity;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;
import com.example.c4q.capstone.network.foursquare.foursquaremodel.Contact;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.ContentValues.TAG;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListViewHolder extends RecyclerView.ViewHolder {
    private TextView name,email;
    private CircleImageView profilePic;

    ///////ashley
    private View v;

    public ContactListViewHolder(View itemView) {
        super(itemView);
        v = itemView; //AJ

        name = itemView.findViewById(R.id.name_contactlist);
        email = itemView.findViewById(R.id.email_contactlist);
        profilePic = itemView.findViewById(R.id.user_icon_contactlist);

    }

    public void onBind(PublicUser user, Context context) {

        if(user != null){
            Log.d(TAG, "onBind: " + user.getLast_name());
            name.setText(user.getFirst_name() + " " + user.getLast_name());
            email.setText(user.getEmail());
            UserIcon userIcon = user.getUser_icon();
            if(userIcon != null){
                String userIconUrl = userIcon.getIcon_url();
                Log.d(TAG, "user url " + userIconUrl);
                Glide.with(context)
                        .load(userIconUrl)
                        .into(profilePic);
            }
        }







//        email.setText(user.getEmail());

    }

    /////////Ashley/////////
    public void getEmail(String emailTxt){
        email = v.findViewById(R.id.email_contactlist);
        email.setText(emailTxt);
    }
}
