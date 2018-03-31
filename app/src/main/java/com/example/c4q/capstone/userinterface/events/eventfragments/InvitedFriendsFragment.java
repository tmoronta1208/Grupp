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
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvitedFriendsFragment extends Fragment {
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
    DatabaseReference contactsRef;


    public InvitedFriendsFragment() {
        // Required empty public constructor
    }

    public static InvitedFriendsFragment newInstance(ArrayList<String> invitedFriends) {
        Log.d("UserFriends Fragment", "invited list array size:" + invitedFriends.size());
        InvitedFriendsFragment fragment = new InvitedFriendsFragment();
        Bundle userFragBundle = new Bundle();
        userFragBundle.putStringArrayList("invitedFriends", invitedFriends);
        fragment.setArguments(userFragBundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String currentUserId = CurrentUser.getInstance().getUserID();

        contactsRef = FirebaseDatabase.getInstance().getReference().child(USER_CONTACTS).child(currentUserId).child("contacts");

        contactListAdapter = new ContactListAdapter(PublicUserDetails.class, R.layout.contact_item_view, ContactListViewHolder.class, contactsRef);
        args = getArguments();
        if (args != null) {
            if (args.getStringArrayList("invitedFriends") != null) {
                ArrayList<String> invitedFriends = args.getStringArrayList("invitedFriends");
                Log.d("UserFriends Fragment", "on create: invited list array size:" + invitedFriends.size());
                friendsUserIDList = invitedFriends;
                convertIdsToUsers(invitedFriends);
            }
        }


        if (friendsUserIDList == null || friendsUserIDList.size() == 0) {
            Log.d("UserFriends Fragment", "on createview: invited list array is null");

        } else {

            convertIdsToUsers(friendsUserIDList);
            if (friendsUserList != null) {
                if (getActivity() != null) {
                    contactListAdapter = new ContactListAdapter(PublicUserDetails.class, R.layout.contact_item_view, ContactListViewHolder.class, contactsRef);
                    linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                    recyclerView.setAdapter(contactListAdapter);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_user_friends, container, false);


        noInvitedFriends = (TextView) rootView.findViewById(R.id.no_invites_text_view);
        //noInvitedFriends.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.friends_recycler_view);

        return rootView;
    }

    public void getFriendUsers(ArrayList<String> invtedList) {
        newInstance(invtedList);
        convertIdsToUsers(invtedList);

    }

    public void convertIdsToUsers(ArrayList<String> invitedList) {
        friendsUserIDList = new ArrayList<>();
        friendsUserIDList.addAll(invitedList);
        Log.d("UserFriends Fragment", "convertIdsTousers");
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
                if (friendsUserList != null) {
                    if (getActivity() != null) {
                        contactListAdapter = new ContactListAdapter(PublicUserDetails.class, R.layout.contact_item_view, ContactListViewHolder.class, contactsRef);
                        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        recyclerView.setAdapter(contactListAdapter);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
                    }
                }
            }
        } else {

        }
    }
}
