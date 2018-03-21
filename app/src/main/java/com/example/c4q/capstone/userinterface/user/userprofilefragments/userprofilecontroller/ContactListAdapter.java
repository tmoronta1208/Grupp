package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.ContactListViewHolder;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.GroupViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melg on 3/20/18.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListViewHolder>{

    List<Integer> randomNumberList = new ArrayList<>();
    Context context;


    public ContactListAdapter(){

    }

    public  ContactListAdapter(List<Integer> randomNumberList,Context context){
            this.context = context;
            this.randomNumberList = randomNumberList;
    }

    @Override
    public ContactListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_view, parent, false);
        return new ContactListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactListViewHolder holder, int position) {

        // using a dummy list to make sure the contacts will show;

        holder.onBind(randomNumberList.get(position));
    }

    @Override
    public int getItemCount() {
        return randomNumberList.size();
    }
}
