package com.example.c4q.capstone.userinterface.user.onboarding;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.c4q.capstone.R;

public class OnBoardActivity extends AppCompatActivity {
    CreateProfileFragment createProfileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        loadCreatProfilFragment();
    }

    /** @Tati this is the onboard activity, it holds all the fragments for onboarding. It starts with the Create Profile
     * Fragment (copy and paste of the EditProfileActvity) when you click next on each fragment, you should call
     * getActivity().getFragmentManager to replace the current fragment with the next.
     */

    public void loadCreatProfilFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        createProfileFragment = new CreateProfileFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.onboard_main_fragment_container,createProfileFragment);
        fragmentTransaction.commit();
    }

}
