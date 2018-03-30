package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Venue;
import com.squareup.picasso.Picasso;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public class VenueViewHolder extends RecyclerView.ViewHolder {
    TextView venueName, venueAddress, venueVoteCount;
    ImageView venuePhoto;
    public VenueViewHolder(View itemView) {
        super(itemView);
        venueName = (TextView) itemView.findViewById(R.id.venue_name_textview);
        venueAddress = (TextView) itemView.findViewById(R.id.venue_address_textview);
        venueVoteCount = (TextView) itemView.findViewById(R.id.venue_vote_textview);
        venuePhoto = (ImageView) itemView.findViewById(R.id.venue_photo_image_view);
    }

    public void onBind(Venue venue, Context context){
        venueName.setText(venue.getVenue_name());
        venueAddress.setText(venue.getVenue_address());
        if (venue.getVenue_vote() != null){
            venueVoteCount.setText(String.valueOf(venue.getVote_count()));
        } else {
            venueVoteCount.setText("not voted yet");
        }
        if (venue.getVenue_photo_url() != null){
            Picasso.with(context)
                    .load(venue.getVenue_photo_url())
                    .into(venuePhoto);
        }

    }
}
