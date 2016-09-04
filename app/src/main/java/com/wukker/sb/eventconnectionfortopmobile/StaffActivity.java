package com.wukker.sb.eventconnectionfortopmobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.vk.sdk.VKSdk;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONDeserialaizationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONSerializationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.SharedPreferencesBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.Event;
import com.wukker.sb.eventconnectionfortopmobile.model.Rating;
import com.wukker.sb.eventconnectionfortopmobile.model.Staff;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

public class StaffActivity extends AppCompatActivity {
    private Event event;
    private Staff staff;
    private TextView staffText;
    private RatingBar ratingBar;
    private SharedPreferences sharedPreferences;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);
        int proposition = getIntent().getIntExtra("position", 0);
        event = JSONDeserialaizationBrain.getEvent(proposition / 100);
        staff = JSONDeserialaizationBrain.getStaff(event, (proposition % 100));
        sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
        user = SharedPreferencesBrain.readUser(sharedPreferences);

        staffText = (TextView)findViewById(R.id.textStaff);
        ratingBar = (RatingBar)findViewById(R.id.ratingBar);

        staffText.setText(staff.toListString());

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                ratingBar.setRating(rating);
                Rating ratingAsObj = new Rating((int) rating, user.getId());

                String response = JSONSerializationBrain.putRating(ratingAsObj, event.getId(), Constants.TIMEOUT);
                System.out.println(response);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_staff, menu);
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
            Intent intent = new Intent(StaffActivity.this,SNRegistrationActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId())
        {
            case (R.id.imageButtonProg):
            {
                Intent intent1 = new Intent(StaffActivity.this, MainActivity.class);
                startActivity(intent1); break;
            }
            case (R.id.imageButtonList):
            {
                Intent intent2 = new Intent(StaffActivity.this, UserListActivity.class);
                startActivity(intent2); break;
            }
            case (R.id.imageButtonQuest):
            {
                Intent intent3 = new Intent(StaffActivity.this, Quest1Activity.class);
                startActivity(intent3); break;
            }
            default:
            {
                Intent intent = new Intent(StaffActivity.this, MainActivity.class);
                startActivity(intent);
            }



        }

    }
}
