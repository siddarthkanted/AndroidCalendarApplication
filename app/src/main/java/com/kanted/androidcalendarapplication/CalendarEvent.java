package com.kanted.androidcalendarapplication;

import android.database.Cursor;
import android.provider.CalendarContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sikanted on 11/18/2015.
 */
public class CalendarEvent {


    public static Map<String, String> GetCalendarEventMap(Cursor eventCursor, boolean formatDate){
        Map<String, String> calendarEventMap = new HashMap<>();
        String[] calendarParameterList = GetCalendarParametersStringArray();

        //initialize map
        for(int count=0; count< calendarParameterList.length; count++){
            calendarEventMap.put(calendarParameterList[count], "-");
        }

        //put values in map
        if (eventCursor != null) {
            for(int count=0; count< calendarParameterList.length; count++){
                String value = eventCursor.getString(count);

                if (formatDate) {
                    switch(calendarParameterList[count]){
                        case CalendarContract.Instances.BEGIN :
                        case CalendarContract.Instances.END : value = CalendarUtils.ConvertMilliSecondsToFormattedDate(value);
                            break;
                    }
                }

                calendarEventMap.put(calendarParameterList[count], value);
            }
        }

        return calendarEventMap;
    }


    public static String[] GetCalendarParametersStringArray(){
        List<String> calendarParametersList = new ArrayList<>();
        calendarParametersList.add(CalendarContract.Instances.TITLE);
        calendarParametersList.add(CalendarContract.Instances.BEGIN);
        calendarParametersList.add(CalendarContract.Instances.END);
        calendarParametersList.add(CalendarContract.Instances.ALL_DAY);
        calendarParametersList.add(CalendarContract.Instances.EVENT_ID);
        return calendarParametersList.toArray(new String[calendarParametersList.size()]);
    }

}
