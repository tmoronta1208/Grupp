package com.example.c4q.capstone.utils;


import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.c4q.capstone.R;
import com.example.c4q.capstone.database.publicuserdata.UserIcon;
import com.example.c4q.capstone.userinterface.user.UserProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static com.example.c4q.capstone.utils.Constants.USER_ICON;

public class UserIconCode extends AppCompatActivity{

//    userImage.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            uploadImage();
//        }
//    });
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_PHOTO_PICKER) {
//            final Uri uri = data.getData();
//            UserIcon test = new UserIcon("hello");
//            FirebaseDatabase.getInstance().getReference().child(USER_ICON).child(currentUserInstance.getUserID()).setValue(test, new DatabaseReference.CompletionListener() {
//                @Override
//                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                    if (databaseError == null) {
//                        String key = databaseReference.getKey();
//                        StorageReference storage = FirebaseStorage.getInstance()
//                                .getReference(USER_ICON)
//                                .child(currentUserInstance.getUserID())
//                                .child(key)
//                                .child(uri.getLastPathSegment());
//                        storage.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    UserIcon test = new UserIcon(task.getResult().getMetadata().getDownloadUrl().toString());
//                                    FirebaseDatabase.getInstance().getReference().child(USER_ICON).child(currentUserInstance.getUserID()).setValue(test);
//
//                                }
//                            }
//                        });
//                    }
//                }
//            });
//        }
//
//    }
//
//
//    public void uploadImage() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
//        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
//
//    }
//
//    public void newIconImg() {
//        userIconDB.child(USER_ICON).child(currentUserInstance.getUserID()).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                UserIcon userIcon = dataSnapshot.getValue(UserIcon.class);
//                try {
//                    String userIconUrl = userIcon.getIcon_url();
//                    Glide.with(UserProfileActivity.this).load(userIconUrl).into(userImage);
//
//                } catch (NullPointerException e) {
//                    e.printStackTrace();
//                    userImage.setImageResource(R.drawable.default_avatar);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

}
