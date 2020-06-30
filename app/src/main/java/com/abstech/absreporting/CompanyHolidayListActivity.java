package com.abstech.absreporting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.github.tibolte.agendacalendarview.CalendarPickerController;

import com.github.tibolte.agendacalendarview.AgendaCalendarView;
import com.github.tibolte.agendacalendarview.CalendarManager;
import com.github.tibolte.agendacalendarview.CalendarPickerController;
import com.github.tibolte.agendacalendarview.agenda.AgendaEventView;
import com.github.tibolte.agendacalendarview.models.BaseCalendarEvent;
import com.github.tibolte.agendacalendarview.models.CalendarEvent;
import com.github.tibolte.agendacalendarview.models.DayItem;

import com.github.tibolte.agendacalendarview.models.WeekItem;
import com.github.tibolte.agendacalendarview.models.WeekItem;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyHolidayListActivity extends BaseActivity implements CalendarPickerController {

    private static final String LOG_TAG = CompanyHolidayListActivity.class.getSimpleName();

   // @BindView(R.id.activity_toolbar)
    Toolbar mToolbar;
  //  @BindView(R.id.agenda_calendar_view)
    //AgendaEventView mAgendaCalendarView;
    AgendaCalendarView mAgendaCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_holiday_list);
        //ButterKnife.bind(this);
        mToolbar=findViewById(R.id.activity_toolbar);
        mAgendaCalendarView=findViewById(R.id.agenda_calendar_view);
        setSupportActionBar(mToolbar);

        // minimum and maximum date of our calendar
        // 2 month behind, one year ahead, example: March 2015 <-> May 2015 <-> May 2016
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -2);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);

        List<CalendarEvent> eventList = new ArrayList<>();
        mockList(eventList);
        // Sync way
        /*
        mAgendaCalendarView.init(eventList, minDate, maxDate, Locale.getDefault(), this);
        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
        */
        //Async way


        //////// This can be done once in another thread
        CalendarManager calendarManager = CalendarManager.getInstance(getApplicationContext());
        calendarManager.buildCal(minDate, maxDate, Locale.getDefault());
        calendarManager.loadEvents(eventList);
        ////////

        List<CalendarEvent> readyEvents = calendarManager.getEvents();
//        List<DayItem> readyDays = calendarManager.getDays();
//        List<WeekItem> readyWeeks = calendarManager.getWeeks();
//        mAgendaCalendarView.init(readyEvents,minDate,maxDate,Locale.getDefault(),this);
//     //   mAgendaCalendarView.init(Locale.getDefault(), readyWeeks,readyDays,readyEvents,this);
//        mAgendaCalendarView.addEventRenderer(new DrawableEventRenderer());
//        mAgendaCalendarView.i
    }

    @Override
    public void onDaySelected(DayItem dayItem) {
        Log.d(LOG_TAG, String.format("Selected day: %s", dayItem));
    }

    @Override
    public void onEventSelected(CalendarEvent event) {
        Log.d(LOG_TAG, String.format("Selected event: %s", event));
    }

    @Override
    public void onScrollToDate(Calendar calendar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        }
    }

    // endregion

    // region Private Methods

//    private void mockList(List<CalendarEvent> eventList) {
//        Calendar startTime1 = Calendar.getInstance();
//        Calendar endTime1 = Calendar.getInstance();
//        endTime1.add(Calendar.MONTH, 1);
//        BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
//                ContextCompat.getColor(this, R.color.orange_dark), startTime1, endTime1, true);
//        eventList.add(event1);
//
//        Calendar startTime2 = Calendar.getInstance();
//        startTime2.add(Calendar.DAY_OF_YEAR, 1);
//        Calendar endTime2 = Calendar.getInstance();
//        endTime2.add(Calendar.DAY_OF_YEAR, 3);
//        BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
//                ContextCompat.getColor(this, R.color.yellow), startTime2, endTime2, true);
//        eventList.add(event2);
//
//        Calendar startTime3 = Calendar.getInstance();
//        Calendar endTime3 = Calendar.getInstance();
//        startTime3.set(Calendar.HOUR_OF_DAY, 14);
//        startTime3.set(Calendar.MINUTE, 0);
//        endTime3.set(Calendar.HOUR_OF_DAY, 15);
//        endTime3.set(Calendar.MINUTE, 0);
//        DrawableCalendarEvent event3 = new DrawableCalendarEvent("Visit of Harpa", "", "Dalvík",
//                ContextCompat.getColor(this, R.color.blue_dark), startTime3, endTime3, false, android.R.drawable.ic_dialog_info);
//        eventList.add(event3);
//    }
private void mockList(List<CalendarEvent> eventList) {
    Calendar startTime1 = Calendar.getInstance();
    Calendar endTime1 = Calendar.getInstance();
    endTime1.add(Calendar.MONTH, 1);
    BaseCalendarEvent event1 = new BaseCalendarEvent("Thibault travels in Iceland", "A wonderful journey!", "Iceland",
            ContextCompat.getColor(this, R.color.orange_dark), startTime1, endTime1, true);
    eventList.add(event1);

    Calendar startTime2 = Calendar.getInstance();
    startTime2.add(Calendar.DAY_OF_YEAR, 1);
    Calendar endTime2 = Calendar.getInstance();
    endTime2.add(Calendar.DAY_OF_YEAR, 3);
    BaseCalendarEvent event2 = new BaseCalendarEvent("Visit to Dalvík", "A beautiful small town", "Dalvík",
            ContextCompat.getColor(this, R.color.yellow), startTime2, endTime2, true);
    eventList.add(event2);

    // Example on how to provide your own layout
    Calendar startTime3 = Calendar.getInstance();
    Calendar endTime3 = Calendar.getInstance();
    startTime3.set(Calendar.HOUR_OF_DAY, 14);
    startTime3.set(Calendar.MINUTE, 0);
    endTime3.set(Calendar.HOUR_OF_DAY, 15);
    endTime3.set(Calendar.MINUTE, 0);
    DrawableCalendarEvent event3 = new DrawableCalendarEvent("Visit of Harpa", "", "Dalvík",
            ContextCompat.getColor(this, R.color.blue_dark), startTime3, endTime3, false,android.R.drawable.ic_dialog_info);
    eventList.add(event3);
}

    // endregion
}