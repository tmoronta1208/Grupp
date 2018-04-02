package com.example.c4q.capstone.userinterface.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.example.c4q.capstone.utils.FBUserFriendsListener;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {


    private View view;
    private EditText searchView;
    private RecyclerView recyclerView;

    private List<Integer> numbers = new ArrayList<>();

    ContactListAdapter contactListAdapter;
    /**
     * ajoxe:
     * data member variables
     */
    List<PublicUser> friendsUserList = new ArrayList<>();
    FBUserDataUtility fbUserDataUtility = new FBUserDataUtility();
    List<String> friendKeys = new ArrayList<>();

    private Button addPerson;


    public ContactListFragment() {
        // Required empty public constructor
    }




   /* public static ContactListFragment newInstance(String title) {
        ContactListFragment fragment = new ContactListFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contactlist, container, false);

        searchView = view.findViewById(R.id.search_bar_cl);
        searchView.setHint("Name, #Grupptag, Email");
        searchView.setHintTextColor(getActivity().getResources().getColor(R.color.hintcolor));


        String currentUserId = CurrentUser.getInstance().getUserID();


        DatabaseReference contactsRef = FirebaseDatabase.getInstance().getReference().child(USER_CONTACTS).child(currentUserId);

        recyclerView = view.findViewById(R.id.contact_list_rec);


        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());

        contactListAdapter = new ContactListAdapter(PublicUserDetails.class, R.layout.contact_item_view, ContactListViewHolder.class, contactsRef);

        /**ajoxe:
         * after modifying the adapter, set the list as friendsUserList
         * contactListAdapter = new ContactListAdapter(friendsUserList, getContext());
         */

        recyclerView.setAdapter(contactListAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView.setLayoutManager(linearLayout);


        return view;
    }

    /**
     * ajoxe:
     * method to get a list of public user ids from the database
     * this method calls the load friends method which gets the public user object by ID
     * when this method is called, the friendsUserList is populated and the adapter is notified of the data set changed
     * <p>
     * <p>
     * Important: to use this list, you must modify your adapter to accept a list of PublicUser objects.
     */
    public void getListOfPublicUsers() {
        fbUserDataUtility.getListPublicUsers(new FBUserFriendsListener() {
            @Override
            public void getUserFriendIds(List<String> userFriendIds) {
                friendKeys = new ArrayList<>();
                friendKeys.addAll(userFriendIds);
                Log.d("Contacts", "getListOfPublicUsers: friend keys size: " + friendKeys.size());
                loadFriendsList(friendKeys);
            }
        });
    }

    public void loadFriendsList(List<String> keyList) {
        Log.d("user friends frag", "loadFriendsFrag" + friendKeys.size());
        for (String s : friendKeys) {
            Log.d("Contacts", "loadFriendsFrag: friends key strings: " + s);
            fbUserDataUtility.getPublicUser(s, new FBUserDataListener() {
                @Override
                public void getUid(String userID) {
                }

                @Override
                public void getPublicUser(PublicUser publicUser) {
                    friendsUserList.add(publicUser);
                    contactListAdapter.notifyDataSetChanged();
                    Log.d("CONTACTS", "loadFriendsList: friends name: " + publicUser.getFirst_name());
                    Log.d("CONTACTS", "loadFriendsList: public user list size: " + friendsUserList.size());
                }
            });
        }
    }

    public void loadActualfriends() {
        friendsUserList = CurrentUser.getInstance().getUserFriendsList();
        contactListAdapter.notifyDataSetChanged();
    }

}
