package com.example.c4q.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.c4q.capstone.database.publicuserdata.PublicUser;
import com.example.c4q.capstone.userinterface.CurrentUser;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.example.c4q.capstone.userinterface.user.onboarding.OnBoardActivity;
import com.example.c4q.capstone.utils.Constants;
import com.example.c4q.capstone.utils.currentuser.CurrentUserUtility;
import com.example.c4q.capstone.utils.currentuser.PublicUserListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;



/**
 * Created by melg on 3/28/18.
 */

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 2;
    private SignInButton signInButton;
    private Button emailLogInButton;
    private FirebaseAuth mfirebaseAuth = FirebaseAuth.getInstance();
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private CurrentUser currentUser = CurrentUser.getInstance();

//    private PublicUser publicUser;
//    private String currentUserID;
//    private DatabaseReference publicUserDatabaseReference, searchUserReference;
//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();


    @Override
    protected void onStart() {
        super.onStart();


        mfirebaseAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signInButton = findViewById(R.id.google_button);



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null ) {
                    CurrentUserUtility currentUserUtility = new CurrentUserUtility();
                            currentUserUtility.getCurrentPublicUser(new PublicUserListener() {
                                @Override
                                public void publicUserExists(Boolean userExists) {
                                    Log.w("TAG", "login user exist" + userExists);
                            if (userExists){

                                Log.w("TAG", "login user has profile" + userExists);
                                startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
                            } else {

                                Log.w("TAG", "login user does not have profile " + userExists);
                                startActivity(new Intent(LoginActivity.this, OnBoardActivity.class));
                            }
                                }
                            });
                    startActivity(new Intent(LoginActivity.this, OnBoardActivity.class));


                }

            }
        };


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                Toast.makeText(this, "Auth went wrong :(", Toast.LENGTH_SHORT).show();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mfirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = mfirebaseAuth.getCurrentUser();
                            String userID = user.getUid();
                            /*CurrentUserUtility currentUserUtility = new CurrentUserUtility();
                            currentUserUtility.getCurrentPublicUser(new PublicUserListener() {
                                @Override
                                public void publicUserExists(Boolean userExists) {
                                    Log.w("TAG", "login user exist" + userExists);
                            if (currentUser.isCurrentUserExists() && userExists){
                                Log.w("TAG", "login user exists" + currentUser.isCurrentUserExists());

                                Log.w("TAG", "login user has profile" + userExists);
                                startActivity(new Intent(LoginActivity.this, UserProfileActivity.class));
                            } else {
                                Log.w("TAG", "login user exists" + currentUser.isCurrentUserExists());
                                Log.w("TAG", "login user does not have profile " + userExists);
                                startActivity(new Intent(LoginActivity.this, OnBoardActivity.class));
                            }
                                }
                            });*/

//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                            Toast.makeText(LoginActivity.this, "auth went wrong", Toast.LENGTH_SHORT).show();
//                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    /* database method*/


//    public void getUserData() {
//        ValueEventListener userListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                Log.d(" LOGIN", "USER LISTENER CALLED");
//                publicUser = dataSnapshot.child(currentUserID).getValue(PublicUser.class);
//                if (publicUser != null) {
//                    Log.d(" LOGIN", "user first name" + publicUser.getFirst_name());
//                } else {
//                    Log.d(" LOGIN", "user is null");
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                // Getting Post failed, log a message
//
//                // ...
//            }
//        };
//        publicUserDatabaseReference.addValueEventListener(userListener);
//    }
}
