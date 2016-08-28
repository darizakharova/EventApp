package com.wukker.sb.eventconnectionfortopmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONDeserialaizationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONSerializationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.SharedPreferencesBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.Event;
import com.wukker.sb.eventconnectionfortopmobile.model.EventType;
import com.wukker.sb.eventconnectionfortopmobile.model.Questionnaire;
import com.wukker.sb.eventconnectionfortopmobile.model.Response;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

import java.util.ArrayList;

public class Quest3Activity extends AppCompatActivity {
    Questionnaire questionnaire;
    Spinner spinner;
    ArrayList<Event> events;
    ArrayList<Event> protoEvents;
    ArrayAdapter<String> adapter;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest3);
        String jsQuest = getIntent().getStringExtra("quest");
        events = new ArrayList<>();
        protoEvents = new ArrayList<>();
        Gson gson = new Gson();
        questionnaire = gson.fromJson(jsQuest, Questionnaire.class);
        sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);

        spinner = (Spinner)findViewById(R.id.spinner6);
        protoEvents = JSONDeserialaizationBrain.getEventList(Constants.conferenceID);
        for (Event event: protoEvents)
        {
            if (event.getEventType() != EventType.ORGANIZATIONAL)
                events.add(event);
        }

        ArrayList<String> eventsForSpinner = new ArrayList<>();
        for (Event event : events)
        {
            eventsForSpinner.add(event.getName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventsForSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quest3, menu);
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

    public void onClick(View view) {
        int selected = spinner.getSelectedItemPosition();
        questionnaire.setBestEventId(events.get(selected).getId());
        User user = SharedPreferencesBrain.readUser(sharedPreferences);

        String response = JSONSerializationBrain.putQuest(questionnaire, user);
        System.out.println(response);
        Intent intent = new Intent(Quest3Activity.this, MainActivity.class);
        startActivity(intent);
       /* Gson gson = new Gson();
        String quest = gson.toJson(questionnaire);

        switch (view.getId())
        {
            case (R.id.buttonbackq3):
            {

                Intent intent = new Intent(Quest3Activity.this, MainActivity.class);
                intent.putExtra("quest", quest);
                startActivity(intent);

                break;
            }
            case (R.id.buttonforwardq3):
            {
                Intent nIntent = new Intent(Quest3Activity.this, Quest4Activity.class);
                nIntent.putExtra("quest", quest);
                startActivity(nIntent);
                break;
            }
        }*/
    }
}
