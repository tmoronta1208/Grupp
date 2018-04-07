package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.groups.Groups;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPGroupDetailsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews.GroupViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melg on 3/18/18.
 */

public class GroupsAdapter extends FirebaseRecyclerAdapter<Groups, GroupViewHolder> {
    List<Integer> groupsList = new ArrayList<>();
    Context context;

    /**
     * @param modelClass      Firebase will marshall the data at a location into
     *                        an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list.
     *                        You will be responsible for populating an instance of the corresponding
     *                        view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location,
     *                        using some combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public GroupsAdapter(Class<Groups> modelClass, int modelLayout, Class<GroupViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    /*constructor to take in list and context from retrofit call*/

//    public GroupsAdapter(Context context, List<Integer> groupsList) {
//        this.context = context;
//        this.groupsList = groupsList;
//    }

    //
//    @Override
//    public GroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item_view, parent, false);
//        return new GroupViewHolder(view);
//    }

//    @Override
//    public void onBindViewHolder(GroupViewHolder holder, int position) {
//        holder.onBind(groupsList.get(position));
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                UPGroupDetailsFragment upGroupDetailsFragment = new UPGroupDetailsFragment();
//                AppCompatActivity fragmentActivity  = (AppCompatActivity) view.getContext();
//                FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.addToBackStack("").replace(R.id.drawer_layout, upGroupDetailsFragment);
//                fragmentTransaction.commit();
//
//
//
//            }
//        });
//    }

    @Override
    protected void populateViewHolder(GroupViewHolder viewHolder, Groups model, int position) {
        viewHolder.setGroupName(model.getGroup_name());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                UPGroupDetailsFragment upGroupDetailsFragment = new UPGroupDetailsFragment();
                AppCompatActivity fragmentActivity = (AppCompatActivity) view.getContext();
                FragmentManager fragmentManager = fragmentActivity.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("").replace(R.id.drawer_layout, upGroupDetailsFragment);
                fragmentTransaction.commit();
            }
        });

    }
}
