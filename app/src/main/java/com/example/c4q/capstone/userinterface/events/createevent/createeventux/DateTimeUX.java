package com.example.c4q.capstone.userinterface.events.createevent.createeventux;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.c4q.capstone.userinterface.events.CreateEventPresenter;

/**
 * Created by amirahoxendine on 3/20/18.
 */

public class DateTimeUX {
    private TimePicker timePicker;
    private DatePicker datePicker;
    private Button closeButton;
    TextView addDate, addTime, dateAndTime;
    private CreateEventPresenter eventPresenter;

    public DateTimeUX(TimePicker timePicker, DatePicker datePicker, Button closeButton, TextView addDate, TextView addTime, TextView dateAndTime, CreateEventPresenter eventPresenter) {
        this.timePicker = timePicker;
        this.datePicker = datePicker;
        this.closeButton = closeButton;
        this.addDate = addDate;
        this.addTime = addTime;
        this.dateAndTime = dateAndTime;
        this.eventPresenter = eventPresenter;

        dateAndTime.setText(eventPresenter.dateTime);
        setCloseButton();
        setDatTimeClick();
    }

    /** Date and Time -AJ */
    /*click listener for date and time -AJ*/
    public void setDatTimeClick(){
        addTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);
            }
        });

        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);
            }
        });

    }

    /*click listner to hide date and time picker -AJ*/
    public void setCloseButton(){
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePicker.getVisibility() == View.VISIBLE) {
                    eventPresenter.setEventTime(timePicker);
                    dateAndTime.setText(eventPresenter.dateTime);
                    timePicker.setVisibility(View.GONE);
                } else if (datePicker.getVisibility() == View.VISIBLE) {
                    /*eventPresenter.setEventDate(datePicker);
                    dateAndTime.setText(eventPresenter.dateTime);
                    datePicker.setVisibility(View.GONE);*/
                }
                closeButton.setVisibility(View.GONE);
            }
        });

    }

}
