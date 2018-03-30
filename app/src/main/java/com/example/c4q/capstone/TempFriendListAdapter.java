package com.example.c4q.capstone;


import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.DEFAULT_ICON;
import static com.example.c4q.capstone.utils.Constants.USER_FRIENDS;

public class TempFriendListAdapter extends FirebaseRecyclerAdapter<PublicUser, TempFriendListViewHolder> {
    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public TempFriendListAdapter(Class<PublicUser> modelClass, int modelLayout, Class<TempFriendListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(TempFriendListViewHolder viewHolder, PublicUser model, int position) {
        viewHolder.setEmail(model.getEmail());
        viewHolder.setUsername(model.getFirst_name() + " " + model.getLast_name());

        try {

            viewHolder.setUserIcon(model.getUser_icon().getIcon_url());

        } catch (NullPointerException e) {
            e.printStackTrace();
            viewHolder.setUserIcon(DEFAULT_ICON);
        }

    }
}
