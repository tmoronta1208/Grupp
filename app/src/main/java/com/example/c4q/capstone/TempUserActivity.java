package com.example.c4q.capstone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.c4q.capstone.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by melg on 3/19/18.
 */

public class TempUserActivity extends AppCompatActivity{

    private CircleImageView profilePic;
    private TextView personName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_user_profile);

        profilePic = findViewById(R.id.circle_imageview);
        personName = findViewById(R.id.user_name);



    }
}
