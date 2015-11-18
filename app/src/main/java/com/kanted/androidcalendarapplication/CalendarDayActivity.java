package com.kanted.androidcalendarapplication;

import android.app.Activity;
import android.provider.CalendarContract;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class CalendarDayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_day);
        populateList(R.id.todayCalendarEventListView);
    }

    private void populateList(int listViewId){
        ListView listView = (ListView)findViewById(listViewId);

        List<Map<String, String>> calendarEventsList = CalendarEvents.GetAllCalendarEventsInList(this);
        List<ThreeStrings> threeStringsList = new ArrayList<>();

        for(Map<String, String> calendarEventsMap : calendarEventsList){

            String title = calendarEventsMap.get(CalendarContract.Instances.TITLE);
            String time = calendarEventsMap.get(CalendarContract.Instances.BEGIN) + "\tTO\t" + calendarEventsMap.get(CalendarContract.Instances.END);

            ThreeStrings threeStrings = new ThreeStrings(title, time, "");
            threeStringsList.add(threeStrings);
        }

        ThreeHorizontalTextViewsAdapter threeHorizontalTextViewsAdapter = new ThreeHorizontalTextViewsAdapter(this, R.layout.three_vertical_text_views_layout, threeStringsList);
        listView.setAdapter(threeHorizontalTextViewsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar_day, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
