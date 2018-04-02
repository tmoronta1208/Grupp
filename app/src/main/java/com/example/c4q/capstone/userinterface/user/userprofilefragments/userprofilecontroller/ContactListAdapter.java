package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;


import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.database.publicuserdata.PublicUserDetails;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListAdapter extends FirebaseRecyclerAdapter<PublicUserDetails, ContactListViewHolder> {

    public ContactListAdapter(Class<PublicUserDetails> modelClass, int modelLayout, Class<ContactListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ContactListViewHolder viewHolder, PublicUserDetails model, int position) {
        String first = model.getFirst_name();
        String last = model.getLast_name();
        String email = model.getEmail();
        String url = model.getIcon_url();

        viewHolder.setUserIcon(url);
        viewHolder.setName(first,last);
        viewHolder.setEmail(email);
    }


    /*@Override
    protected void populateViewHolder(ContactListViewHolder viewHolder, UserContacts model, int position) {
        *//*String email = model.getContacts().get(position).getEmail();
        String first = model.getContacts().get(position).getFirst_name();
        String last = model.getContacts().get(position).getLast_name();
        String icon = model.getContacts().get(position).getIcon_url();

        viewHolder.setEmail(email);
        viewHolder.setName(first, last);
        viewHolder.setUserIcon(icon);
    }*/


}
