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

import com.google.gson.Gson;
import com.wukker.sb.eventconnectionfortopmobile.brains.SharedPreferencesBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.Questionnaire;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

public class Quest2Activity extends AppCompatActivity {
    private Questionnaire questionnaire;
    SharedPreferences sharedPreferences;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest2);
        sharedPreferences = getSharedPreferences(Constants.name, Context.MODE_PRIVATE);
        user = SharedPreferencesBrain.readUser(sharedPreferences);
        RatingBar organizationalScore = (RatingBar) findViewById(R.id.conferenceHall);
        RatingBar regitrationProcess = (RatingBar) findViewById(R.id.registrationprocess);
        RatingBar organizerWorkScore = (RatingBar) findViewById(R.id.organizationalExellence);
        RatingBar themesRelevanceScore = (RatingBar) findViewById(R.id.actuality);

        questionnaire = new Questionnaire(user.getId(), Constants.CONFERENCE_ID);

        organizationalScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {



                ratingBar.setRating(rating);
                questionnaire.setOrganizationalScore((int)rating);


            }
        });

        regitrationProcess.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {



                ratingBar.setRating(rating);
                questionnaire.setRegitrationProcess((int) rating);


            }
        });

        organizerWorkScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {



                ratingBar.setRating(rating);
                questionnaire.setOrganizerWorkScore((int) rating);


            }
        });

        themesRelevanceScore.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {


                ratingBar.setRating(rating);
                questionnaire.setThemesRelevanceScore((int) rating);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quest2, menu);
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
        Gson gson = new Gson();
        String quest = gson.toJson(questionnaire);

        switch (view.getId())
        {
            case (R.id.buttonbackq2):
            {

                Intent intent = new Intent(Quest2Activity.this, MainActivity.class);
                startActivity(intent);
                break;
            }
            case (R.id.buttonforwardq2):
            {
                Intent nIntent = new Intent(Quest2Activity.this, Quest3Activity.class);
                nIntent.putExtra("quest", quest);
                startActivity(nIntent);
                break;
            }
        }
    }
}
