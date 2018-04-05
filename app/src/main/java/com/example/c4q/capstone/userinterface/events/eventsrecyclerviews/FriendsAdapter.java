package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsViewHolder> {
    public List<PublicUser> friendsList = new ArrayList<>();

    public FriendsAdapter(List<PublicUser> friendsList) {
        this.friendsList = friendsList;
    }

    @Override
    public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.invited_friends_display_item, parent, false);
        return new FriendsViewHolder(view);
    }



    @Override
    public void onBindViewHolder(FriendsViewHolder holder, int position) {
        final PublicUser model = friendsList.get(position);
        String first = model.getFirst_name();
        String url = model.getUser_icon().getIcon_url();

        holder.setUserIcon(url);
        holder.setName(first);

    }

    @Override
    public int getItemCount() {
        if (friendsList != null){
            return friendsList.size();
        } return 0;

    }
}
