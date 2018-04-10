package com.example.c4q.capstone.userinterface.user;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.ContactListAdapter;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.example.c4q.capstone.utils.SimpleDividerItemDecoration;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


import static com.example.c4q.capstone.utils.Constants.USER_CONTACTS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {

    private View view;
    private EditText searchView;
    private RecyclerView recyclerView;
    private Button searchBtn;
    private ContactListAdapter contactListAdapter;
    private DatabaseReference contactsRef;

    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_contactlist, container, false);

        searchView = view.findViewById(R.id.search_bar_cl);
        searchView.setHint("Name, #Grupptag, Email");
        searchView.setHintTextColor(getActivity().getResources().getColor(R.color.hintcolor));

        String currentUserId = CurrentUser.getInstance().getUserID();

        contactsRef = FirebaseDatabase.getInstance().getReference().child(USER_CONTACTS).child(currentUserId);

        recyclerView = view.findViewById(R.id.contact_list_rec);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(view.getContext());

        contactListAdapter = new ContactListAdapter(PublicUserDetails.class, R.layout.contact_item_view, ContactListViewHolder.class, contactsRef);

        searchBtn = view.findViewById(R.id.search_contacts_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* String query = searchView.getText().toString().trim();
                searchExistingContact(query);*/
               Intent searIntent = new Intent(ContactListFragment.this.getActivity(), UserSearchActivity.class);
               startActivity(searIntent);
            }
        });

        recyclerView.setAdapter(contactListAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView.setLayoutManager(linearLayout);

        return view;
    }

    private void searchExistingContact(String query) {
        Query contactSearchQuery;

        if (query.contains("@")) {
            contactSearchQuery = contactsRef.orderByChild("email").startAt(query).endAt(query + "\uf8ff");
        } else {
            contactSearchQuery = contactsRef.orderByChild("first_name").startAt(query).endAt(query + "\uf8ff");
        }
        contactListAdapter = new ContactListAdapter(PublicUserDetails.class, R.layout.contact_item_view, ContactListViewHolder.class, contactSearchQuery);
        recyclerView.setAdapter(contactListAdapter);
    }
}
