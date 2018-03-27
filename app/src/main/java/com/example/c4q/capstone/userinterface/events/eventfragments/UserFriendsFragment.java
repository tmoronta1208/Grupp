package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.FriendsAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.example.c4q.capstone.utils.FBUserFriendsListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFriendsFragment extends Fragment {
    View rootView;
    RecyclerView recyclerView;
    ContactListAdapter contactListAdapter;
    LinearLayoutManager linearLayoutManager;
    List<PublicUser> friendsUserList = CurrentUser.getInstance().getUserFriendsList();

    public UserFriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_user_friends, container, false);
        if (friendsUserList == null){
            friendsUserList = new ArrayList<>();
        }
        recyclerView = (RecyclerView) rootView.findViewById(R.id.friends_recycler_view);
        if(friendsUserList != null){
            contactListAdapter = new ContactListAdapter(friendsUserList, getActivity() );
            linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            recyclerView.setAdapter(contactListAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);
            getFriendUsers();
        }


        return rootView;
    }

    public void getFriendUsers(){
        friendsUserList = CurrentUser.getInstance().getUserFriendsList();
        if (friendsUserList == null){
            friendsUserList = new ArrayList<>();
        }
        contactListAdapter.notifyDataSetChanged();
    }
}
