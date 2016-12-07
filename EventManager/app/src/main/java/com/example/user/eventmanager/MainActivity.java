package com.example.user.eventmanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final String APP_ID = "2M63kjzMqPESvFpjVl8Cz7fiQOeWWymAOw7R8HWX";
    private final String CLIENT_KEY = "1Us6AidXzdDLwjN7ouEUw4NB5EMfeZrkgxEUp8gf";

    private EventDataManager dataManager;

    private Toolbar toolbar;

    private ListView eventList;
    private ArrayAdapter<EventObject> eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.mainToolbar);
        setSupportActionBar(toolbar);

        ParseObject.registerSubclass(EventObject.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APP_ID)
                .clientKey(CLIENT_KEY)
                .server("https://parseapi.back4app.com/").build());


        eventList = (ListView)findViewById(R.id.eventList);
        eventAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        eventList.setAdapter(eventAdapter);

        dataManager = new EventDataManager();
    }

    @Override
    protected void onResume() {
        new UpdateEventsTask().execute();



        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                Intent addEventIntent = new Intent(this, AddEventActivity.class);
                startActivity(addEventIntent);
                break;
        }

        return true;
    }

    class UpdateEventsTask extends AsyncTask<Void, Void, EventObject[]>
    {
        @Override
        protected EventObject[] doInBackground(Void... params)
        {
            EventObject[] events;

            try
            {
                events = dataManager.getAllEvents();
            }
            catch (ParseException e) {
                throw new RuntimeException();
            }

            return events;
        }

        @Override
        protected void onPostExecute(EventObject[] eventObjects)
        {
            eventAdapter.addAll(eventObjects);
            //eventAdapter.notifyDataSetChanged();

            //EventObject e = new EventObject("Hello", "Hello", Calendar.getInstance().getTime());

            // Remove all the items
            /*for (int i = 0; i < eventAdapter.getCount(); i++)
                eventAdapter.remove(eventAdapter.getItem(i));*/

            //eventAdapter.add(e);

            /*EventObject e = new EventObject("H", "H", Calendar.getInstance().getTime());
            eventAdapter.add(e);*/
        }
    }
}
