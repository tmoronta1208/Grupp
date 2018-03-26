package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventInviteFragment extends Fragment {
    private String title;
    private int imageMain;
    private int imageSecondary;
    View rootView;
    Button addFriendsButton, addGroupButton;
    EditText addNote;
    FrameLayout inviteGuestsContainer;


    public CreateEventInviteFragment() {
        // Required empty public constructor
    }

    public static CreateEventInviteFragment newInstance(String title) {
        CreateEventInviteFragment fragment = new CreateEventInviteFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        title = getArguments().getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.fragment_create_event_invite, container, false);
        addNote = (EditText) rootView.findViewById(R.id.add_note);
        addFriendsButton = (Button) rootView.findViewById(R.id.add_friends_button);
        addGroupButton = (Button) rootView.findViewById(R.id.add_group_button);
        inviteGuestsContainer = (FrameLayout) rootView.findViewById(R.id.invite_guests_fragment_container);
        //expandUX = new ExpandUX(inviteGuestsContainer, addFriendsButton, addGroupButton);

        return rootView;
    }

}
