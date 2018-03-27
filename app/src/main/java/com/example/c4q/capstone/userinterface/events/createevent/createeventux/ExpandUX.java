package com.example.c4q.capstone.userinterface.events.createevent.createeventux;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public class ExpandUX {
    private FrameLayout inviteGuestsContainer;
    private static String TAG = "CREATE_EVENT_UX: ";
    private Button addFriendsButton, addGroupButton;

    public ExpandUX(FrameLayout inviteGuestsContainer, Button addFriendsButton, Button addGroupButton) {
        this.inviteGuestsContainer = inviteGuestsContainer;
        this.addFriendsButton = addFriendsButton;
        this.addGroupButton = addGroupButton;

        addUsersOnClickListener(addFriendsButton);
        addUsersOnClickListener(addGroupButton);
    }

    /*click listener that toggles fragment visibility -AJ*/
    public void addUsersOnClickListener(final Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "add users clicked" + button.getTag().toString());
                if (inviteGuestsContainer.getVisibility() == View.VISIBLE) {
                    inviteGuestsContainer.setVisibility(View.GONE);
                } else {
                    inviteGuestsContainer.setVisibility(View.VISIBLE);
                    //TODO load fragments logic for adding users to guest list
                }
            }
        });
    }
}
