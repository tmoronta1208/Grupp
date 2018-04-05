package com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.userinterface.events.createevent.NewEventListener;

import java.util.Calendar;

/**
 * Created by amirahoxendine on 4/3/18.
 */

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {
    NewEventListener eventPresenter;
    TextView addTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        TimePickerDialog timePickerFragment = new TimePickerDialog( getActivity(),TimePickerDialog.THEME_HOLO_LIGHT, this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
        return timePickerFragment;
    }

    public void setEventPresnter(NewEventListener eventPresnter, TextView addTime){
        this.eventPresenter = eventPresnter;
        this.addTime = addTime;
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        eventPresenter.timeEntered(hourOfDay, minute, addTime);
    }
}
