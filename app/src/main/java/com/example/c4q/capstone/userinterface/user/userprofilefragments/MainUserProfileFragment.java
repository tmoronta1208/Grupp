package com.example.c4q.capstone.userinterface.user.userprofilefragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPagerAdapter;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainUserProfileFragment extends Fragment {
    View rootView;
    ViewPager viewPager;
    FragmentPagerAdapter adapterViewPager;
    TextView test;


    public MainUserProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main_user_profile, container, false);

        viewPager = (ViewPager) rootView.findViewById(R.id.user_profile_view_pager);
        adapterViewPager = new UserProfilePagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setPageTransformer(true, new FlipVerticalTransformer());
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.user_profile_tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }

}
