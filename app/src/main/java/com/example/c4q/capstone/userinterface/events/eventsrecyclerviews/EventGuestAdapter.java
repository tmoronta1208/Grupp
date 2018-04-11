package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import com.example.c4q.capstone.database.events.EventGuest;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by amirahoxendine on 4/6/18.
 */

public class EventGuestAdapter  extends FirebaseRecyclerAdapter<EventGuest, EventGuestsViewHolder> {
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
    String eventID;
    public EventGuestAdapter(Class<EventGuest> modelClass, int modelLayout, Class<EventGuestsViewHolder> viewHolderClass, Query ref, String eventID) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        this.eventID = eventID;
    }

    @Override
    protected void populateViewHolder(EventGuestsViewHolder viewHolder, EventGuest model, int position) {
        viewHolder.onBind(model, eventID);

    }
}
