package com.example.user.eventmanager;

import com.parse.ParseClassName;
import com.parse.ParseObject;

import java.util.Calendar;
import java.util.Date;

@ParseClassName("Event")
public class EventObject extends ParseObject
{
    public EventObject()
    {
    }

    public EventObject(String title, String description, Date date)
    {
        put("title", title);
        put("description", description);
        put("date", date);
    }

    @Override
    public String toString() {
        Date date = (Date)get("date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return get("title") + "  " +
                calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                calendar.get(Calendar.MONTH) + "/" +
                calendar.get(Calendar.YEAR);
    }
}
