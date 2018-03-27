package com.example.c4q.capstone.userinterface.user.onboarding;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.c4q.capstone.R;

public class OnBoardActivity extends AppCompatActivity {
    CreateProfileFragment createProfileFragment;
    private static final int NUM_PAGES = 4;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);


        viewPager = findViewById(R.id.onboarding_pager);
        pagerAdapter = new ViewPagerActivityAdapter(getSupportFragmentManager());


    }

    private class ViewPagerActivityAdapter extends PagerAdapter {
        public ViewPagerActivityAdapter(FragmentManager supportFragmentManager) {
        }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return false;
        }
    }
}
