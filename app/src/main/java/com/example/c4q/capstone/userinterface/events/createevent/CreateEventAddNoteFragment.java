package com.example.c4q.capstone.userinterface.events.createevent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.EventActivity;
import com.example.c4q.capstone.userinterface.events.EventFragmentListener;
import com.example.c4q.capstone.userinterface.events.createevent.createeventux.EditTextUX;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventAddNoteFragment extends Fragment {
    NewEventBuilder newEventBuilder;
    CreateEventPresenter eventPresenter;
    EditTextUX editTextUX;
    EditText addNote;
    View rootView;
    String eventID;

    public CreateEventAddNoteFragment() {
        // Required empty public constructor
    }

    public static CreateEventAddNoteFragment newInstance(NewEventBuilder newEventBuilder) {
        CreateEventAddNoteFragment fragment = new CreateEventAddNoteFragment();
        fragment.loadeEventSingleton(newEventBuilder);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventPresenter = new CreateEventPresenter(NewEventBuilder.getInstance());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_create_event_add_note, container, false);
        addNote = rootView.findViewById(R.id.add_note_tv);
        editTextUX = new EditTextUX(addNote, eventPresenter, CreateEventAddNoteFragment.this.getActivity(), rootView, "addNote", new EventFragmentListener() {
            @Override
            public void swapFragments() {
                Log.d("Add note frag", "Swap fragments called");
                loadEventFragment();
            }

            @Override
            public void getEventIdKEy(String key) {
                eventID = key;
                loadEventFragment();
            }
        });
        return rootView;
    }

    public void loadEventFragment() {
        Intent intent = new Intent(CreateEventAddNoteFragment.this.getActivity(), EventActivity.class);
        intent.putExtra("eventID", eventID);
        intent.putExtra("eventType", "new");
        startActivity(intent);
        newEventBuilder.destroyInstance();
        getActivity().finish();
    }

    public void loadeEventSingleton(NewEventBuilder eventPTSingleton){
        newEventBuilder = eventPTSingleton;
        eventPresenter = new CreateEventPresenter(newEventBuilder);
    }

    public void setETActionListener(final EditText editText){
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {


                    eventPresenter.setEventNote(editText.getText().toString());
                    if (eventPresenter.validateEvent()){
                        loadEventFragment();
                    }
                    handled = true;
                }
                return handled;
            }
        });
    }

}
