package com.example.c4q.capstone.userinterface.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPEventsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPGroupFragment;

import org.jetbrains.annotations.NotNull;

import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;
import belka.us.androidtoggleswitch.widgets.MultipleToggleSwitch;
import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import io.ghyeok.stickyswitch.widget.StickySwitch;

/**
 * Created by melg on 3/20/18.
 */

public class MainProfileFragment extends Fragment {
    private UPEventsFragment eventsFragment;
    private UPGroupFragment groupFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ToggleSwitch toggleSwitch;

    private View view;


    public MainProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.profile_main_frag, container, false);
        setFragmentReference();
        loadFirstFragment();

        toggleSwitch = view.findViewById(R.id.toogle_bar);



        toggleSwitch.setOnToggleSwitchChangeListener(new ToggleSwitch.OnToggleSwitchChangeListener(){

            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (toggleSwitch.getCheckedTogglePosition() == 1){
                    swapFragments(groupFragment);
                } else{
                    swapFragments(eventsFragment);
                }
                // Write your code ...
            }
        });

//        final StickySwitch stickySwitch = view.findViewById(R.id.sticky_switch);
//        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
//            @Override
//            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
//                if (stickySwitch.getDirection() == StickySwitch.Direction.RIGHT) {
////                    Toast.makeText(activity, "Direction " + stickySwitch.getDirection(), Toast.LENGTH_SHORT).show();
//                    swapFragments(groupFragment);
//                } else if (stickySwitch.getDirection() == StickySwitch.Direction.LEFT){
////                    Toast.makeText(activity, "Direction "+ stickySwitch.getDirection(), Toast.LENGTH_SHORT).show();
//                    swapFragments(eventsFragment);
//                }
//            }
//
//        });


        return view;
    }

    public void setFragmentReference() {
        eventsFragment = new UPEventsFragment();
        groupFragment = new UPGroupFragment();

    }

    private void loadFirstFragment() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.up_bottom_frag_cont, eventsFragment).commit();
    }

    private void swapFragments(android.support.v4.app.Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.up_bottom_frag_cont, fragment).commit();
    }


}
