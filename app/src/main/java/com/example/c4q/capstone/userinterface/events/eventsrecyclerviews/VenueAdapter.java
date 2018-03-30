package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Venue;

import java.util.List;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public class VenueAdapter extends RecyclerView.Adapter<VenueViewHolder> {
    List<Venue> venueList;
    Context context;

    public VenueAdapter(List<Venue> venueList, Context context) {
        this.venueList = venueList;
        Log.d ("Venue Fragment", "get venue adapter called " + venueList.size());
        this.context = context;
    }

    @Override
    public VenueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.venue_item_view, parent, false);
        return new VenueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VenueViewHolder holder, int position) {
        Venue venue = venueList.get(position);
        holder.onBind(venue, context);
    }

    @Override
    public int getItemCount() {
        return venueList.size();
    }

}
