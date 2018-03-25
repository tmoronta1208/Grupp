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
    FriendsAdapter friendsAdapter;
    ContactListAdapter contactListAdapter;
    LinearLayoutManager linearLayoutManager;
    List<PublicUser> friendsUserList = new ArrayList<>();
    FBUserDataUtility fbUserDataUtility = new FBUserDataUtility();
    List<String> friendKeys = new ArrayList<>();

    public UserFriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_user_friends, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.friends_recycler_view);
       // friendsAdapter = new FriendsAdapter(friendsUserList);
        contactListAdapter = new ContactListAdapter(friendsUserList, getActivity() );
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        getFriendUsers();
        return rootView;
    }

    public void getFriendUsers(){
        Log.d("user friends frag", "get friend users called");
        fbUserDataUtility.getUserFriendKeys(fbUserDataUtility.currentUserID, new FBUserFriendsListener() {
            @Override
            public void getUserFriendIds(List<String> userFriendIds) {
                friendKeys.addAll(userFriendIds);
                loadFriendsList(friendKeys);
            }

            @Override
            public void getUserFriends(List<PublicUser> userFriendsList) {

            }
        });
    }

    public void loadFriendsList(List<String> keyList){
        Log.d("user friends frag", "loadFriendsFrag" + friendKeys.size());
        for (String s : friendKeys) {
            Log.d("user friends frag", "loadFriendsFrag: friends key strings " + s);
            fbUserDataUtility.getPublicUser(s, new FBUserDataListener() {
                @Override
                public void getUid(String userID) {
                }

                @Override
                public void getPublicUser(PublicUser publicUser) {
                    friendsUserList.add(publicUser);
                    contactListAdapter.notifyDataSetChanged();
                    Log.d("get public user", "friends name" + publicUser.getFirst_name());
                }
            });
        }
    }
}
