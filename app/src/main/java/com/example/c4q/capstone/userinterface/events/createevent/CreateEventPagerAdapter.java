package com.example.c4q.capstone.userinterface.events.createevent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.eventfragments.CreateEventFragment;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class CreateEventPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;

    public CreateEventPagerAdapter(FragmentManager fragmentManager) {
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
                return new CreateEventFragment();
            case 1:
                return CreateEventOneFragment.newInstance("Pick a venue type");
            case 2:
                return CreateEventTwoFragment.newInstance("Schedule Your Event");
            case 3:
                return CreateEventTwoFragment.newInstance("Add Friends");
            case 4:
                return CreateEventTwoFragment.newInstance("Finalize");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "•";
            case 1:
                return "•";
            case 2:
                return "•";
            case 3:
                return "•";
            case 4:
                return "•";
            default:
                return null;
        }
    }

}
