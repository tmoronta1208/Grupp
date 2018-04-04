package com.example.c4q.capstone.userinterface.user;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


import com.example.c4q.capstone.R;
import com.example.c4q.capstone.TempUserActivity;
import com.example.c4q.capstone.database.events.UserEvent;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.alerts.InviteNotifications;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventActivity;
import com.example.c4q.capstone.userinterface.user.onboarding.OnBoardActivity;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;

import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPCreateGroupFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.fragmentanimation.ScreenSlidePagerAdapter;
import com.example.c4q.capstone.LoginActivity;
import com.example.c4q.capstone.utils.currentuser.CurrentUserUtility;
import com.example.c4q.capstone.utils.currentuser.UserEventListener;
import com.firebase.ui.auth.AuthUI;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;


public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";


    private Toolbar toolbar;
    //    private FloatingActionButton floatingActionButton;
    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton creatEvent, addPerson;
    private Context context;
    private Activity activity;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;




    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListner);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();

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
                //startActivity(new Intent(UserProfileActivity.this, AddPersonActivity.class));
                UPCreateGroupFragment upCreateGroupFragment = new UPCreateGroupFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack("Grupp Details Fragment").replace(R.id.drawer_layout, upCreateGroupFragment);
                fragmentTransaction.commit();

            }
        });


        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mAuth.getCurrentUser() == null) {
                    startActivity(new Intent(UserProfileActivity.this, LoginActivity.class));
                }
            }
        };
        pushEventInviteNotifications();
        //new InviteNotifications("test", "notification", getApplicationContext());


    }

    public void pushEventInviteNotifications(){
        final Context mContext = getApplicationContext();
        CurrentUserUtility currentUserUtility = new CurrentUserUtility();
        currentUserUtility.getSingleEventInviteList(CurrentUser.userID, new UserEventListener() {
            @Override
            public void getUserEventList(Map<String, UserEvent> userEventMap) {

                if (userEventMap != null){
                    Log.d(TAG, "notifications not null");

                        new InviteNotifications("You're Invited!", "you have new events", getApplicationContext(), null);

                }else{
                    Log.d(TAG, "notifications null");
                }
            }
        });


        //
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
                InviteNotifications inviteNotifications = new InviteNotifications(getApplicationContext());
                inviteNotifications.showNotificationVoteComplete();

                startActivity(new Intent(UserProfileActivity.this, TempUserActivity.class));

                break;
            case R.id.add_friends_menu_item:
                startActivity(new Intent(UserProfileActivity.this, UserSearchActivity.class));
                break;
            case R.id.sign_out:

                mAuth.signOut();

                AuthUI.getInstance()
                        .signOut(UserProfileActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }

                            // do something here

                        });


                //TODO
                break;
        }
        return super.onOptionsItemSelected(item);
    }




}