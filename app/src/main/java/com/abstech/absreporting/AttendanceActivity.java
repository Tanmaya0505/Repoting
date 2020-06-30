package com.abstech.absreporting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AttendanceActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView DateEtxt;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private AlertDialog alertDialog;
    CalendarView calendarViewScrollable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        mToolbar = findViewById(R.id.toolbar);
        DateEtxt = findViewById(R.id.date);
        final CalendarView calendarView=(CalendarView)findViewById(R.id.datePicker1);
        dateFormatter = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string.title_attendance);

            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();

                }
            });
        }
        calendarViewScrollable = findViewById(R.id.datePicker1);
        Calendar calendar = Calendar.getInstance();
        calendarViewScrollable.setMinDate((new Date().getTime()));
//        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
//        long endOfMonth = calendar.getTimeInMillis();
        calendarViewScrollable.setMaxDate((new Date().getTime()));
//        calendarView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fromDatePickerDialog.show();
//            }
//        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                //Log.e("event", "datachanged");
                //fromDatePickerDialog.show();
                AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_attendance_dialog, viewGroup, false);
                Button button = dialogView.findViewById(R.id.btnsubmit);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
                builder.setCancelable(true);
                builder.setView(dialogView);
                alertDialog = builder.create();
                alertDialog.show();
            }

        });
        setDateTimeField();
        DateEtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromDatePickerDialog.show();
            }
        });
    }
    private void setDateTimeField() {
        Calendar newCalendar = Calendar.getInstance();
//        newCalendar.set(Calendar.MONTH, Calendar.JANUARY);
//        newCalendar.set(Calendar.DAY_OF_MONTH, 1);
//        newCalendar.set(Calendar.YEAR, 1900);
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                DateEtxt.setText(dateFormatter.format(newDate.getTime()));

                AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.activity_attendance_dialog, viewGroup, false);
                builder.setView(dialogView);
                alertDialog = builder.create();
                alertDialog.show();
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }
}
