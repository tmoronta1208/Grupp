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
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.navdrawer.NavDrawerPresenter;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.MainUserProfileFragment;



import de.hdodenhof.circleimageview.CircleImageView;


public class UserProfileActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
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

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    MainUserProfileFragment mainUserProfileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        context = this;
        activity = this;
        setNavDrawerLayout();

        setUserInfo();
        setMainUserProfileFragment();


    }

    private void setMainUserProfileFragment(){
        mainUserProfileFragment = new MainUserProfileFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.up_bottom_frag_cont, mainUserProfileFragment);
        fragmentTransaction.commit();
    }

    private void setUserInfo(){
        userFullName = currentUserInstance.getUserFullName();
        userName = findViewById(R.id.user_name);
        userName.setText(userFullName);
        userImage = findViewById(R.id.circle_imageview);
    }

    public void showMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.user_profile_pop_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
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
                //TODO
                break;
        }
        return true;
    }
/** ajoxe:  Nav Drawer set up **/
    /*method to load and display navigation drawer - AJ*/
    public void setNavDrawerLayout() {
        navDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navDrawerPresenter = new NavDrawerPresenter(activity, context);
        navDrawerPresenter.setNavDrawerViews(navDrawerLayout, navigationView);
        navDrawerPresenter.setNavigationViewMethods();
//        setToolbar();
    }

    /*method to load toolbar as action bar and display nav drawer icon - AJ*/
//    public void setToolbar() {
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        actionbar = getSupportActionBar();
//        navDrawerPresenter.setToolbarViews(toolbar, actionbar);
//        navDrawerPresenter.setActionbar("My Profile");
//    }

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
