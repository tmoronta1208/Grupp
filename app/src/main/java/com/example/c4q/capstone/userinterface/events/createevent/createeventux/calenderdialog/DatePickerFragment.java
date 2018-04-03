package com.example.c4q.capstone.userinterface.events.createevent.createeventux.calenderdialog;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.c4q.capstone.R;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventActivity;
import com.example.c4q.capstone.userinterface.events.createevent.CreateEventPresenter;
import com.example.c4q.capstone.userinterface.events.createevent.NewEventListener;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener{

    NewEventListener eventPresenter;
    TextView addDate;

        @NonNull
        @Override
        public Dialog onCreateDialog (Bundle savedInstanceState){

        // Use the current date as the default date in the date picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog new instance
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), R.style.datepicker,this, year, month, day);

        // return the DatePickerDialog
        return datePicker;
    }

    public void setEventPresnter(NewEventListener eventPresnter, TextView addDate){
            this.eventPresenter = eventPresnter;
            this.addDate = addDate;
    }

        @Override
        public void onDateSet (DatePicker view,int year, int monthOfYear, int dayOfMonth) {
            // Sets the date to textview
            // Month value start with zero, we have to add by one


                String date = "Date: " +(monthOfYear+1) + "/"+ dayOfMonth + "/" + year;
                eventPresenter.dateEntered(monthOfYear, dayOfMonth, addDate);

                //eventPresenter.setEventDate(date);

        }
}
