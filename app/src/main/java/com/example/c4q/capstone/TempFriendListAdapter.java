package com.example.c4q.capstone;


import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import static com.example.c4q.capstone.utils.Constants.DEFAULT_ICON;

public class TempFriendListAdapter extends FirebaseRecyclerAdapter<PublicUserDetails, TempFriendListViewHolder> {
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
    public TempFriendListAdapter(Class<PublicUserDetails> modelClass, int modelLayout, Class<TempFriendListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(TempFriendListViewHolder viewHolder, PublicUserDetails model, int position) {
        viewHolder.setEmail(model.getEmail());
        viewHolder.setUsername(model.getFirst_name() + " " + model.getLast_name());
        viewHolder.setUserIcon(model.getIcon_url());
    }
}
