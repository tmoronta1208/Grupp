package com.example.c4q.capstone.userinterface.events.createevent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.CurrentUserPost;

import java.util.ArrayList;
import java.util.List;

public class CreateEventActivity extends AppCompatActivity {
    public ViewPager vpPager;
    FragmentPagerAdapter adapterViewPager;
    CreateEventPTSingleton eventSingleton = CreateEventPTSingleton.getNewInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = new CreateEventPagerAdapter(getSupportFragmentManager(), eventSingleton);
        vpPager.setAdapter(adapterViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.create_event_tab_layout);
        tabLayout.setupWithViewPager(vpPager);
    }

    public void venueTypeButtonClick(View view) {

        View otherView;

        if(view.getTag().toString().equals("selected")){
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
        if (view.getId() == R.id.bar_choice_button) {
            eventSingleton.setEventVenueType("bar");
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            view.setTag("selected");
            otherView = view.getRootView().findViewById(R.id.restaurant_choice_button);
            otherView.setTag("unselected");
            otherView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (view.getId() == R.id.restaurant_choice_button) {
            eventSingleton.setEventVenueType("restaurant");
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            view.setTag("selected");
            otherView = view.getRootView().findViewById(R.id.bar_choice_button);
            otherView.setTag("unselected");
            otherView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }
        vpPager.setCurrentItem(vpPager.getCurrentItem() + 1);
    }

    public void nextPage(View view){
        vpPager.setCurrentItem(vpPager.getCurrentItem() + 1);
    }

}
