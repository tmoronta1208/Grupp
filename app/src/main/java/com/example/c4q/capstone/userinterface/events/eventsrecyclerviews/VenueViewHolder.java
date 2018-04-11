package com.example.c4q.capstone.userinterface.events.eventsrecyclerviews;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Venue;
import com.squareup.picasso.Picasso;

/**
 * Created by amirahoxendine on 3/29/18.
 */

public class VenueViewHolder extends RecyclerView.ViewHolder {
    TextView venueName, venueAddress, venueVoteCount,moreInfo, website, description;
    ImageView venuePhoto;
    private LinearLayout fullInfo;
    private RatingBar venueRatingBar;
    RelativeLayout venueImgLayout;
    public VenueViewHolder(View itemView) {
        super(itemView);
        venueName = (TextView) itemView.findViewById(R.id.venue_name_textview);
        venueAddress = (TextView) itemView.findViewById(R.id.venue_address_textview);
        venueVoteCount = (TextView) itemView.findViewById(R.id.venue_vote_textview);
        moreInfo = (TextView) itemView.findViewById(R.id.more_info_tv);
        description = (TextView) itemView.findViewById(R.id.descriptionTxt);
        website = (TextView) itemView.findViewById(R.id.websiteTxt);
        fullInfo = (LinearLayout) itemView.findViewById(R.id.full_info_layout);
        venuePhoto = (ImageView) itemView.findViewById(R.id.venue_photo_image_view);
        venueRatingBar = (RatingBar) itemView.findViewById(R.id.swipe_card_rating_bar);
        venueImgLayout = (RelativeLayout) itemView.findViewById(R.id.venue_image_layout);
    }

    public void onBind(final Venue venue, final Context context){
        venueName.setText(venue.getVenue_name());
        String fullAddress = venue.getVenue_address() + " "
                + venue.getVenue_city() + ", " + venue.getVenue_State();
        venueAddress.setText(fullAddress);
        if (venue.getVenue_vote() != null){
            venueVoteCount.setText(String.valueOf(venue.getVote_count()) + " votes");
        } else {
            venueVoteCount.setText("no votes yet");
        }
        if (venue.getVenue_photo_url() != null){
            Picasso.with(context)
                    .load(venue.getVenue_photo_url())
                    .into(venuePhoto);
        }
        if(venue.getVenue_description() != null){
            description.setText(venue.getVenue_description());
        }
        if (venue.getRating_avg() != 0){
            venueRatingBar.setNumStars(5);
            double rate = venue.getRating_avg();
            double div = 2.0;
            double starRating = rate/div;
            Log.d("venue rating", "double" + starRating);
            float floatRating = (float) starRating;
            Log.d("venue rating", "float" + starRating);
            venueRatingBar.setRating(floatRating);
        } else {
            venueRatingBar.setVisibility(View.GONE);
        }

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getTag().toString().equals("more")){
                    v.setTag("less");
                    String lessInfo = "less info";
                    moreInfo.setText(lessInfo);
                    venueImgLayout.setVisibility(View.GONE);
                    fullInfo.setVisibility(View.VISIBLE);
                } else if(v.getTag().toString().equals("less")){
                    v.setTag("more");
                    String info = "more info";
                    moreInfo.setText(info);
                    venueImgLayout.setVisibility(View.VISIBLE);
                    fullInfo.setVisibility(View.GONE);
                }
            }
        });
       if (venue.getVenue_url() != null){
           website.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                       Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(venue.getVenue_url()));
                       context.startActivity(webIntent);

               }
           });
       } else {
           website.setVisibility(View.GONE);
       }


    }
}
