package com.example.c4q.capstone.userinterface.user;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.c4q.capstone.LoginActivity;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.TempUserActivity;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventActivity;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;

import com.example.c4q.capstone.userinterface.user.userprofilefragments.fragmentanimation.ScreenSlidePagerAdapter;
import com.firebase.ui.auth.AuthUI;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;




public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";


    private Toolbar toolbar;
//    private FloatingActionButton floatingActionButton;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton creatEvent, addPerson;
    private Context context;
    private Activity activity;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    private CurrentUser currentUserInstance = CurrentUser.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        context = this;
        activity = this;

        floatingActionMenu = findViewById(R.id.floatingaction_menu);
        creatEvent = findViewById(R.id.create_event_mini);
        creatEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, CreateEventActivity.class));
            }
        });
        addPerson = findViewById(R.id.add_group_mini);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, AddPersonActivity.class));

            }
        });






//        floatingActionButton = findViewById(R.id.floatingactionb);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(UserProfileActivity.this, CreateEventActivity.class));
//            }
//        });





    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_profile_pop_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_profile_menu_item:
                startActivity(new Intent(UserProfileActivity.this, EditProfileActivity.class));
                break;
            case R.id.edit_preferences_menu_item:
                //TODO

                startActivity(new Intent(UserProfileActivity.this, TempUserActivity.class));

                break;
            case R.id.add_friends_menu_item:
                startActivity(new Intent(UserProfileActivity.this, UserSearchActivity.class));
                break;
//            case R.id.add_group_menu_item:
//                startActivity(new Intent(UserProfileActivity.this, CreateEventActivity.class));
//                //TODO
//                break;
            case R.id.signout_menu_item:
                AuthUI.getInstance().signOut(this);
                startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));


                //TODO
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}