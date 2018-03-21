package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;

import java.util.List;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsViewHolder> {
    List<PublicUser> friendsList;

    public FriendsAdapter(List<PublicUser> friendsList) {
        this.friendsList = friendsList;
    }

    @Override
    public FriendsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_friend_item_view, parent, false);
        return new FriendsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendsViewHolder holder, int position) {
        PublicUser publicUser = friendsList.get(position);
        holder.onBind(publicUser);

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}
