package com.example.c4q.capstone.userinterface.user.onboarding;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.example.c4q.capstone.R;


import java.lang.reflect.Field;
import java.text.Format;

import github.chenupt.multiplemodel.viewpager.PagerModelManager;


public class OnBoardActivity extends FragmentActivity {
    private int duration = 5000;
    CreateProfileFragment createProfileFragment;
    private static final int NUM_PAGES = 3;
    public static ViewPager viewPager;
    private FragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        viewPager = findViewById(R.id.onboarding_viewpager);


        try {
            Field mscroller = ViewPager.class.getDeclaredField("mScroller");
            mscroller.setAccessible(true);
            mscroller.set(viewPager, new FixSpeedScroller(viewPager.getContext()));


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


//        try {
//            Field mScroller;
//            mScroller = ViewPager.class.getDeclaredField("mScroller");
//            mScroller.setAccessible(true);
//            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(), sInterpolator);
//            // scroller.setFixedDuration(5000);
//            mScroller.set(viewPager, scroller);
//        } catch (NoSuchFieldException e) {
//        } catch (IllegalArgumentException e) {
//        } catch (IllegalAccessException e) {
//        }

        PagerModelManager manager = new PagerModelManager();

        ViewPagerActivityAdapter adapter = new ViewPagerActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        viewPager.setPageTransformer(true, new CubeOutTransformer());


    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {

            super.onBackPressed();

        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }

    }


    public class FixSpeedScroller extends Scroller {

        int mDuration = 2000;

        public FixSpeedScroller(Context context) {
            super(context);
        }


        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);

        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);


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
                    fragment = new RestaurantPreferencesFragment();
                    break;

                default:
                    fragment = null;

            }

            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return getTitle();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
