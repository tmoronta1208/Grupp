package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.createevent.NewEventBuilder;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amirahoxendine on 3/21/18.
 */

public class FriendsAdapter extends RecyclerView.Adapter<ContactListViewHolder> {
    List<PublicUser> friendsList = new ArrayList<>();
    CreateEventPresenter eventPresenter;
    Context context;

    public FriendsAdapter(List<PublicUser> friendsList, CreateEventPresenter eventPresenter, Context context) {
        this.friendsList = friendsList;
        this.eventPresenter = eventPresenter;
        this.context = context;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_view, parent, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {
        final PublicUser user = friendsList.get(position);
        /*holder.onBind(user, context);
        holder.itemView.setTag(user.getUser_id());
        holder.onBind(user, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setBackgroundColor(context.getResousetOnClick();rces().getColor(R.color.colorAccent));
                List<String> friendId = NewEventBuilder.getInstance().getInvitedGuests();
                if (friendId != null){
                    if(!friendId.contains(user.getUser_id())){
                        friendId.add(user.getUser_id());
                    }
                } else{
                    friendId = new ArrayList<>();
                    friendId.add(user.getUser_id());
                }

                NewEventBuilder.getInstance().setInvitedGuests(friendId);
                eventPresenter.setEventGuests(friendId);
                List<PublicUser> invitedFriendUser = NewEventBuilder.getInstance().getInvitedFriendsUserList();
                if (invitedFriendUser != null){
                    invitedFriendUser.add(user);
                    Log.d("invite adapter", "pub user list size: " + invitedFriendUser.size());
                } else {
                    invitedFriendUser = new ArrayList<>();
                    invitedFriendUser.add(user);
                    Log.d("invite adapter", "pub user list size: " + invitedFriendUser.size());
                }
                NewEventBuilder.getInstance().setInvitedFriendsUserList(invitedFriendUser);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if (friendsList != null){
            return friendsList.size();
        } return 0;

    }
}
