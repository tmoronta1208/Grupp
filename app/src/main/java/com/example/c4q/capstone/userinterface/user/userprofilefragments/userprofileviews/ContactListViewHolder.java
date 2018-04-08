package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.c4q.capstone.utils.Constants.GROUPS;
import static com.example.c4q.capstone.utils.Constants.GROUP_NAME;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListViewHolder extends RecyclerView.ViewHolder {
    private TextView name, email;
    private CircleImageView userIcon;
    private Context contactContext;
    Button addButton;
    private FirebaseAuth authentication;
    private DatabaseReference rootRef;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseUser currentUser;
    private String currentUserID;



    public ContactListViewHolder(View itemView) {
        super(itemView);
        itemView.setClickable(true);
        authentication = FirebaseAuth.getInstance();
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();

        rootRef = FirebaseDatabase.getInstance().getReference();


        addButton = itemView.findViewById(R.id.add_contact_button);

//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addButton.setVisibility(View.INVISIBLE);
//
////                final String contactID = getRef(position).getKey();
//
//
//                //  rootRef.child(GROUPS).child(currentUserID).push().child(GROUP_NAME).setValue(groupTitle);
//                // UserSearchActivity.addToContactList(contactID, viewHolder.addContactButton, first, last, email, icon, radiusString, zipCode);
//
//
//            }
//        });
    }

    public Context getContactContext() {
        contactContext = itemView.getContext();
        return contactContext;
    }

    public void setName(String first, String last) {
        name = itemView.findViewById(R.id.name_contactlist);
        name.setText(first + " " + last);
    }

    public void setEmail(String emailAddress) {
        email = itemView.findViewById(R.id.email_contactlist);
        email.setText(emailAddress);
    }

    public void setUserIcon(String url) {
        userIcon = itemView.findViewById(R.id.user_icon_contactlist);
        if(url != null){
            Glide.with(getContactContext()).load(url).into(userIcon);
        }

    }

}
