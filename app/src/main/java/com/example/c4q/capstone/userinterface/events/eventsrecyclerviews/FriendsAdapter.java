package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FriendsAdapter extends RecyclerView.Adapter<ContactListViewHolder> {
    List<PublicUser> friendsList = new ArrayList<>();
    View.OnClickListener listener;

    public FriendsAdapter(List<PublicUser> friendsList, View.OnClickListener listener) {
        this.friendsList = friendsList;
        this.listener = listener;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_view, parent, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        PublicUser user = friendsList.get(position);
        holder.onBind(user);
        holder.itemView.setTag(user.getFirst_name());
        holder.itemView.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        if (friendsList != null){
            return friendsList.size();
        } return 0;

    }
}
