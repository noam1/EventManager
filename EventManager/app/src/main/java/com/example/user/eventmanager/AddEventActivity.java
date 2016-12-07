package com.example.user.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;

public class AddEventActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatePicker datePicker;

    private EditText titleEdit;
    private EditText descriptionEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        toolbar = (Toolbar)findViewById(R.id.addEventToolbar);
        setSupportActionBar(toolbar);

        titleEdit = (EditText)findViewById(R.id.titleEdit);
        descriptionEdit = (EditText)findViewById(R.id.descriptionEdit);

        datePicker = (DatePicker)findViewById(R.id.datePicker);
    }

    private void addEvent()
    {
        String title = titleEdit.getText().toString();
        String description = descriptionEdit.getText().toString();
        Date date = getDateFromDatePicker(datePicker);

        EventObject newEvent = new EventObject(title, description, date);
        newEvent.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    throw new RuntimeException();
            }
        });
    }

    private Date getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_event_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.event_done:

                addEvent();
                finish();
                break;
        }

        return true;
    }
}
