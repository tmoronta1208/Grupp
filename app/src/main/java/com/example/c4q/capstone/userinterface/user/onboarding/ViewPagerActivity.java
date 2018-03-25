package com.example.c4q.capstone.userinterface.user.onboarding;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventFragment;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventOneFragment;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventTwoFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.PreferencesFragment;

public class ViewPagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 2;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter pagerAdapter;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewPager = findViewById(R.id.onboarding_pager);
        pagerAdapter = new ViewPagerActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    private class ViewPagerActivityAdapter extends FragmentStatePagerAdapter {


        public ViewPagerActivityAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        //arguments that go into fragment
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CreateProfileFragment.newInstance(new OnboardingListener() {
                        @Override
                        public void getID(String id) {
                            userId = id;
                            Log.d("View Pager", "listener called: id = " + userId);
                        }
                    });
                case 1:
                    Log.d("View Pager", "preferences loaded: id = " + userId);
                    return PreferencesFragment.newInstance(userId);

                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return false;
        }
    }
}
