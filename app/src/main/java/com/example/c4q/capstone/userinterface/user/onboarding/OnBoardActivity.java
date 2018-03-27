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

public class OnBoardActivity extends FragmentActivity {
    CreateProfileFragment createProfileFragment;
    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private FragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);


        viewPager = findViewById(R.id.onboarding_pager);
        pagerAdapter = new ViewPagerActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);


    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {

            super.onBackPressed();

        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }

    }

    private class ViewPagerActivityAdapter extends FragmentStatePagerAdapter {
        public ViewPagerActivityAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new CreateProfileFragment();
                    break;
                case 1:
                    fragment = new BarPreferencesFragment();
                    break;
                case 2:
                    fragment = new AmenityPreferencesFragment();
                    break;

                default:
                    fragment = null;

            }

            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
