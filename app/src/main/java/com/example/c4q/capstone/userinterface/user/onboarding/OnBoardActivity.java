package com.example.c4q.capstone.userinterface.user.onboarding;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.PreferencesFragment;

public class OnBoardActivity extends AppCompatActivity {
    CreateProfileFragment createProfileFragment;
    private static final int NUM_PAGES = 4;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        // loadCreatProfilFragment();

        viewPager = findViewById(R.id.onboarding_pager);
        pagerAdapter = new OnBoardActivity.OnBoardingActivityAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }

    }

    private class OnBoardingActivityAdapter extends FragmentStatePagerAdapter {

        public OnBoardingActivityAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {


                case 0:
                    return new CreateProfileFragment();
                case 1:
                    return new PreferencesFragment();

                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    /** @Tati this is the onboard activity, it holds all the fragments for onboarding. It starts with the Create Profile
     * Fragment (copy and paste of the EditProfileActvity) when you click next on each fragment, you should call
     * getActivity().getFragmentManager to replace the current fragment with the next.
     */

//    public void loadCreatProfilFragment(){
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.onboard_main_fragment_container,createProfileFragment);
//        fragmentTransaction.commit();
//    }

}
