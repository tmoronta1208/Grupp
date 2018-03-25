package com.example.c4q.capstone.userinterface.user;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventActivity;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPEventsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPGroupFragment;
import com.firebase.ui.auth.AuthUI;


import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;
import io.ghyeok.stickyswitch.widget.StickySwitch;


public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";

    private TextView userName;
    private CircleImageView userImage;
    private Button editButton, contactButton, searchNewFriends;
    private View fragContainer;
    private String userFullName;

    private Toolbar toolbar;
    private SupportActivity activity;
    private Context context;
    private BottomNavigationView navigation;
    private FloatingActionButton floatingActionButton;

    private ContactListFragment contactListFragment;
    private UPEventsFragment eventsFragment;
    private UPGroupFragment groupFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private CurrentUser currentUserInstance = CurrentUser.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setFragmentReference();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadFirstFragment();

        context = this;
        activity = this;

        floatingActionButton = findViewById(R.id.floatingactionb);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, CreateEventActivity.class));
            }
        });

//        setUserInfo();

        final StickySwitch stickySwitch = findViewById(R.id.sticky_switch);
        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(@NotNull StickySwitch.Direction direction, @NotNull String text) {
                if (stickySwitch.getDirection() == StickySwitch.Direction.RIGHT) {
//                    Toast.makeText(activity, "Direction " + stickySwitch.getDirection(), Toast.LENGTH_SHORT).show();
                    swapFragments(groupFragment);
                } else if (stickySwitch.getDirection() == StickySwitch.Direction.LEFT){
//                    Toast.makeText(activity, "Direction "+ stickySwitch.getDirection(), Toast.LENGTH_SHORT).show();
                    swapFragments(eventsFragment);
                }
            }

        });


    }


    public void setFragmentReference() {
        contactListFragment = new ContactListFragment();
        eventsFragment = new UPEventsFragment();
        groupFragment = new UPGroupFragment();

    }

    private void loadFirstFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.up_bottom_frag_cont, eventsFragment)
                .addToBackStack("next")
                .commit();
    }

    private void swapFragments(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.up_bottom_frag_cont, fragment)
                .addToBackStack("next")
                .commit();
    }

////    private void setUserInfo() {
//        userFullName = currentUserInstance.getUserFullName();
//        userName = findViewById(R.id.user_name);
//        userName.setText(userFullName);
//        userImage = findViewById(R.id.circle_imageview);
//    }


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
                break;
            case R.id.add_friends_menu_item:
                startActivity(new Intent(UserProfileActivity.this, UserSearchActivity.class));
                break;
            case R.id.add_group_menu_item:
                startActivity(new Intent(UserProfileActivity.this, CreateEventActivity.class));
                //TODO
                break;
            case R.id.signout_menu_item:
                AuthUI.getInstance().signOut(this);
                //TODO
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}



