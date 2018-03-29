package com.example.c4q.capstone.userinterface.events.eventfragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventPresenter;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventAddNameFragment;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPTSingleton;
import com.example.c4q.capstone.userinterface.events.eventsrecyclerviews.FriendsAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.example.c4q.capstone.utils.FBUserFriendsListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFriendsFragment extends Fragment {
    View rootView;
    RecyclerView recyclerView;
    ContactListAdapter contactListAdapter;
    LinearLayoutManager linearLayoutManager;
    //List<PublicUser> friendsUserList = CurrentUser.getInstance().getUserFriendsList();
    ArrayList<PublicUser> friendsUserList = new ArrayList<>();
    ArrayList<String> friendsUserIDList = new ArrayList<>();
    Bundle args;
    FBUserDataUtility userDataUtility = new FBUserDataUtility();
    Boolean listIsNull;
    TextView noInvitedFriends;

    public UserFriendsFragment() {
        // Required empty public constructor
    }

    public static UserFriendsFragment newInstance(ArrayList<String> invitedFriends) {
        Log.d ("UserFriends Fragment", "invited list array size:" + invitedFriends.size());
        UserFriendsFragment fragment = new UserFriendsFragment();
        Bundle userFragBundle = new Bundle();
        userFragBundle.putStringArrayList("invitedFriends", invitedFriends);
        fragment.setArguments(userFragBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        args = getArguments();
        if (args != null) {
            if (args.getStringArrayList("invitedFriends") != null) {
                ArrayList<String> invitedFriends = args.getStringArrayList("invitedFriends");
                convertIdsToUsers(invitedFriends);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_user_friends, container, false);
        noInvitedFriends = (TextView) rootView.findViewById(R.id.no_invites_text_view);

        if (friendsUserList == null || friendsUserIDList.size() == 0) {
            noInvitedFriends.setVisibility(View.VISIBLE);
        } else {
            recyclerView = (RecyclerView) rootView.findViewById(R.id.friends_recycler_view);
            if (friendsUserList != null) {
                if (getActivity() != null) {
                    contactListAdapter = new ContactListAdapter(friendsUserList, getActivity());
                    linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setAdapter(contactListAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }
        }
        return rootView;
    }

    public void getFriendUsers(ArrayList<String> invtedList) {
        newInstance(invtedList);
        //convertIdsToUsers(invtedList);

    }

    public void convertIdsToUsers(ArrayList<String> invitedList){
        friendsUserIDList = invitedList;
        if (friendsUserIDList != null) {
            if (friendsUserIDList.size() != 0) {
                for (String s : friendsUserIDList) {
                    userDataUtility.getPublicUser(s, new FBUserDataListener() {
                        @Override
                        public void getUid(String userID) {
                        }

                        @Override
                        public void getPublicUser(PublicUser publicUser) {
                            friendsUserList.add(publicUser);
                        }
                    });
                }
            }
        }else {

        }
    }
}
