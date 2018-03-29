package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;

import com.example.c4q.capstone.database.events.Events;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.EventsViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


/**
 * Created by melg on 3/18/18.
 */

public class EventsAdapter extends FirebaseRecyclerAdapter<Events,EventsViewHolder> {

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

    public EventsAdapter(Class<Events> modelClass, int modelLayout, Class<EventsViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(EventsViewHolder viewHolder, Events model, int position) {
        viewHolder.setEvent_name(model.getEvent_name());
    }
}
