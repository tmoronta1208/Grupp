package com.example.c4q.capstone;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PreferencesViewHolder extends RecyclerView.ViewHolder {
    private TextView prefType;
    private ImageView prefIcon;
    private Context prefContext;

    public PreferencesViewHolder(View itemView) {
        super(itemView);
        prefContext = itemView.getContext();
    }

    public void setPrefIcon(String url) {
        prefIcon = itemView.findViewById(R.id.pref_icon);
        Glide.with(getPrefContext()).load(url).into(prefIcon);
    }

    public void setPrefType(String type) {
        prefType = itemView.findViewById(R.id.pref_type);
        prefType.setText(type);
    }

    public Context getPrefContext() {
        return prefContext;
    }
}
