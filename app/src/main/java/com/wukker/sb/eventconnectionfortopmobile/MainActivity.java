package com.wukker.sb.eventconnectionfortopmobile;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wukker.sb.eventconnectionfortopmobile.brains.JSONDeserialaizationBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.Conference;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;


public class MainActivity extends ListActivity {

    private ArrayAdapter<String> eventArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Conference conference = JSONDeserialaizationBrain.getConference(Constants.conferenceID);

        eventArray = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, JSONDeserialaizationBrain.getEventForListView(conference));
        setListAdapter(eventArray);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(MainActivity.this, SectionActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    public void onClick(View view) {

        switch (view.getId())
        {
            case (R.id.imageButtonProg):
            {
                Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent1); break;
            }
            case (R.id.imageButtonList):
            {
                Intent intent2 = new Intent(MainActivity.this, UserListActivity.class);
                startActivity(intent2); break;
            }
            case (R.id.imageButtonQuest):
            {
                Intent intent3 = new Intent(MainActivity.this, Quest1Activity.class);
                startActivity(intent3); break;
            }
            default:
            {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }



        }

    }
}
