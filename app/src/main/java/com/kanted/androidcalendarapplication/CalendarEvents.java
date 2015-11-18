package com.kanted.androidcalendarapplication;


import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.format.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarEvents {


    private static Uri.Builder GetCalendarUriBuilder(){

        Uri.Builder builder;

        if (android.os.Build.VERSION.SDK_INT <= 7)
        {
            // the old way
            builder = Uri.parse("content://calendar/instances/when").buildUpon();
        } else
        {
            // the new way
            builder = Uri
                    .parse("content://com.android.calendar/instances/when").buildUpon();
        }

        return builder;
    }

    public static String GetAllCalendarEventsInString(Context context){
        Cursor eventCursor = GetAllCalendarEvents(context);
        return packAllEventsInString(eventCursor);
    }

    public static List<Map<String, String>> GetAllCalendarEventsInList(Context context){
        Cursor eventCursor = GetAllCalendarEvents(context);
        return packAllEventsInList(eventCursor);
    }


    private static Cursor GetAllCalendarEvents(Context context){

        requestCalendarSync(context);

        ContentResolver contentResolver = context.getContentResolver();

        Uri.Builder builder = GetCalendarUriBuilder();

        long now = new Date().getTime();
        ContentUris.appendId(builder, now);
        ContentUris.appendId(builder, now + (DateUtils.DAY_IN_MILLIS * 2));

        Cursor eventCursor = contentResolver.query(builder.build(), CalendarEvent.GetCalendarParametersStringArray(), null, null, CalendarContract.Instances.BEGIN +" ASC");

        return eventCursor;
    }


    private static List<Map<String, String>> packAllEventsInList(Cursor eventCursor){
        List<Map<String, String>> calendarEventsList = new ArrayList<>();

        if (eventCursor != null) {
            while (eventCursor.moveToNext()) {
                Map<String, String> calendarEventMap = CalendarEvent.GetCalendarEventMap(eventCursor, true);
                calendarEventsList.add(calendarEventMap);
            }
        }

        return calendarEventsList;
    }

    private static String packAllEventsInString(Cursor eventCursor){
        StringBuilder stringBuilder = new StringBuilder();

        if (eventCursor != null) {

            while (eventCursor.moveToNext()) {
                Map<String, String> calendarEventMap = CalendarEvent.GetCalendarEventMap(eventCursor, true);

                for(Map.Entry<String, String> entry : calendarEventMap.entrySet()){
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append("::");
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append("\n");
                }

                stringBuilder.append("\n\n");
                stringBuilder.append("===============");
                stringBuilder.append("\n\n");
            }
        }

        return stringBuilder.toString();
    }

    private static void requestCalendarSync(Context context)
    {
        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccounts();

        for (Account account : accounts)
        {
            int isSyncable = ContentResolver.getIsSyncable(account,  CalendarContract.AUTHORITY);

            if (isSyncable > 0)
            {
                Bundle extras = new Bundle();
                extras.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
                ContentResolver.requestSync(accounts[0], CalendarContract.AUTHORITY, extras);
            }
        }
    }

    public static void OpenCalendarAddEventIntent(Context context){
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        context.startActivity(intent);
    }

    public static void OpenCalendarSpecificEventIntent(Context context, long eventID){
        Uri uri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, eventID);
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(uri);
        context.startActivity(intent);
    }

}
