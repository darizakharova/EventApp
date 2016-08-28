package com.wukker.sb.eventconnectionfortopmobile;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wukker.sb.eventconnectionfortopmobile.brains.JSONDeserialaizationBrain;
import com.wukker.sb.eventconnectionfortopmobile.brains.JSONSerializationBrain;
import com.wukker.sb.eventconnectionfortopmobile.model.User;
import com.wukker.sb.eventconnectionfortopmobile.model.methods.Constants;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        TextView textView = (TextView)findViewById(R.id.allList);

        StringBuilder sb = new StringBuilder();
        ArrayList<User> users = new ArrayList<>();

        String response = JSONDeserialaizationBrain.getResponse(Constants.conferenceID);
        users = JSONDeserialaizationBrain.getUserListFromResponse(response);

        System.out.println("HERE");
        for (User user : users)
        {
            sb.append("\n" + Constants.space + Constants.space + Constants.space + user.getFirstname() + Constants.space + user.getMiddlename() + Constants.space + user.getLastname() + "," + Constants.space + user.getCompanyName()+"\n" + "_______________________");
        }

        String bigText = sb.toString();
        textView.setText(bigText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_list, menu);
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

        switch (view.getId())
        {
            case (R.id.imageButtonProg):
            {
                Intent intent1 = new Intent(UserListActivity.this, MainActivity.class);
                startActivity(intent1); break;
            }
            case (R.id.imageButtonList):
            {
                Intent intent2 = new Intent(UserListActivity.this, UserListActivity.class);
                startActivity(intent2); break;
            }
            case (R.id.imageButtonQuest):
            {
                Intent intent3 = new Intent(UserListActivity.this, Quest1Activity.class);
                startActivity(intent3); break;
            }
            default:
            {
                Intent intent = new Intent(UserListActivity.this, MainActivity.class);
                startActivity(intent);
            }



        }

    }
}
