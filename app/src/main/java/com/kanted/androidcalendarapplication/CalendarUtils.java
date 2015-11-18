package com.kanted.androidcalendarapplication;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sikanted on 11/18/2015.
 */
public class CalendarUtils {

    public static String dateFormat = "dd-MMM-yyyy hh:mm";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

    public static String ConvertMilliSecondsToFormattedDate(String milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(milliSeconds));
        return simpleDateFormat.format(calendar.getTime());
    }
}
