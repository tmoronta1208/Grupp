package com.example.c4q.capstone.userinterface.user.userprofilefragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.c4q.capstone.userinterface.events.createevent.CreateEventOneFragment;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventTwoFragment;
import com.example.c4q.capstone.userinterface.user.ContactListFragment;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class UserProfilePagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public UserProfilePagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages.
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for a particular page.
    @Override
    //arguments that go into fragment
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return UPEventsFragment.newInstance("Events");
            case 1:
                return ContactListFragment.newInstance("Friends");
            case 2:
                return UPGroupFragment.newInstance("Groups");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Events";
            case 1:
                return "Friends";
            case 2:
                return "Group";

            default:
                return null;
        }
    }
}
