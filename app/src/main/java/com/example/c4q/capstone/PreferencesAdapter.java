package com.example.c4q.capstone;

import android.util.Log;

import com.example.c4q.capstone.database.privateuserdata.PrivateUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.BEACH_ICON;
import static com.example.c4q.capstone.utils.Constants.BEER_ICON;
import static com.example.c4q.capstone.utils.Constants.CLUB_ICON;
import static com.example.c4q.capstone.utils.Constants.COCKTAIL_ICON;
import static com.example.c4q.capstone.utils.Constants.GAY_BAR_ICON;
import static com.example.c4q.capstone.utils.Constants.HOOKAH_ICON;
import static com.example.c4q.capstone.utils.Constants.HOTEL_BAR_ICON;
import static com.example.c4q.capstone.utils.Constants.KARAOKE_ICON;
import static com.example.c4q.capstone.utils.Constants.LOUNGE_ICON;
import static com.example.c4q.capstone.utils.Constants.PREFERENCES;
import static com.example.c4q.capstone.utils.Constants.PRIVATE_USER;
import static com.example.c4q.capstone.utils.Constants.PUB_ICON;

public class PreferencesAdapter extends FirebaseRecyclerAdapter<PrivateUser, PreferencesViewHolder> {

    public PreferencesAdapter(Class modelClass, int modelLayout, Class viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(PreferencesViewHolder viewHolder, PrivateUser model, int position) {
        DatabaseReference prefRef = FirebaseDatabase.getInstance().getReference()
                .child(PRIVATE_USER)
                .child(CurrentUser.getInstance().getUserID());

        prefRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PrivateUser privateUser = dataSnapshot.getValue(PrivateUser.class);
                Log.d("BLAH", "populateViewHolder: " + privateUser.getPreferences().getBar_prefs());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        String type = model.getPreferences().getBar_prefs().get(1);
//        viewHolder.setPrefType(type);
//        setPrefIcons(viewHolder, type);

    }

    private void setPrefIcons(PreferencesViewHolder viewHolder, String type) {
        switch (type) {
            case "beer":
                viewHolder.setPrefIcon(BEER_ICON);
                break;
            case "karaoke":
                viewHolder.setPrefIcon(KARAOKE_ICON);
                break;
            case "cocktail":
                viewHolder.setPrefIcon(COCKTAIL_ICON);
                break;
            case "lounge":
                viewHolder.setPrefIcon(LOUNGE_ICON);
                break;
            case "club":
                viewHolder.setPrefIcon(CLUB_ICON);
                break;
            case "hotel":
                viewHolder.setPrefIcon(HOTEL_BAR_ICON);
                break;
            case "hookah":
                viewHolder.setPrefIcon(HOOKAH_ICON);
                break;
            case "pub":
                viewHolder.setPrefIcon(PUB_ICON);
                break;
            case "beach":
                viewHolder.setPrefIcon(BEACH_ICON);
                break;
            case "gay":
                viewHolder.setPrefIcon(GAY_BAR_ICON);
                break;
        }
    }
}
