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

import com.example.c4q.capstone.LoginActivity;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.VenueVoteSwipeActivity;
import com.example.c4q.capstone.userinterface.navdrawer.NavDrawerPresenter;
import com.example.c4q.capstone.userinterface.user.search.UserSearchActivity;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPEventsFragment;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.UPGroupFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.c4q.capstone.utils.Constants.PUBLIC_USER;

public class UserProfileActivity extends AppCompatActivity {
    private static final String TAG = "UserProfileActivity";

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth authentication;
    private FirebaseUser currentUser;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference publicUserDatabaseReference;
    private String currentUserID;

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

        userFullName = currentUserInstance.getUserFullName();
        Log.d(TAG, "current user test" + userFullName);



        userImage = findViewById(R.id.circle_imageview);
        userName = findViewById(R.id.user_name);
        editButton = findViewById(R.id.edit_button);
        fragContainer = findViewById(R.id.up_bottom_frag_cont);
        userName.setText(userFullName);

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

        searchNewFriends = findViewById(R.id.add_new_friends);

        searchNewFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, UserSearchActivity.class));
            }
        });

        /*authentication = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        publicUserDatabaseReference = firebaseDatabase.getReference().child(PUBLIC_USER);
        currentUser = authentication.getCurrentUser();
        currentUserID = currentUser.getUid();*/

        setNavDrawerLayout();
        setUpGroupFrag();
        setUpEventsFrag();
        //setFirebaseDatabaseListner();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserProfileActivity.this, EditProfileActivity.class));
            }
        });

    }

    /*public void setFirebaseDatabaseListner() {
        publicUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        PublicUser publicUser = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
        String userFullName = publicUser.getFirst_name() + " " + publicUser.getLast_name();
        userName.setText(userFullName);
    }*/

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
