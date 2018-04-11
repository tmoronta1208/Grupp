package com.example.c4q.capstone.userinterface.user.onboarding;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Toolbar;

import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.example.c4q.capstone.R;

import java.util.ArrayList;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

public class OnBoardActivity extends FragmentActivity {
    CreateProfileFragment createProfileFragment;
    private static final int NUM_PAGES = 3;
    public static ViewPager viewPager;
    private FragmentStatePagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        viewPager = findViewById(R.id.view_pager);

        PagerModelManager manager = new PagerModelManager();

       // manager.addCommonFragment(BarPreferencesFragment.class, AmenityPreferencesFragment.class, getTitles());
        ViewPagerActivityAdapter adapter =new ViewPagerActivityAdapter(getSupportFragmentManager());
        //ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
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

    private List<String> getTitles() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");


        return list;
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
