package com.example.c4q.capstone.userinterface.events.createevent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventTwoFragment extends Fragment {
    private String title;
    private int imageMain;
    private int imageSecondary;
    View view;


    public CreateEventTwoFragment() {
        // Required empty public constructor
    }

    public static CreateEventTwoFragment newInstance(String title) {
        CreateEventTwoFragment fragment = new CreateEventTwoFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageMain = getArguments().getInt("imageMain", 0);
        imageSecondary = getArguments().getInt("imageSecondary", 0);
        title = getArguments().getString("title");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_create_event_two, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.txtMain);
        tvLabel.setText(title);

        return view;
    }

}
