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

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.model.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.utils.FBUserDataListener;
import com.example.c4q.capstone.utils.FBUserDataUtility;
import com.example.c4q.capstone.utils.FBUserFriendsListener;

import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {


    private View view;
    private RecyclerView recyclerView;
    private List<Integer> numbers = new ArrayList<>();

    ContactListAdapter contactListAdapter;
    /**ajoxe:
     * data member variables
     */
    List<PublicUser> friendsUserList = new ArrayList<>();
    FBUserDataUtility fbUserDataUtility = new FBUserDataUtility();
    List<String> friendKeys = new ArrayList<>();

    private Button addPerson;


    public ContactListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_contactlist, container, false);

        /**ajoxe:
         * populate friendsUserList
         */
        getListOfPublicUsers();

        recyclerView = view.findViewById(R.id.contact_list_rec);
        addPerson = view.findViewById(R.id.addperson_cl);

        /*sends user to add person activity*/
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPersonIntent = new Intent(getActivity().getApplicationContext(), AddPersonActivity.class);
                getActivity().getApplicationContext().startActivity(addPersonIntent);


            }
        });

        for (int i = 0; i <=20 ; i++) {
            numbers.add(i);
        }

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());
        contactListAdapter = new ContactListAdapter(friendsUserList,getContext());
        /**ajoxe:
         * after modifying the adapter, set the list as friendsUserList
         * contactListAdapter = new ContactListAdapter(friendsUserList, getContext());
         */
        recyclerView.setAdapter(contactListAdapter);
        recyclerView.setLayoutManager(linearLayout);



            return view;
    }
    /**ajoxe:
     * method to get a list of public user ids from the database
     * this method calls the load friends method which gets the public user object by ID
     * when this method is called, the friendsUserList is populated and the adapter is notified of the data set changed
     *
     *
     * Important: to use this list, you must modify your adapater to accept a list of PublicUser objects.
     */
    public void getListOfPublicUsers(){
        fbUserDataUtility.getListPublicUsers(new FBUserFriendsListener() {
            @Override
            public void getUserFriendIds(List<String> userFriendIds) {
                friendKeys.addAll(userFriendIds);
                Log.d("Contacts", "getListOfPublicUsers: friend keys size: " + friendKeys.size());
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

}
