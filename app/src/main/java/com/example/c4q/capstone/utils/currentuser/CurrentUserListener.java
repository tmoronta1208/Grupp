package com.example.c4q.capstone.utils.currentuser;

import com.example.c4q.capstone.database.privateuserdata.PrivateUser;

/**
 * Created by amirahoxendine on 3/23/18.
 */

public interface CurrentUserListener {
    void getPrivateUser(PrivateUser privateUser);
}
