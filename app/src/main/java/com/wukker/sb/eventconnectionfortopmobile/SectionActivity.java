package com.wukker.sb.eventconnectionfortopmobile;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wukker.sb.eventconnectionfortopmobile.brains.JSONDeserialaizationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONSerializationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.SharedPreferencesBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.Event;
import com.wukker.sb.eventconnectionfortopmobile.model.EventType;
import com.wukker.sb.eventconnectionfortopmobile.model.Rating;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

public class SectionActivity extends ListActivity {
    private Event event;
    private ArrayAdapter<String> staffArray;
    int proposition;
    RatingBar ratingBar;
    SharedPreferences sharedPreferences;
    String key;
    User user;
    int lastRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section);
        getListView().setEmptyView(findViewById(R.id.empty1));
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
        user = SharedPreferencesBrain.readUser(sharedPreferences);

        proposition = getIntent().getIntExtra("position", 0);
        event = JSONDeserialaizationBrain.getEvent(proposition);

        key = "Event" + event.getName();


        //{ratingBar.setRating(SharedPreferencesBrain.getRating(key, sharedPreferences));}

        TextView timeView = (TextView)findViewById(R.id.textView5);


        timeView.setText(event.toTimeString() + "\n" + "\n" + event.toListName() + "\n" + "\n" + event.toListBrief());

        if (event.getEventType() != EventType.ORGANIZATIONAL)
        {
            staffArray = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                    JSONDeserialaizationBrain.getStaffForListView(event));
            setListAdapter(staffArray);
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener(){

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                //ratingBar.setNumStars(5);


                ratingBar.setRating(rating);
                Rating ratingAsObj = new Rating((int)rating, user.getId());

                String response = JSONSerializationBrain.putRating(ratingAsObj, event.getId(),
                        Constants.TIMEOUT);
                System.out.println(response);

                SharedPreferencesBrain.saveRating((int) rating, sharedPreferences, key);

            }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_section, menu);
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
        Intent intent = new Intent(SectionActivity.this, StaffActivity.class);
        intent.putExtra("position", (proposition*100+position));
        startActivity(intent);
    }

    public void onClick(View view) {

        switch (view.getId())
        {
            case (R.id.imageButtonProg):
            {
                Intent intent1 = new Intent(SectionActivity.this, MainActivity.class);
                startActivity(intent1); break;
            }
            case (R.id.imageButtonList):
            {
                Intent intent2 = new Intent(SectionActivity.this, UserListActivity.class);
                startActivity(intent2); break;
            }
            case (R.id.imageButtonQuest):
            {
                Intent intent3 = new Intent(SectionActivity.this, Quest1Activity.class);
                startActivity(intent3); break;
            }
            default:
            {
                Intent intent = new Intent(SectionActivity.this, MainActivity.class);
                startActivity(intent);
            }



        }
    }


}
