package com.example.c4q.capstone.userinterface.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.navdrawer.NavDrawerPresenter;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPEventsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPGroupFragment;


import de.hdodenhof.circleimageview.CircleImageView;


public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";

    private TextView userName;
    private CircleImageView userImage;
    private Button editButton, contactButton, searchNewFriends;
    private View fragContainer;
    private String userFullName;

    /**
     * ajoxe: Nav Drawer
     */
    NavigationView navigationView;
    private DrawerLayout navDrawerLayout;
    NavDrawerPresenter navDrawerPresenter;
    SupportActivity activity;
    Context context;
    Toolbar toolbar;
    ActionBar actionbar;
    CurrentUser currentUserInstance = CurrentUser.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        context = this;
        activity = this;
        setNavDrawerLayout();

        setUserInfo();
        setEditButton();
        setSearchNewFriends();
        setContactsButton();

        fragContainer = findViewById(R.id.up_bottom_frag_cont);
        setUpGroupFrag();
        setUpEventsFrag();

    }

    private void setUserInfo(){
        userFullName = currentUserInstance.getUserFullName();
        userName = findViewById(R.id.user_name);
        userName.setText(userFullName);
        userImage = findViewById(R.id.circle_imageview);
    }
    private void setEditButton(){
        editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, EditProfileActivity.class));
            }
        });
    }

    private void setSearchNewFriends(){
        searchNewFriends = findViewById(R.id.add_new_friends);

        searchNewFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, UserSearchActivity.class));
            }
        });
    }

    private void setContactsButton(){
        contactButton = findViewById(R.id.contact_list_button);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ContactListFragment contactListFragment = new ContactListFragment();
                FragmentManager eFragmentManager = getSupportFragmentManager();
                FragmentTransaction eFragmentTransaction = eFragmentManager.beginTransaction();
                eFragmentTransaction.replace(R.id.up_bottom_frag_cont, contactListFragment, "Contact FRAG");
                eFragmentTransaction.addToBackStack("contactListFragment");
                eFragmentTransaction.commit();


            }
        });
    }
    public void setUpEventsFrag() {
        UPEventsFragment UPEventsFragment = new UPEventsFragment();
        FragmentManager eFragmentManager = getSupportFragmentManager();
        FragmentTransaction eFragmentTransaction = eFragmentManager.beginTransaction();
        eFragmentTransaction.replace(R.id.events_frag_container, UPEventsFragment, "Events FRAG");
        eFragmentTransaction.commit();
    }

    public void setUpGroupFrag() {

        UPGroupFragment UPGroupFragment = new UPGroupFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.group_frag_cont, UPGroupFragment, "GROUP FRAG");
        fragmentTransaction.commit();
    }
/** ajoxe:  Nav Drawer set up **/
    /*method to load and display navigation drawer - AJ*/
    public void setNavDrawerLayout() {
        navDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navDrawerPresenter = new NavDrawerPresenter(activity, context);
        navDrawerPresenter.setNavDrawerViews(navDrawerLayout, navigationView);
        navDrawerPresenter.setNavigationViewMethods();
        setToolbar();
    }

    /*method to load toolbar as action bar and display nav drawer icon - AJ*/
    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        navDrawerPresenter.setToolbarViews(toolbar, actionbar);
        navDrawerPresenter.setActionbar("My Profile");
    }

    /*method that closes navigation drawer whenever item is selected - AJ*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                navDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
