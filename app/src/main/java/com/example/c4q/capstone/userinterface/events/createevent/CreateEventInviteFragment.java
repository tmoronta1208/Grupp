package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.FriendsAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventInviteFragment extends Fragment {

    View rootView;
    FrameLayout inviteGuestsContainer;
    private NewEventBuilder newEventBuilder;
    CreateEventPresenter eventPresenter;
    RecyclerView recyclerView;
    InviteListAdapter contactListAdapter;
    LinearLayoutManager linearLayoutManager;
    List<PublicUser> friendsUserList = CurrentUser.getInstance().getUserFriendsList();
    View.OnClickListener listener;
    Set<String> friendIdInvite = new HashSet<>();
    FriendsAdapter friendsAdapter;
    String currentUserId = CurrentUser.userID;



    public CreateEventInviteFragment() {
        // Required empty public constructor
    }

    public static CreateEventInviteFragment newInstance(NewEventBuilder eventPTSingleton) {
        CreateEventInviteFragment fragment = new CreateEventInviteFragment();
        fragment.loadEventSingleton(eventPTSingleton);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventPresenter = new CreateEventPresenter(NewEventBuilder.getInstance());
        setOnClick();
        DatabaseReference contactsRef = FirebaseDatabase.getInstance().getReference().child(USER_CONTACTS).child(currentUserId);
        contactListAdapter = new InviteListAdapter(PublicUserDetails.class, R.layout.contact_item_view, ContactListViewHolder.class, contactsRef, listener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      rootView = inflater.inflate(R.layout.fragment_create_event_invite, container, false);


        recyclerView = (RecyclerView) rootView.findViewById(R.id.invite_recycler_view);
        //friendsAdapter = new FriendsAdapter(friendsUserList,eventPresenter, getActivity().getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        getFriendUsers();
        return rootView;
    }

    public void getFriendUsers(){
        //friendsUserList = CurrentUser.getInstance().getUserFriendsList();
       // Log.d("invite frag", "user list" + friendsUserList.size());
        //friendsAdapter.notifyDataSetChanged();
    }

    public void loadEventSingleton(NewEventBuilder eventPTSingleton){
        newEventBuilder = eventPTSingleton;
        eventPresenter = new CreateEventPresenter(newEventBuilder);
    }
    private void setOnClick(){
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO separate logic - send everything to presenter, presenter sends to builder
                v.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                friendIdInvite.add(v.getTag().toString());
                List<String> friendId = new ArrayList<>();
                friendId.addAll(friendIdInvite);
                newEventBuilder.setInvitedGuests(friendId);
                eventPresenter.setEventGuests(friendId);



            }
        };
    }

}
