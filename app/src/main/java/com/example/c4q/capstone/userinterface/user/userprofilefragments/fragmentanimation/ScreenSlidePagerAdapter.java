package com.example.c4q.capstone.userinterface.user.userprofilefragments.fragmentanimation;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.c4q.capstone.userinterface.events.createevent.CreateEventFragment;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventOneFragment;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventTwoFragment;
import com.example.c4q.capstone.userinterface.user.ContactListFragment;
import com.example.c4q.capstone.userinterface.user.MainProfileFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPEventsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPGroupFragment;

/**
 * Created by melg on 3/25/18.
 */

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private int NUM_PAGES = 2;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    // Returns the fragment to display for a particular page.
    @Override
    //arguments that go into fragment
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainProfileFragment();
            case 1:
                return new ContactListFragment();


            default:
                return null;
        }
    }

    // Returns the page title for the top indicator

}