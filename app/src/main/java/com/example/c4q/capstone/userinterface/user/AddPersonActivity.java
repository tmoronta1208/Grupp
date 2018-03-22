package com.example.c4q.capstone.userinterface.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.user.userprofilefragments.userprofilecontroller.GroupsAdapter;

/**
 * Created by melg on 3/21/18.
 */

public class AddPersonActivity extends AppCompatActivity{
    private EditText addPersonEmail,addPersonNumber;
    private String newUserEmail,newUserPhone;
    private Button findFriends;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addperson);

        addPersonEmail = findViewById(R.id.enteremail_ap);
        addPersonNumber = findViewById(R.id.enterphone_ap);
        findFriends = findViewById(R.id.find_friends_button);

        newUserEmail = addPersonEmail.getText().toString();
        newUserPhone = addPersonNumber.getText().toString();


//        findFriends.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               //
//            }
//        });
    }
}
