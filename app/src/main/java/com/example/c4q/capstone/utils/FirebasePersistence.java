package com.example.c4q.capstone.utils;


import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class FirebasePersistence extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        FirebaseDatabase.getInstance().getReference().keepSynced(true);
    }
}
