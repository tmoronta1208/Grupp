package com.example.c4q.capstone.userinterface.events.createevent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public class CreateEventPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 4;
    private CreateEventPTSingleton createEventPTSingleton;

    public CreateEventPagerAdapter(FragmentManager fragmentManager, CreateEventPTSingleton eventPTSingleton) {
        super(fragmentManager);
        createEventPTSingleton = eventPTSingleton;
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
                return CreateEventInviteFragment.newInstance(createEventPTSingleton);
            case 1:
                return CreateEventAddVenueFragment.newInstance(createEventPTSingleton);
            case 2:
                return CreateEventAddNoteFragment.newInstance(createEventPTSingleton);
            case 3:
                return CreateEventAddNameFragment.newInstance(createEventPTSingleton);

            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
            case 4:
                return "";
            default:
                return null;
        }
    }

}
