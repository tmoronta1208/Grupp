package com.example.c4q.capstone.userinterface.events;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.events.Venue;
import com.example.c4q.capstone.network.barzz.barzzmodel.Results;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

/**
 * Created by amirahoxendine on 3/18/18.
 */
@Layout(R.layout.vote_swipe_card_view)
public class VenueCardView {



    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    //private Results results;
    private Venue venue;
    private Context context;
    private SwipePlaceHolderView swipeView;

    public VenueCardView(Context context, Venue venue, SwipePlaceHolderView swipeView) {
        this.context = context;
     this.venue = venue;
     Log.d("results testing:", " " + venue.getVenue_name());
        this.swipeView = swipeView;
    }

    @Resolve
    private void onResolved(){

        Glide.with(context).load(venue.getVenue_photo_url()).into(profileImageView);
        nameAgeTxt.setText(venue.getVenue_name());
        locationNameTxt.setText(venue.getVenue_address());

    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        //swipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}
