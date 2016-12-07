package com.example.user.eventmanager;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class EventDataManager
{
    public EventObject[] getAllEvents() throws ParseException {

        ParseQuery<EventObject> query = ParseQuery.getQuery(EventObject.class);
        List<EventObject> result = query.find();

        EventObject[] resultArray = new EventObject[result.size()];
        result.toArray(resultArray);

        return resultArray;
    }
}
