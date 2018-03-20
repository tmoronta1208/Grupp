package com.example.c4q.capstone.utils;

import android.content.Context;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.network.barzzmodel.Results;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by amirahoxendine on 3/18/18.
 */

public class DummyDataUtility {
    List<Results> dummyBars;


    public List<Results> buildDummyList(Context context){
        Type collectiontype = new TypeToken<Collection<Results>>() {
        }.getType();
        Gson gs = new Gson();
        Collection<Results> dummyBarCol = null;
        InputStream is = context.getApplicationContext().getResources().openRawResource(R.raw.dummybars);
        InputStreamReader isr = new InputStreamReader(is);
        dummyBarCol = gs.fromJson(isr, collectiontype);
        dummyBars = new ArrayList<>();
        dummyBars.addAll(dummyBarCol);
        return dummyBars;
    }
}
