package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;


import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.firebase.ui.auth.data.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import static com.example.c4q.capstone.utils.Constants.USER_ICON;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListAdapter extends FirebaseRecyclerAdapter<PublicUserDetails, ContactListViewHolder> {

    public ContactListAdapter(Class<PublicUserDetails> modelClass, int modelLayout, Class<ContactListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(final ContactListViewHolder viewHolder, PublicUserDetails model, int position) {
        String contactID = getRef(position).getKey();
        DatabaseReference iconRef = FirebaseDatabase.getInstance().getReference().child(USER_ICON).child(contactID);

        iconRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserIcon userIcon = dataSnapshot.getValue(UserIcon.class);
                String url = userIcon.getIcon_url();
                viewHolder.setUserIcon(url);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        String first = model.getFirst_name();
        String last = model.getLast_name();
        String email = model.getEmail();

        viewHolder.setName(first, last);
        viewHolder.setEmail(email);
    }
}
