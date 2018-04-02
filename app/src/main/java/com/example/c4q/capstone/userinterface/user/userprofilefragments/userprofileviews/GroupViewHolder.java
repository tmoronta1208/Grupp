package com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofileviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPCreateGroupFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by melg on 3/18/18.
 */

public class GroupViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private CircleImageView groupImage;
    private TextView groupName;

    public GroupViewHolder(final View itemView) {
        super(itemView);

        groupImage = itemView.findViewById(R.id.group_image);
        context = itemView.getContext();
        groupName = itemView.findViewById(R.id.group_name);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UPCreateGroupFragment upCreateGroupFragment = new UPCreateGroupFragment();
                //FragmentTransaction fragmentTransaction =
            }
        });


    }

    public void onBind(int position) {
        groupName.setText("Grupp " + String.valueOf(position));


//        groupImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent groupDetailIntent = new Intent(context, UPCreateGroupFragment.class);
////                context.startActivity(groupDetailIntent);
//
////                UPCreateGroupFragment upGroupDisplayFragment = new UPCreateGroupFragment();
////             //   FragmentManager fragmentManager =
////                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.replace(R.id.up_bottom_frag_cont, upGroupDisplayFragment);
////                fragmentTransaction.commit();
//
//
//               // FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//                //transaction.replace(R.id.up_bottom_frag_cont, fragment).commit();
//
//
//            }
//        });


    }
}
