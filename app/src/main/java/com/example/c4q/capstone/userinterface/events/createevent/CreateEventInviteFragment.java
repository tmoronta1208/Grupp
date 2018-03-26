package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.FriendsAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventInviteFragment extends Fragment {

    View rootView;
    FrameLayout inviteGuestsContainer;
    private CreateEventPTSingleton createEventPTSingleton;
    CreateEventPresenter eventPresenter;
    RecyclerView recyclerView;
    ContactListAdapter contactListAdapter;
    LinearLayoutManager linearLayoutManager;
    List<PublicUser> friendsUserList = CurrentUser.getInstance().getUserFriendsList();
    View.OnClickListener listener;
    Set<String> friendIdInvite = new HashSet<>();
    FriendsAdapter friendsAdapter;


    public CreateEventInviteFragment() {
        // Required empty public constructor
    }

    public static CreateEventInviteFragment newInstance(CreateEventPTSingleton eventPTSingleton) {
        CreateEventInviteFragment fragment = new CreateEventInviteFragment();
        fragment.loadEventSingleton(eventPTSingleton);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventPresenter = new CreateEventPresenter(CreateEventPTSingleton.getInstance());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.fragment_create_event_invite, container, false);
      setOnClick();

        recyclerView = (RecyclerView) rootView.findViewById(R.id.invite_recycler_view);
        friendsAdapter = new FriendsAdapter(friendsUserList,listener);
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setAdapter(friendsAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        getFriendUsers();
        return rootView;
    }

    public void getFriendUsers(){
        friendsUserList = CurrentUser.getInstance().getUserFriendsList();
        friendsAdapter.notifyDataSetChanged();
    }

    public void loadEventSingleton(CreateEventPTSingleton eventPTSingleton){
        createEventPTSingleton = eventPTSingleton;
        eventPresenter = new CreateEventPresenter(createEventPTSingleton);
    }
    private void setOnClick(){
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                friendIdInvite.add(v.getTag().toString());
                List<String> friendId = new ArrayList<>();
                friendId.addAll(friendIdInvite);
                createEventPTSingleton.setInvitedGuests(friendId);
                eventPresenter.setEventGuests(friendId);
            }
        };
    }

}
