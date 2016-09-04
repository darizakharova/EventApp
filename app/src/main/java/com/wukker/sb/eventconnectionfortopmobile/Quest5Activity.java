package com.wukker.sb.eventconnectionfortopmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.facebook.login.LoginManager;
import com.google.gson.Gson;
import com.vk.sdk.VKSdk;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONDeserialaizationBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.Event;
import com.wukker.sb.eventconnectionfortopmobile.model.EventType;
import com.wukker.sb.eventconnectionfortopmobile.model.Questionnaire;
import com.wukker.sb.eventconnectionfortopmobile.model.Staff;
import com.wukker.sb.eventconnectionfortopmobile.model.StaffType;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

import java.util.ArrayList;

public class Quest5Activity extends AppCompatActivity {
    Questionnaire questionnaire;
    Spinner spinner1;
    Spinner spinner2;
    ArrayList<Event> protoEvents;
    ArrayList<Event> events;
    ArrayList<Staff> staffs;
    ArrayList<String> forAdapterEvents;
    ArrayList<String> forAdapterM;
    ArrayAdapter<String> adapterE;
    ArrayAdapter<String> adapterM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest5);
        String jsQuest = getIntent().getStringExtra("quest");
        Gson gson = new Gson();
        questionnaire = gson.fromJson(jsQuest, Questionnaire.class);

        spinner1 = (Spinner)findViewById(R.id.spinner9);
        spinner2 = (Spinner)findViewById(R.id.spinner10);

        protoEvents = JSONDeserialaizationBrain.getEventList(Constants.CONFERENCE_ID);
        for (Event event : protoEvents)
        {
            if (event.getEventType() != EventType.ORGANIZATIONAL)
            {
                events.add(event);
                for (Staff staff: event.getAllStaff())
                {
                    if (staff.getStaffType() == StaffType.SPEAKER)
                        staffs.add(staff);
                }
            }

        }

        for (Staff staff : staffs)
        {
            forAdapterM.add(staff.toListString());
        }

        for (Event event : events)
        {
            forAdapterEvents.add(event.getName());
        }

        adapterE = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, forAdapterEvents);
        adapterE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapterE);

        adapterM = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, forAdapterM);
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapterM);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quest5, menu);
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
        if (id == R.id.logout) {
            LoginManager.getInstance().logOut();
            VKSdk.logout();
            SharedPreferences sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        int selected1 = spinner1.getSelectedItemPosition();
        int selected2 = spinner2.getSelectedItemPosition();
        questionnaire.setBestSpeakerEventId(events.get(selected1).getId());
        questionnaire.setBestSpeakerId(staffs.get(selected2).getId());

        Gson gson = new Gson();
        String quest = gson.toJson(questionnaire);

        switch (view.getId())
        {
            case (R.id.buttonbackq5):
            {

                Intent intent = new Intent(Quest5Activity.this, Quest4Activity.class);
                intent.putExtra("quest", quest);
                startActivity(intent);
                break;
            }
            case (R.id.buttonforwardq5):
            {
                Intent nIntent = new Intent(Quest5Activity.this, Quest6Activity.class);
                nIntent.putExtra("quest", quest);
                startActivity(nIntent);
                break;
            }
        }
    }
}
