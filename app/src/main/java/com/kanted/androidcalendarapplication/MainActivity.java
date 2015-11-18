package com.kanted.androidcalendarapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GetAllCalendarEvents(){
        String events = CalendarEvents.GetAllCalendarEventsInString(this);
        TextView textView = (TextView)findViewById(R.id.calendarEvents);
        textView.setText(events);
    }

    @Override
    public void onResume(){
        super.onResume();
        GetAllCalendarEvents();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void OpenCalendarSpecificEventIntent(View view){
        EditText openCalendarSpecificEventIntentEditText = (EditText)findViewById(R.id.openCalendarSpecificEventIntentEditTextId);
        Long eventId = Long.parseLong(openCalendarSpecificEventIntentEditText.getText().toString());
        CalendarEvents.OpenCalendarSpecificEventIntent(this, eventId);
    }

    public void OpenCalendarAddEventIntent(View view){
        CalendarEvents.OpenCalendarAddEventIntent(this);
    }

    public void OpenCalendarDayActivity(View view){
        Intent intent = new Intent(getBaseContext(), CalendarDayActivity.class);
        startActivity(intent);
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
